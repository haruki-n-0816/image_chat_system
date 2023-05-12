<template>
  <div>
    
    <div class="forms">
      <form @submit.prevent="loginCheck">
        <b-img :src="logoPath" width="150px" />
        <div>
          <div class="login-input-wrapper">
            <b-form-input type="email" placeholder="Mail" v-model="mail"></b-form-input>
          </div>
          <b-form-input type="password" placeholder="Password" v-model="password"></b-form-input>
        </div>
        <div class="button-wrapper">
          <b-button class="login-button" variant="secondary" type="submit">ログイン</b-button>
          <router-link to="/create" class="to-create-page">新規登録の方はこちらから</router-link>
        </div>
      </form>
    </div> 
  </div>
</template>

<script>
  import axios from 'axios';
  axios.defaults.baseURL = 'http://localhost:8081';
  
  export default {
    data() {
      return {
        mail: '',
        password: '',
        userName: '',
        userId: '',
        errorMessage: '',
        logoPath: require('@/assets/logo3.png')
      }
    },
  
    methods: {
      async loginCheck() {
        try {
          const response = await axios.post('/login', {
            mail: this.mail,
            password: this.password,
          });
          console.log(response);
          this.$store.dispatch('setAuthentication', response.data.result);
          if (response.data.result) {
            this.$store.dispatch('setUserId', response.data.userId);
            this.$store.dispatch('setUserName', response.data.userName);
            this.userId = this.$store.getters.userId;
            this.userName = this.$store.getters.userName;
            this.$router.push("/roomIndex");
          }else{
            alert("ログインに失敗しました。メールアドレス、パスワードをご確認ください。")
          }
        } catch (error) {
          console.log(error);
        }
      }
    }
  }
</script>

<style>
  body {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url(~@/assets/kabe.jpg);
    background-size: cover;
    background-position: center;
  }

  body::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: -1;
    background-image: url(~@/assets/kabe.jpg);
    background-size: cover;
    background-position: center;
    filter: blur(4px); 
  }

  .forms {
    border: 1px solid #aaa;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.7);
    padding: 3em;
  }

  .login-button {
    margin-top: 30px;
  }

  .to-create-page {
    margin-top: 10px;
    margin-left: 0;
    color: brown;
  }

  .button-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
  }

  .login-input-wrapper {
    margin-top: 10px;
    margin-bottom: 10px;
  }
</style>