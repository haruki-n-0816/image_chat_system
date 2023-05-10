<template>
  <div>
    <b-form @submit.prevent="createAccount" style="text-align: center;">
      <h3>アカウント新規作成</h3>
      <b-alert v-model="showSuccessMessage" variant="success" :dismissible="false" fade>新規作成に成功しました</b-alert>
      <b-form-group label="名前:">
        <b-form-input v-model="name" type="text"></b-form-input>
      </b-form-group>
      <b-form-group label="mail:">
        <b-form-input v-model="mail" type="email"></b-form-input>
      </b-form-group>
      <b-form-group label="Pass:">
        <b-form-input v-model="password" type="password"></b-form-input>
      </b-form-group>
      <b-button variant="primary" type="submit">新規登録</b-button>
      <b-button variant="primary" @click="$router.push('/Login')">キャンセル</b-button>
    </b-form>
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
