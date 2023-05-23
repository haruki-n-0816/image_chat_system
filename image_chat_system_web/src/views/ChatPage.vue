<template>
  <div>
    <div class="chat-title">チャットページ</div>
    <button ref="scrollbtntop" class="go-top"></button>
    <button ref="scrollbtn" class="go-down"></button>

    <div class="line-container">
      <div v-for="message in messages" :key="message.id">
        <div class="speech-balloon" :class="message.balloonClass">
          {{ formatDate(message.postTime) }}
          <div class="says">
            userID:{{ message.userId }} userName:{{ message.chatPoster }} <br>
          </div>
          <div id="message-position">
            {{ message.message }}
          </div>
          <!-- <img v-if="message.imagePath" :src="require(`@/assets/${message.imagePath}`)"  alt="Image"> -->
          <img v-if="message.imagePath" :src="getImagePath(message.imagePath)" alt="Image">
          <!-- <img :src="getImagePath()" alt="Image"> -->
          <!-- {{ message.imagePath }} -->
          <button @click="confirmDelete(message.id)">削除</button>
        </div>
        <div class="bms-clear"></div>
      </div>

    </div>
    <form class="message-post" @submit.prevent="sendMessage" @keypress.enter="sendMessage">
      <textarea class="chat-input" placeholder="250文字まで入力できます" maxlength="250" type="text" v-model="messageBox"
        @keydown.shift.enter="newLine"></textarea>
      <b-button @click="imageUpLoad">画像</b-button>
      <button type="submit">送信</button>
    </form>

    <b-modal class="modal-position" ref="modal" title="画像編集" v-model="openimageModal" @hide="imageModal" ok-title="送信" cancel-title="キャンセル">
      <image-edit-window></image-edit-window>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';

import Vue from 'vue'
import ImageEditWindow from '../components/ImageEditWindow.vue'
Vue.component('image-edit-window', ImageEditWindow);

export default {
  components: {
    ImageEditWindow
  },
  data() {
    return {
      messageBox: [],
      userId: '',
      userName: '',
      imagePath: '',
      messages: [],
      chatRoomId: '',
      postTime: '',
      openimageModal: false,
    }
  },
  mounted() {
    this.userId = this.$store.getters.userId;
    this.userName = this.$store.getters.userName;
    this.chatRoomId = this.$route.params.roomId;
    this.getChatHistoryAll();

    // スクロールボタンを取得する
    const scrollBtn = this.$refs.scrollbtn;

    // スクロールボタンがクリックされたときに最下部にスクロールする
    scrollBtn.addEventListener('click', () => {
      window.scrollTo({
        top: document.body.scrollHeight,
        behavior: 'smooth'
      });
    });
    const scrollBtnTop = document.querySelector('.go-top');
    scrollBtnTop.addEventListener('click', () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    });

    // ○秒ごとにチャット履歴を取得するために、setInterval()関数を使用する
    this.intervalId = setInterval(() => {
      this.getChatHistoryAll();
    }, 1000);
  },
  // コンポーネントが破棄される前に実行される「beforeDestroy」,インターバル処理を停止することができる
  beforeDestroy() {
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
    async confirmDelete(messageId) {
      const confirm = window.confirm('本当にこのメッセージを削除しますか？');
      if (confirm) {
        await this.deleteMessage(messageId);
      }
    },
    async deleteMessage(messageId) {
      try {
        const response = await axios.post('/deleteMessage', {
          messageId: messageId
        });
        console.log(response);
        this.getChatHistoryAll();
      } catch (error) {
        console.error(error);
      }
    },
    // async submitImageData() {

    // },
    imageUpLoad() {
      this.$refs.modal.show();
    },
    imageModal() {
      this.$refs.modal.hide();
    },
    getImagePath(imagePath) {
      try{
        // alert(`@/assets/${imagePath}`);
        return require(`@/assets/${imagePath}`);
      } catch (error) {
        // alert(error);
      }
      location.reload();
    }
  }
}
</script>

<style>
.line-container {
  padding: 0;
  background: #7494c0;
  overflow: hidden;
  margin: 30px auto;
  font-size: 80%;
  width: 70%;
}

.speech-balloon-left {
  position: relative;
  display: inline-block;
  margin: 1.5em 15px 1.5em 15px;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #e0edff;
  text-align: left;
  float: left;
  white-space: pre-wrap;
  word-break: break-all;
}

.speech-balloon-left:before {
  content: "";
  position: absolute;
  top: 50%;
  left: -30px;
  margin-top: -15px;
  border: 15px solid transparent;
  border-right: 15px solid #e0edff;
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
  font-size: 16px;
  background: #e0edff;
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
  margin-top: -15px;
  border: 15px solid transparent;
  border-left: 15px solid #e0edff;
}

.speech-balloon-right p {
  margin: 0;
  padding: 0;
}

.chat-title {
  background-color: #fff;
  border: 1px solid #ef858c;
  border-right: 20px solid #ef858c;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, .1);
  color: #ef858c;
  padding: 10px 20px;
  position: relative;
  margin: 30px auto;
  font-size: 250%;
  width: 60vw;
  text-align: center;
}

.chat-title:after {
  box-shadow: 0 15px 10px rgba(0, 0, 0, .1);
  content: '';
  position: absolute;
  transform: rotate(1deg);
  -moz-transform: rotate(1deg);
  -webkit-transform: rotate(1deg);
  -o-transform: rotate(1deg);
  bottom: 15px;
  right: -3px;
  width: 85%;
  height: 10px;
  z-index: -1;
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

.chat-input {
  width: 60vw;
  max-height: 3em;
  border-radius: 10px;
  font-size: 100%;
  resize: none;
  overflow-wrap: normal;
  word-break: break-all;
}

.message-post {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.message-position {
  text-align: left;
  color: #ef858c;
}

.modal-position{
  width: 50%;
  height: auto;
  text-align: center;
}

.bms-clear {
  clear: both;
  /* 左メッセージと右メッセージの回り込み(float)の効果の干渉を防ぐために必要（これが無いと横になる) */
}
</style> 