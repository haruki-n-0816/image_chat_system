<template>
    <div>
        <p>次郎チャットログイン</p><br>
        <label>mail:</label>
        <input type="email" v-model="mail"><br>
        <label>パスワード:</label>
        <input type="password" v-model="password"><br>
        <b-button variant="primary" @click="LoginCheck()">ログイン</b-button>
        <br>
        <router-link to="/Create">新規作成はこちらから</router-link>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            mail: '',
            password: ''
        }
    },
    methods: {
        async LoginCheck() {
            alert("もーーーーーーーーーーーーーーーーーーーーり");
            try {
                const response = await axios.post('localhost:8080/login', {
                    mail: this.mail,
                    password: this.password,
                });
                console.log(response);
            } catch (error) {
                console.error(error);
            }
        }
    }
}
</script>

<style>
/* css */
</style>