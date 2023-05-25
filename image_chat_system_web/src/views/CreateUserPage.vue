<template>
	<div>
		<div class="forms">
			<h2>ユーザー新規作成</h2>
			<b-form @submit.prevent="createAccount">
				<b-img :src="logoPath" width="180px" />
				<div>
					<b-alert v-model="showSuccessMessage" variant="success" :dismissible="false" fade>新規作成に成功しました</b-alert>
					<div class="login-input-wrapper">
						<b-form-input v-model="name" placeholder="Name" type="text"></b-form-input>
					</div>
					<div class="create-input-wrapper">
						<b-form-input type="text" name="email" v-model="email"></b-form-input>
						<p v-if="isInValidEmail" class="error" style="color:red">※メールアドレスを正しく入力してください。</p>
					</div>
					<div class="password-wrapper">
						<b-form-input v-model="password" placeholder="Password" type="password"
							class="password-input"></b-form-input>
						<b-button class="password-toggle" @click.once="showHidden"></b-button>
					</div>
					<div>
						<b-button variant="primary" class="create-user-button" type="submit"
							:disabled="isInValidEmail">新規登録</b-button>
						<b-button variant="secondary" class="create-user-button"
							@click="$router.push('/Login')">キャンセル</b-button>
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
	}, computed: {
		isFormInvalid() {
			return !(this.name && this.email && this.password);
		},
		isInValidEmail() {
			const reg = new RegExp(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/);
			return !reg.test(this.email);
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
		async showHidden(event) {
			event.preventDefault();
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
	background: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' height='48' width='48'><path d='M24 31.5q3.55 0 6.025-2.475Q32.5 26.55 32.5 23q0-3.55-2.475-6.025Q27.55 14.5 24 14.5q-3.55 0-6.025 2.475Q15.5 19.45 15.5 23q0 3.55 2.475 6.025Q20.45 31.5 24 31.5Zm0-2.9q-2.35 0-3.975-1.625T18.4 23q0-2.35 1.625-3.975T24 17.4q2.35 0 3.975 1.625T29.6 23q0 2.35-1.625 3.975T24 28.6Zm0 9.4q-7.3 0-13.2-4.15Q4.9 29.7 2 23q2.9-6.7 8.8-10.85Q16.7 8 24 8q7.3 0 13.2 4.15Q43.1 16.3 46 23q-2.9 6.7-8.8 10.85Q31.3 38 24 38Zm0-15Zm0 12q6.05 0 11.125-3.275T42.85 23q-2.65-5.45-7.725-8.725Q30.05 11 24 11t-11.125 3.275Q7.8 17.55 5.1 23q2.7 5.45 7.775 8.725Q17.95 35 24 35Z'/></svg>") no-repeat center center;
	background-size: 50% auto;
	cursor: pointer;
}

.password-toggle.is-visible {
	background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' height='48' width='48'><path d='m31.45 27.05-2.2-2.2q1.3-3.55-1.35-5.9-2.65-2.35-5.75-1.2l-2.2-2.2q.85-.55 1.9-.8 1.05-.25 2.15-.25 3.55 0 6.025 2.475Q32.5 19.45 32.5 23q0 1.1-.275 2.175-.275 1.075-.775 1.875Zm6.45 6.45-2-2q2.45-1.8 4.275-4.025Q42 25.25 42.85 23q-2.5-5.55-7.5-8.775Q30.35 11 24.5 11q-2.1 0-4.3.4-2.2.4-3.45.95L14.45 10q1.75-.8 4.475-1.4Q21.65 8 24.25 8q7.15 0 13.075 4.075Q43.25 16.15 46 23q-1.3 3.2-3.35 5.85-2.05 2.65-4.75 4.65Zm2.9 11.3-8.4-8.25q-1.75.7-3.95 1.075T24 38q-7.3 0-13.25-4.075T2 23q1-2.6 2.775-5.075T9.1 13.2L2.8 6.9l2.1-2.15L42.75 42.6ZM11.15 15.3q-1.85 1.35-3.575 3.55Q5.85 21.05 5.1 23q2.55 5.55 7.675 8.775Q17.9 35 24.4 35q1.65 0 3.25-.2t2.4-.6l-3.2-3.2q-.55.25-1.35.375T24 31.5q-3.5 0-6-2.45T15.5 23q0-.75.125-1.5T16 20.15Zm15.25 7.1Zm-5.8 2.9Z'/></svg>");
}

@media (any-hover: hover) {
	.password-toggle:hover {
		opacity: 0.7;
	}
}</style>