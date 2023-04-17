<template>
  <body>
    <div class="container">
      <div class="chat-box"></div>
      <form class="chat-form">
        <input type="text" placeholder="メッセージを入力してください">
        <button type="submit">送信</button>
      </form>
    </div>
  </body>
</template>

<script>
export default {
  mounted() {
    const form = this.$el.querySelector('.chat-form');
    const input = this.$el.querySelector('input[type="text"]');
    const chatBox = this.$el.querySelector('.chat-box');
    const sender = "ユーザー名";
    form.addEventListener('submit', (e) => {
      e.preventDefault();
      const message = input.value.trim(); // trim()を使って、空白を削除する
      if (message) { 
        const time = new Date().toLocaleTimeString();
        const chatMessage = `
          <div class="chat-message">
            <div class="chat-message-sender">${sender}</div>
            <div class="chat-message-text">${message}</div>
            <div class="chat-message-time">${time}</div>
          </div>
        `;
        chatBox.innerHTML += chatMessage;
        input.value = '';
      }
    });
  }
}
</script>

<style>
    .container {
      height: 400px auto;
      width: 600px auto;
      margin: 30px;
      padding: 20px;
      background-color: #f0f0f0;
      border: 1px solid #ccc;
    }
    .chat-box {
      height: 800px;
      overflow-y: scroll;
      margin-bottom: 20px;
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
