<template>
  <div>
    <div class="line-container">
      <div class="chat-title">チャットページ</div>
      <button ref="scrollbtntop" class="go-top"></button>
      <button ref="scrollbtn" class="go-down"></button>

      <div v-for="message in messages" :key="message.id">
        <div class="speech-balloon" :class="message.balloonClass">
          {{ formatDate(message.postTime) }}
          <div class="says">
            userID:{{ message.userId }} userName:{{ message.chatPoster }} <br>
            {{ message.message }}
          </div>
          <button @click="confirmDelete(message.id)">削除</button>
        </div>
        <div class="bms-clear"></div>
      </div>

    </div>
    <form class="message-post" @submit.prevent="sendMessage">
      <input class="chat-input" type="text" v-model="messageBox">
      <button type="submit">送信</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';

export default {
  data() {
    return {
      messageBox: '',
      userId: '',
      userName: '',
      imagePath: '',
      messages: [],
      chatRoomId: '',
      postTime: '',
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
    const scrollBtnTop = this.$refs.scrollbtntop;
    scrollBtnTop.addEventListener('click', () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    });

    // ○秒ごとにチャット履歴を取得するために、setInterval()関数を使用する
    this.intervalId = setInterval(() => {
      this.getChatHistoryAll();
    }, 10000);
  },
  // コンポーネントが破棄される前に実行される「beforeDestroy」,インターバル処理を停止することができる、これがないとメモリリークのリスクが低い
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

    async sendMessage() {   //ここから送信してDBに登録するためのメソッド
      try {
        const response = await axios.post('/chatPagePost', {
          chatRoomId: this.chatRoomId,
          chatPoster: this.userName,
          userId: this.userId,
          message: this.messageBox,
          imagePath: this.imagePath
        });
        console.log(response);
        this.messageBox = '';    //ボックスをからにする
        this.getChatHistoryAll();
      } catch (error) {
        console.error(error);
      }
    },

    async getChatHistoryAll() {
      this.messages = []
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
    }
  }
}
</script>

<style>
.line-container {
  padding: 0;
  background: #7494c0;
  overflow: hidden;
  max-width: 500px;
  margin: 30px auto;
  font-size: 80%;
  overflow: hidden;
}

.speech-balloon-left {
  position: relative;
  display: inline-block;
  margin: 1.5em 0 1.5em 15px;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #e0edff;
  text-align: left;
  float: left;
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

.bspeech-balloon-left p {
  margin: 0;
  padding: 0;
}

.speech-balloon-right {
  position: relative;
  display: inline-block;
  margin: 1.5em 15px 1.5em 0;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #e0edff;
  text-align: right;
  float: right;
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
  font-size: 40px
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
  width: 60px;
  height: 60px;
  box-sizing: border-box;
  background: #FFF;
  border: 1px solid #999;
  padding-top: 30px;
  text-align: center;
  letter-spacing: -1px;
  font-size: 85%;
  text-decoration: none;
  color: #333;
  opacity: 0.5;
  position: fixed;
  top: 47%;
  right: 43px;
  z-index: 10000;
  overflow: auto;
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

@media(max-width:750px) {
  .go-top {
    width: 40px;
    height: 40px;
    text-indent: -9999px;
    opacity: 1;
    border: none;
    background: none;
    bottom: 10px;
    right: 10px;
  }

  .go-top::before {
    bottom: 0;
    left: 50%;
    right: auto;
    transform: rotate(45deg);
  }
}

@media (min-width: 751px) and (max-width: 1200px) {
  .go-top {
    width: 50px;
    height: 50px;
    bottom: 15px;
    right: 15px;
  }
}

@media (min-width: 1201px) {
  .go-top {
    width: 60px;
    height: 60px;
    bottom: 20px;
    right: 20px;
  }
}

.go-down {
  display: block;
  width: 60px;
  height: 60px;
  box-sizing: border-box;
  background: #FFF;
  border: 1px solid #999;
  padding-top: 30px;
  text-align: center;
  letter-spacing: -1px;
  font-size: 85%;
  text-decoration: none;
  color: #333;
  opacity: 0.5;
  position: fixed;
  top: 53%;
  right: 100px;
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

@media(max-width:750px) {
  .go-down {
    width: 40px;
    height: 40px;
    text-indent: -9999px;
    opacity: 1;
    border: none;
    background: none;
    bottom: 10px;
    right: 10px;
  }

  .go-down::before {
    bottom: 0;
    left: 50%;
    right: auto;
    transform: rotate(45deg);
  }
}

@media (min-width: 751px) and (max-width: 1200px) {
  .go-down {
    width: 50px;
    height: 50px;
    bottom: 15px;
    right: 15px;
  }
}

@media (min-width: 1201px) {
  .go-down {
    width: 60px;
    height: 60px;
    bottom: 20px;
    right: 20px;
  }
}

.chat-input{
  width: 30%;
  height: 40px;
  border-radius: 10px;
}

.message-post {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.bms-clear {
  clear: both;
  /* 左メッセージと右メッセージの回り込み(float)の効果の干渉を防ぐために必要（これが無いと横になる */
}
</style> 