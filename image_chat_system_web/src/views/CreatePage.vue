<template>
  <div>
    <form class="CreateForm" style="text-align: center;">
      <h3 class="title">アカウント新規作成</h3>
      <br>
      <b-alert v-model="showSuccessMessage" variant="success" :dismissible="false" fade>新規作成に成功しました</b-alert>
      <label>名前:</label>
      <input class="loginInfo" type="text" v-model="name">
      <br><br>
      <label>mail:</label>
      <input class="loginInfo" type="mail" v-model="mail">
      <br><br>
      <label>Pass:</label>
      <input class="loginInfo" type="password" v-model="password">
      <br><br>
      <b-button class="btn btn-primary btn-space" variant="primary" @click="CreateAcaunt()">新規登録</b-button>
      <button class="btn btn-primary btn-space" @click="$router.push('/Login')">キャンセル</button>
      <br><br>
    </form>
  </div>
</template>
<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';

export default{
  data() {
    return {
      name: '',
      mail: '',
      password: '',
      showSuccessMessage: false,
    };
  },
  methods: {
    async CreateAcaunt() {
      try {
        const response = await axios.post('/create', {
          name: this.name,
          mail: this.mail,
          password: this.password,
        });
        if(response.data === true){
          // アカウント作成に成功した場合、ポップアップメッセージを表示して2秒後に画面を遷移する
          this.showSuccessMessage = true;
          setTimeout(() => {
            this.$router.push('/login');
          }, 1000);
        }else{
          alert("既に登録されているメールアドレスです")
        }
      } catch (error) {
        console.error(error);
      } 
    }
  },
}
</script>

  
  <style>
  .CreateForm {
    width: 40%;
    margin-left: 30%;
  }
  
  .btn-primary {
    width: 150px;
  }
  
  .btn-space {
    margin-right: 10px;
  }
  </style>