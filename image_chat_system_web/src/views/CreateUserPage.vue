<template>
	<div>
		<div class="forms">
			<h2>ユーザー新規作成</h2>
			<b-form @submit.prevent="createAccount">
				<b-img :src="logoPath" width="180px" />
				<div>
					<b-alert v-model="showSuccessMessage">新規作成に成功しました</b-alert>
					<div class="login-input-wrapper">
						<b-form-input v-model="name" placeholder="Name(10文字以内で入力してください)" type="text" maxlength="10"></b-form-input>
					</div>
					<div class="create-input-wrapper">
						<b-form-input type="text" name="email" placeholder="Mail" v-model="email"></b-form-input>
						<p v-if="isInValidEmail && email" class="error" style="color:red">※メールアドレスを正しく入力してください。</p>
					</div>
					<div class="password-wrapper">
						<b-form-input v-model="password" placeholder="Password(半角英数字のみ)" type="password" class="password-input" pattern="[\x01-\x7E]*"></b-form-input>
						<b-button class="password-toggle" @click.once="showHidden"></b-button>
					</div>
					<p v-if="isInValidPassword && password" class="error" style="color:red">※パスワードを正しく入力してください。</p>
					<div>
						<b-button variant="primary" class="create-user-button" type="submit" :disabled="isFormInvalid">新規登録</b-button>
						<b-button variant="secondary" class="create-user-button" @click="$router.push('/Login')">キャンセル</b-button>
						<p class="text">(au bord du feu)をご利用いただく際には、節度を守ってご利用ください。</p>
					</div>
				</div>
			</b-form>
		</div>
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
			logoPath: require('/public/assets/logo.png'),
			email: ''
		};
	}, 
	computed: {
		isFormInvalid() {
			return !(this.name && this.email && this.password);
		},
		isInValidEmail() {
			const reg = new RegExp(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/);
			return !reg.test(this.email);
		},
		isInValidPassword(){
			const vali = new RegExp(/^[A-Za-z0-9]+$/)
			return !vali.test(this.password);
		}
	},
	methods: {
		async createAccount() {
			try {
				if (this.isFormInvalid) {
					alert('入力フォームを正しく入力してください');
				} else if (!this.isFormInvalid) {
					const response = await axios.post('/create', {
						name: this.name,
						mail: this.email,
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
				}
			} catch (error) {
				console.error(error);
			}
		},
		async showHidden() {
			const passwordToggle = document.querySelector('.password-toggle')

			passwordToggle.addEventListener('click', (e) => {
				const input = e.target.previousElementSibling
				const type = input.getAttribute('type')
				input.setAttribute('type', type === 'password' ? 'text' : 'password')
				passwordToggle.classList.toggle('is-visible')
			})
		}
	},
};
</script>

<style>
.text {
	font-family: 'TsukuARdGothic-Regular';
}

.create-user-button {
	margin-top: 30px;
	margin-left: 10px;
	margin-bottom: 30px;
}

.create-input-wrapper {
	margin-top: 10px;
	margin-bottom: 10px;
}

.password-input {
	border: 1px solid #ddd;
	padding: 10px;
	border-radius: 5px;
	font-size: 16px;
	flex: 1;
	outline: none;
	appearance: none;
	padding: 10px 0 10px 10px;
	border: none;
	border-radius: 5px 0 0 5px;
	font-size: 16px;
}

.password-wrapper {
	display: flex;
	border-radius: 5px;
}

.password-toggle {
	width: 40px;
	border: none;
	background: url(/public/assets/show-eye.png);
	background-size: 100% auto;
	cursor: pointer;
}

.password-toggle.is-visible {
	background-image: url(/public/assets/hidden-eye.png);
}

@media (any-hover: hover) {
	.password-toggle:hover {
		opacity: 0.7;
	}
}</style>