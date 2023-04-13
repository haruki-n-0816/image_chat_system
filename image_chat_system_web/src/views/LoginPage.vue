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
        <br>
        <router-link to="後から入力">新規作成はこちらから</router-link>
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
            console.log(this.mail);
            console.log(this.password);
            try {
                const respones = await axios.post('http://localhost:8081/login',
                    JSON.stringify({
                        mail: this.mail,
                        password: this.password
                    }),
                    {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                console.log(respones);
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