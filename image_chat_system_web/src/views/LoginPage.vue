<template>
    <div >
        <form class="LoginForm" @submit.prevent="LoginCheck()">
            <h3 class="title">ログイン</h3>
            <br>
            <!-- <p>userId:{{ userId }} userName:{{ userName }}</p> -->
            <!-- <div v-if="errorMessage" class="error">{{ errorMessage }}</div> -->
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="email" v-model="mail">
            <br><br>
            <label>パスワード:</label>
            <input type="password" v-model="password">
            <br>
            <b-button class="createPageButton" variant="primary" type="submit">ログイン</b-button>
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
        async LoginCheck() {
            try {
                const respones = await axios.post('/login', {
                    mail: this.mail,
                    password: this.password,
                });
                console.log(respones);
                this.$store.dispatch('setAuthentication', respones.data.result);
                    if (respones.data.result) {
                        // alert("ログインしたよ！")
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
    .LoginForm{
        width: 40%;
    margin-left: 30%;
    }

    input {
    width: 80%;
    /* border-bottom: solid 1px #3e00fa;
    border-top: none;
    border-left: none;
    border-right: none; */
    font-size: 1rem;
    padding: 0.5em 0.4em;
    transition: all 0.4s;
    margin: 0.7rem 0;
    }

    input:focus {
    transform: scale3d(1.06,1.06,1.06);
    }

    .createPageButton {
    background: transparent;
    width: 50%;
    margin-top: 2.5rem;
    font-size: 1rem;
    padding: 1em 0;
    color: #bdc3c7;
    transition: all 0.6s;
    }

    /* .title{
        color: #040506;
    border-bottom: solid 1px #7035c4;
    padding: 0 0 0.8em 0;
    width: 50%;
    margin-top: 3%;
    margin-left: 25%;
    margin-bottom: 1em;
    } */

    .error{
        color: crimson;
    }

</style>