<template>
    <div>
      <div id="state" style="border: 1px solid #000; width: 10rem;"></div>
      <div>
        <input id="message" type="text" style="width: 20rem;">
        <input id="send" type="button" value="送信"/>
      </div>
      <ul id="list"></ul>
    </div>
  </template>
  
  <script>
  export default {
    mounted() {
      // Web Socketの生成
      const webSocket = new WebSocket("ws://127.0.0.1/SampleServer/sample");
  
      // 接続成功時
      webSocket.onopen = () => {
        document.getElementById("state").innerText = "接続成功";
        // サーバーからのメッセージを受信した時の処理
        webSocket.onmessage = (event) => {
          const li = document.createElement("li");
          li.innerText = event.data;
          document.getElementById("list").appendChild(li);
        }
      };
  
      // 接続エラー時
      webSocket.onerror = () => {
        document.getElementById("state").innerText = "接続失敗";
      };
  
      // 送信ボタンがクリックされた時の処理
      document.getElementById("send").addEventListener("click", () => {
        // サーバーにメッセージを送信
        const inputElement = document.getElementById('message');
        webSocket.send(inputElement.value);
        inputElement.value = "";
      });
    }
  }
  </script>
  