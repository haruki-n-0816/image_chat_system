
<template>
    <div>
        <p>新規作成</p><br>
        <label>名前:</label>
        <input type="text" v-model="name"><br>
        <label>mail:</label>
        <input type="mail" v-model="mail"><br>
        <label>Pass:</label>
        <input type="password" v-model="password"><br>
        <b-button variant="primary" @click="CreateAcaunt()">新規登録</b-button>
        <br>
        <router-link to="/Login">キャンセル</router-link><br><br>
        <b-button variant="primary" @click="imageup()">画像アップ</b-button>
    </div>
</template>

<script>
import axios from 'axios';
export default{
    data(){
        return{
            name:'',
            mail:'',
            password:''
        }
    },
    methods:{
        async CreateAcaunt(){
            alert("作成できそうだよ！");
            try{
                const response = await axios.post('localhost:8080/create',{
                    name: this.name,
                    mail: this.mail,
                    password: this.password,
                });
                console.log(response);
            } catch (error){
                console.error(error)
            }
        }
    },
    async imageup(){
        alert("画像編集！");
    }
    
}
</script>