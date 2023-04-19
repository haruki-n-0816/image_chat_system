<template>
  <div class="container">
    <div class="chat-box" ref="chatBox">
      <div v-for="(chatMessage, index) in chatMessages" :key="index" class="chat-message">
        <div class="chat-message-text">
          <div>{{ chatMessage.chat_poster }}</div>
          <div>{{ chatMessage.message }}</div>
          <div class="chat-message-time">{{ chatMessage.post_time }}</div>
        </div>
      </div>
    </div>
    <form class="chat-form" @submit.prevent="sendMessage">
      <input type="text" placeholder="メッセージを入力してください" v-model="message">
      <button type="submit">送信</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chat_poster: 'ユーザー名',
      message: '',
      chatMessages: [],
      post_time:''
    }
  },
  methods: {
    sendMessage() {
      if (this.message) {
        const time = new Date().toLocaleTimeString();
        const chatMessage = {
          schat_poster: this.schat_poster,
          message: this.message,
          post_time: this.post_time,
        };
        this.chatMessages.push(chatMessage);
        this.$refs.chatBox.scrollTop = this.$refs.chatBox.scrollHeight; // メッセージ送信後、自動でスクロールする
        this.message = ''; // メッセージをリセットする
        this.$socket.send(JSON.stringify(chatMessage)); // WebSocketを使用してメッセージを送信する
      }
    },
  },
  created() {
    this.$socket.onmessage = (event) => {
      const chatMessage = JSON.parse(event.data);
      this.chatMessages.push(chatMessage);
      this.$refs.chatBox.scrollTop = this.$refs.chatBox.scrollHeight; // メッセージ受信後、自動でスクロールする
    };
  },
}
</script>

<style>
.container {
  height: 400px;
  width: 600px;
  margin: 30px;
  padding: 20px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
}
.chat-box {
  height: 300px;
  overflow-y: scroll;
  margin-bottom: 10px;
}
.chat-message {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 10px;
}
    .chat-message-text {
      padding: 5px 10px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 1px 1px 2px #ccc;
      margin-right: 10px;
    }
    .chat-message-time {
      font-size: 12px;
      color: #999;
    }
    .chat-form {
      display: flex;
    }
    .chat-form input[type="text"] {
      flex: 1;
      padding: 10px;
      border-radius: 5px;
      border: none;
      margin-right: 10px;
      word-break: break-all;
    }
    .chat-form button[type="submit"] {
      padding: 10px;
      border-radius: 5px;
      border: none;
      background-color: #007bff;
      color: #fff;
    }
</style>