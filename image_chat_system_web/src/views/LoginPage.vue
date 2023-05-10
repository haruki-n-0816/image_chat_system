<template>
    <div class="text-center">
      <form @submit.prevent="loginCheck">
        <h3>ログイン</h3>
        <b-form-group label="mail:" label-for="mail-input">
          <b-form-input type="email" v-model="mail"></b-form-input>
        </b-form-group>
        <b-form-group label="パスワード:" label-for="password-input">
          <b-form-input type="password" v-model="password"></b-form-input>
        </b-form-group>
        <b-button variant="primary" type="submit">ログイン</b-button>
      </form>
      <router-link to="/create">新規登録の方はこちらから</router-link>
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
        errorMessage: ''
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
          }
        } catch (error) {
          alert("ログインに失敗しました");
        }
      }
    }
  }
  </script>
  