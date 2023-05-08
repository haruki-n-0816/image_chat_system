<template>
    <div style="text-align: center;">
        <form  @submit.prevent="LoginCheck()" >
            <h3>ログイン</h3>
            <br>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input  type="email" v-model="mail">
            <br><br>
            <label>パスワード:</label>
            <input  type="password" v-model="password">
            <br><br>
            <b-button  variant="primary" type="submit">ログイン</b-button>
        </form>
        <router-link to="/create" >新規登録の方はこちらから</router-link>
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
        async LoginCheck() {
            try {
                const respones = await axios.post('/login', {
                    mail: this.mail,
                    password: this.password,
                });
                console.log(respones);
                this.$store.dispatch('setAuthentication', respones.data.result);
                    if (respones.data.result) {
                        this.$store.dispatch('setUserId', respones.data.userId);
                        this.$store.dispatch('setUserName', respones.data.userName);
                        this.userId = this.$store.getters.userId;
                        this.userName = this.$store.getters.userName;
                        this.$router.push("/chatindex2") //他のページに移動
                    }
            } catch (error) {
                alert("ログインの失敗")
                // this.errorMessage = "※ログインに失敗しました";
            }
        }
    }
}
</script>

<style>


</style>