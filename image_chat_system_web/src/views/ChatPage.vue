<template>
  <div>
    <div class="line-container">
      <div class="h2-a">チャットページ</div>

      <div v-for="message in messages" :key="message.id">
        <div class="balloon" :class="message.balloonClass">
          {{ formatDate(message.post_time) }}
          <!-- <div class="balloon1-right" v-if="!message.isSelf"></div>
          <div class="balloon1-left" v-else> -->
          <div class="says">
            userID:{{ message.userId }} userName: {{ message.chat_poster }} <br>
            {{ message.message }}
          </div>
          <!-- {{ formatDate(message.post_time) }} -->
        </div>

        <div class="bms-clear"></div>
      </div>


    </div>
    <form @submit.prevent="sendMessage">
      <input type="text" v-model="message">
      <button type="submit">送信</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      message: '',
      userId: '',
      userName: '',
      imagePath: '',
      messages: [],
      chat_room_id: '',
      post_time: '',
    }
  },

  // Vueインスタンスが生成された直後に呼ばれるメソッド
  mounted() {
    this.userId = this.$store.getters.userId;
    this.userName = this.$store.getters.userName;
    axios.defaults.baseURL = 'http://localhost:8081';
    this.chat_room_id = this.$route.params.roomId;
    this.getChatHistoryAll();
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
    formatDate(timestamp) {
      const date = new Date(timestamp);
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
        const response = await axios.post('/chatpagepost', {
          chat_room_id: this.chat_room_id,
          chat_poster: this.userName,
          userId: this.userId,
          message: this.message,
          imagePath: this.imagePath
        });
        console.log(response);
        this.$store.dispatch('setAuthentication', response.data.result);
        this.message = '';    //ボックスをからにする
        // alert(this.userName + '\n' + this.message + '\n' + this.chat_room_id);
        this.getChatHistoryAll();
      } catch (error) {
        alert("エラー出てるよ！")
        console.error(error);
      }
    },
    async getChatHistoryAll() {
      console.log(this.chat_room_id);
      this.message = []
      try {
        const response = await axios.post('/chatPage', {
          chat_room_id: this.chat_room_id
        });
        console.log(response);
        const messages = response.data;
        this.messages = messages.map(message => {
          return {
            id: message.id,
            chat_room_id: message.chat_room_id,
            chat_poster: message.chat_poster,
            userId: message.userId,
            message: message.message,
            image_path: message.image_path,
            post_time: message.post_time,
            isSelf: message.userId === this.userId,
            balloonClass: message.userId === this.userId ? 'balloon1-right' : 'balloon1-left',
          }
        });
      } catch (error) {
        alert("エラー出とるやんけ！" + '\n' + this.chat_room_id)
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

.balloon1-left {
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

.balloon1-left:before {
  content: "";
  position: absolute;
  top: 50%;
  left: -30px;
  margin-top: -15px;
  border: 15px solid transparent;
  border-right: 15px solid #e0edff;
}

.balloon1-left p {
  margin: 0;
  padding: 0;
}

.balloon1-right {
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

.balloon1-right:before {
  content: "";
  position: absolute;
  top: 50%;
  left: 100%;
  margin-top: -15px;
  border: 15px solid transparent;
  border-left: 15px solid #e0edff;
}

.balloon1-right p {
  margin: 0;
  padding: 0;
}

.h2-a {
  background-color: #fff;
  /* 背景色 */
  border: 1px solid #ef858c;
  /* 枠線 */
  border-right: 20px solid #ef858c;
  /* 右側の太い線 */
  box-shadow: 1px 1px 1px rgba(0, 0, 0, .1);
  color: #ef858c;
  /* 文字色 */
  padding: 10px 20px;
  /* 上下・左右の余白 */
  position: relative;
  font-size:40px
}

.h2-a:after {
  box-shadow: 0 15px 10px rgba(0, 0, 0, .1);
  /* 付箋の影 */
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

.bms-clear {
  clear: both;
  /* 左メッセージと右メッセージの回り込み(float)の効果の干渉を防ぐために必要（これが無いと横になる */
}

</style> 