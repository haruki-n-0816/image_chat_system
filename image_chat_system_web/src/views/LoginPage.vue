<template>
    <div>
        <p>次郎チャットログイン</p>
        <br>
        <form @submit.prevent="LoginCheck()">
            <label>mail</label>
            <input type="email" v-model="mail"><br>
            <label>パスワード</label>
            <input type="password" v-model="password"><br>
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
            password: ''
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
                this.$store.dispatch('setAuthentication', respones.data);
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