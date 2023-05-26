<template>
	<div class="chat-page">
		<main-navigation-bar></main-navigation-bar>
		<button ref="scrollbtntop" class="go-top"></button>
		<button ref="scrollbtn" class="go-down"></button>

		<div class="line-container" :key="imageKey">
			<div v-for="message in messages" :key="message.id">
				<div class="speech-balloon" :class="message.balloonClass">
					{{ formatDate(message.postTime) }}
					<!-- <button @click="confirmDelete(message.id)">削除</button> -->
					<div>
						userName:{{ message.chatPoster }}<br>
						userID:{{ message.userId }} <br>
					</div>
					<div id="message-position">
						{{ message.message }}
					</div>
					<img v-if="message.imagePath" :src="getImagePath(message.imagePath)" alt="Image">
				</div>
				<div class="bms-clear"></div>
			</div>
		</div>

		<div class="container">
			<form class="message-post" @submit.prevent="sendMessage" @keypress.enter="sendMessage">
				<b-img :src="logoPath" width="50px" class="pic-edit" @click="imageUpLoad" style="cursor: pointer;" />
				<textarea class="chat-input" placeholder="250文字まで入力できます" maxlength="250" type="text" v-model="messageBox"
					@keydown.shift.enter="newLine" ref="messageBox"></textarea>

				<b-img :src="logoPathz" width="50px" class="pic-edit" @click="sendMessage" style="cursor: pointer;" />
			</form>
		</div>

		<b-modal class="modal-position" ref="modal" title="画像編集" v-model="openimageModal" @hide="imageModal" ok-title="送信"
			cancel-title="キャンセル" @ok="sendImage">
			<image-edit-window ref="ImageEditWindow"></image-edit-window>
		</b-modal>
		<div>
			<main-navigation-bar :roomName="roomName"></main-navigation-bar>
		</div>
	</div>
</template>

<!-- eslint-disable  -->

<script>
import axios from 'axios';
import Vue from 'vue'
import ImageEditWindow from '../components/ImageEditWindow.vue'
import MainNavigationBar from '../components/MainNavigationBar.vue';
import autosize from 'autosize';
Vue.component('image-edit-window', ImageEditWindow);
Vue.component('main-navigation-bar', MainNavigationBar);
axios.defaults.baseURL = 'http://localhost:8081';

export default {
	components: {
		ImageEditWindow,
		MainNavigationBar
	},
	data() {
		return {
			messageBox: "",
			userId: '',
			userName: '',
			imagePath: '',
			messages: [],
			chatRoomId: '',
			postTime: '',
			roomName: '',
			imageKey: 0,
			openimageModal: false,
			logoPath: require('/public/assets/picture-logo.png'),
			logoPathz: require('/public/assets/send.png'),
		}
	},
	mounted() {
		this.userId = sessionStorage.getItem('userId');
		this.userName = sessionStorage.getItem('userName');
		this.chatRoomId = this.$route.params.roomId;
		this.getChatHistoryAll();
		this.getRooomName();

		const scrollBtn = this.$refs.scrollbtn;

		scrollBtn.addEventListener('click', () => {
			window.scrollTo({
				top: document.body.scrollHeight,
				behavior: 'smooth'
			});
		});
		const scrollBtnTop = this.$refs.scrollbtntop;
		scrollBtnTop.addEventListener('click', () => {
			window.scrollTo({
				top: 0,
				behavior: 'smooth'
			});
		});
		const textarea = this.$refs.messageBox;
		autosize(textarea);
		textarea.style.height = 'auto';
		textarea.style.height = textarea.scrollHeight + 'px';

		this.intervalId = setInterval(() => {
			this.getChatHistoryAll();
		}, 1000);

		this.imageEditWindow = this.$refs.imageEditWindow;

	},
	beforeDestroy() {
		const scrollBtn = this.$refs.scrollbtn;
		scrollBtn.removeEventListener('click', this.scrollToBottom);

		const scrollBtnTop = this.$refs.scrollbtntop;
		scrollBtnTop.removeEventListener('click', this.scrollToTop);

		clearInterval(this.intervalId);
	},
	methods: {
		formatDate(timeStamp) {
			const date = new Date(timeStamp);
			const year = date.getFullYear();
			const month = date.getMonth() + 1;
			const day = date.getDate();
			const hours = date.getHours();
			const minutes = date.getMinutes();
			const seconds = date.getSeconds();
			return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
		},
		newLine() {
		},
		async sendMessage(event) {
			if (!event.shiftKey) {
				try {
					const messageBox = this.messageBox;
					const response = await axios.post('/chatPagePost', {
						chatRoomId: this.chatRoomId,
						chatPoster: this.userName,
						userId: this.userId,
						message: messageBox,
						imagePath: this.imagePath
					});
					console.log(response);
					this.messageBox = "";
					this.getChatHistoryAll();
					this.$nextTick(() => {
						const textarea = this.$refs.messageBox;
						textarea.style.height = 'auto';
						textarea.style.height = textarea.scrollHeight + 'px';
					});
				} catch (error) {
					console.error(error);
				}
			}
		},
		async getChatHistoryAll() {
			try {
				const response = await axios.post('/chatPage', {
					chatRoomId: this.chatRoomId
				});
				console.log(response);
				const messages = response.data;
				this.messages = messages.map(message => {
					return {
						id: message.id,
						chatRoomId: message.chatRoomId,
						chatPoster: message.chatPoster,
						userId: message.userId,
						message: message.message,
						imagePath: message.imagePath,
						postTime: message.postTime,
						isSelf: message.userId === this.userId,
						balloonClass: message.userId === this.userId ? 'speech-balloon-right' : 'speech-balloon-left',
					}
				});
			} catch (error) {
				console.error(error);
			}
		},
		async getRooomName() {
			try {
				const response = await axios.post('/getRoomName', null, {
					params: {
						chatRoomId: this.chatRoomId
					}
				});
				this.roomName = response.data;
			} catch (error) {
				console.error(error);
			}
		},
		//削除メソッド
		//   async confirmDelete(messageId) {
		//   const confirm = window.confirm('本当にこのメッセージを削除しますか？');
		//   if (confirm) {
		//     await this.deleteMessage(messageId);
		//   }
		// },
		// async deleteMessage(messageId) {
		//   try {
		//     const response = await axios.post('/deleteMessage', {
		//       messageId: messageId
		//     });
		//     console.log(response);
		//     this.getChatHistoryAll();
		//   } catch (error) {
		//     console.error(error);
		//   }
		// },
		imageUpLoad() {
			this.$refs.modal.show();
		},
		imageModal() {
			this.$refs.modal.hide();
		},
		getImagePath(imagePath) {
			try {
				return require(`/public/chatupload/${imagePath}`);
			} catch (error) {
				console.log(error);
			}
			location.reload();
		},
		reloadImage() {
			this.imageKey += 1;
		},
		resetTextareaSize() {
			const textarea = this.$refs.messageBox;
			textarea.style.height = 'auto';
			textarea.style.height = textarea.scrollHeight + 'px';
		},
		sendImage() {
			this.$refs.ImageEditWindow.sendImage();
		},
	},

}
</script>

<style>
.chat-page {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

.pic-edit {
	margin-bottom: 40px;
	margin-left: 0.5rem;
	margin-right: 1rem;
}

.container {
	display: flex;
	align-items: center;
}

.chat-input {
	width: 50vw;
	border-radius: 10px;
	font-size: 100%;
	overflow-wrap: normal;
	margin-bottom: 0;
	word-break: break-all;
	resize: vertical;
}

.message-post {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	background: #fff;
	background-color: transparent;
}

.line-container {
	padding: 1rem;
	/* background: #7494C0; */
	overflow: hidden;
	margin: 2rem auto 0 auto;
	font-size: 80%;
	width: 69.5%;
	padding-bottom: 6.5rem;
}

.speech-balloon-left {
	position: relative;
	display: inline-block;
	margin: 1.5em 15px 1.5em 15px;
	padding: 7px 10px;
	min-width: 120px;
	max-width: 100%;
	color: #555;
	font-size: 14px;
	background: #ffffff;
	text-align: left;
	float: left;
	white-space: pre-wrap;
}

.speech-balloon-left:before {
	content: "";
	position: absolute;
	top: 50%;
	left: -30px;
	margin-top: -15px;
	border: 15px solid transparent;
	border-right: 15px solid #ffffff;
}

.speech-balloon-left p {
	margin: 0;
	padding: 0;
}

.speech-balloon-right {
	position: relative;
	display: inline-block;
	margin: 1.2em 15px 1.2em 15px;
	padding: 7px 10px;
	min-width: 120px;
	max-width: 100%;
	color: #555;
	font-size: 14px;
	background: #ffffff;
	text-align: left;
	float: right;
	white-space: pre-wrap;
	word-break: break-all;
}

.speech-balloon-right:before {
	content: "";
	position: absolute;
	top: 50%;
	left: 100%;
	margin-top: -12px;
	border: 15px solid transparent;
	border-left: 15px solid #ffffff;
}

.speech-balloon-right p {
	margin: 0;
	padding: 0;
}

.speech-balloon-left,
.speech-balloon-right {
	border-radius: 10px;
}

html {
	scroll-behavior: smooth;
}

.go-top {
	display: block;
	width: 3vw;
	height: 3vw;
	box-sizing: border-box;
	background: #FFF;
	border: 1px solid #999;
	padding-top: 30px;
	text-align: center;
	letter-spacing: -1px;
	text-decoration: none;
	color: #333;
	opacity: 2.5vw;
	position: fixed;
	top: 47%;
	right: 12%;
	z-index: 10000;
	transform: translate(50%, -50%);
}

.go-top::before {
	content: "";
	display: block;
	border-top: 2px solid #333;
	border-right: 2px solid #333;
	width: 25%;
	height: 25%;
	top: 25%;
	left: 0;
	right: 0;
	margin: auto;
	position: absolute;
	overflow: auto;
	transform: rotate(-45deg);
}

.go-top:hover {
	opacity: 1;
}

.go-down {
	display: block;
	width: 3vw;
	height: 3vw;
	box-sizing: border-box;
	background: #FFF;
	border: 1px solid #999;
	padding-top: 30px;
	text-align: center;
	letter-spacing: -1px;
	font-size: 2.5vw;
	text-decoration: none;
	color: #333;
	opacity: 2.5vw;
	position: fixed;
	top: 53%;
	right: 12%;
	z-index: 10000;
	transform: translate(50%, -50%);
}

.go-down::before {
	content: "";
	display: block;
	border-top: 2px solid #333;
	border-right: 2px solid #333;
	width: 25%;
	height: 25%;
	top: 25%;
	left: 0;
	right: 0;
	margin: auto;
	position: absolute;
	transform: rotate(135deg);
}

.go-down:hover {
	opacity: 1;
}

#message-position {
	text-align: left;
	font-size: 170%;
}

.modal-position {
	width: 50%;
	height: auto;
	text-align: center;
}

.b-modal {
	z-index: 9999;
}

.bms-clear {
	clear: both;
}

.image-editing{
    width: 10%;
    height: 10%;
}
</style> 