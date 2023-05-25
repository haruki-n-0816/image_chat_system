<template>
	<div class="forms">
		<h2>サインイン</h2>
		<form @submit.prevent="loginCheck">
			<b-img :src="logoPath" width="180px" />
			<div>
				<div class="login-input-wrapper">
					<b-form-input type="email" placeholder="Mail" v-model="mail"></b-form-input>
				</div>
				<b-form-input type="password" placeholder="Password" v-model="password"></b-form-input>
			</div>
			<div class="button-wrapper">
				<b-button class="login-button" variant="secondary" type="submit">ログイン</b-button>
				<router-link to="/create" class="to-create-page">新規登録の方はこちらから</router-link>
			</div>
		</form>
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
			errorMessage: '',
			logoPath: require('/public/assets/logo.png')
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
				sessionStorage.setItem('setAuthentication', JSON.stringify(response.data.result));
				if (response.data.result) {
					sessionStorage.setItem('userId', response.data.userId);
					sessionStorage.setItem('userName', response.data.userName);
					this.userId = sessionStorage.getItem('userId');
					this.userName = sessionStorage.getItem('userName');
					this.$router.push("/roomIndex");
				} else {
					alert("ログインに失敗しました。メールアドレス、パスワードをご確認ください。")
				}
			} catch (error) {
				console.log(error);
			}
		}
	}
}
</script>

<style>
body {
	justify-content: center;
	align-items: center;
	background-image: url(/public/assets/kabe.jpg);
	background-size: cover;
	background-position: center;
}

body::before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	background-image: url(/public/assets/kabe.jpg);
	background-size: cover;
	background-position: center;
	filter: blur(6px);
}

.forms {
	border: 1px solid #aaa;
	text-align: center;
	background-color: rgba(255, 255, 255, 0.7);
	padding: 2em;
	margin-top: 50px;
	margin-bottom: 0;
	margin-right: 300px;
	margin-left: 300px;
	border-radius: 30px;
}

@media (max-width: 600px) {
	.forms {
		margin: 20px;
		padding: 1em;
	}
}

@media (max-width: 600px) {
	.forms {
		margin: 20px;
		padding: 1em;
	}
}

@media (min-width: 601px) and (max-width: 900px) {
	.forms {
		margin: 40px;
		padding: 2em;
		max-width: 500px;
		margin-left: auto;
		margin-right: auto;
	}
}

@media (min-width: 901px) and (max-width: 1200px) {
	.forms {
		margin: 60px;
		padding: 3em;
		max-width: 600px;
		margin-left: auto;
		margin-right: auto;
	}
}

@media (min-width: 1201px) {
	.forms {
		margin: 60px 40px 40px 40px;
		padding: 2em;
		max-width: 800px;
		margin-left: auto;
		margin-right: auto;
	}
}

.login-button {
	margin-top: 30px;
}

.to-create-page {
	margin-top: 10px;
	margin-left: 0;
	color: rgb(0, 64, 255);
}

.button-wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 10px;
}

.login-input-wrapper {
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>