<template>
  <div>
    <div class="forms">
      <h2>ユーザー新規作成</h2>
      <b-form @submit.prevent="createAccount">
        <b-img :src="logoPath" width="150px"/>
          <div>
            <b-alert v-model="showSuccessMessage" variant="success" :dismissible="false" fade>新規作成に成功しました</b-alert>
              <div class="login-input-wrapper">
                <b-form-input v-model="name" placeholder="Name" type="text"></b-form-input>
              </div>
              <div class="create-input-wrapper">
                <b-form-input v-model="mail" placeholder="Mail" type="email"></b-form-input>
              </div>
                <b-form-input v-model="password" placeholder="Password" type="password"></b-form-input>
            <b-button variant="secondary" class="create-user-button" type="submit">新規登録</b-button>
            <b-button variant="secondary" class="create-user-button" @click="$router.push('/Login')">キャンセル</b-button>
          </div>
      </b-form>
    </div>
    </div>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';

export default {
  data() {
    return {
      name: '',
      mail: '',
      password: '',
      showSuccessMessage: false,
      logoPath: require('@/assets/logo3.png')
    };
  },
  methods: {
    async createAccount() {
      try {
        const response = await axios.post('/create', {
          name: this.name,
          mail: this.mail,
          password: this.password,
        });
        if (response.data === true) {
          this.showSuccessMessage = true;
          setTimeout(() => {
            this.$router.push('/login');
          }, 2000);
        } else {
          alert('既に登録されているメールアドレスです');
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style>
  .create-user-button{
    margin-top: 30px;
    margin-left: 10px;
  }

  .create-input-wrapper{
    margin-top: 10px;
    margin-bottom: 10px;
  }
</style>