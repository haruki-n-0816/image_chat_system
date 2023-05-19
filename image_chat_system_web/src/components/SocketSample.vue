<template>
    <div>
        <h2>Socket Communication</h2>
        <div>
            <textarea v-model="message"></textarea>
            <button @click="sendMessage">Send</button>
        </div>
        <ul>
            <li v-for="msg in messages" :key="msg.id">{{ msg }}</li>
        </ul>
    </div>
</template>
  
<script>
import io from 'socket.io-client';

export default {
    data() {
        return {
            message: '',
            messages: [],
        };
    },
    mounted() {
        // ソケット接続の設定
        const socket = io('http://localhost:8081'); // サーバーのURLに合わせて変更してください

        // サーバーからのメッセージ受信時の処理
        socket.on('message', (data) => {
            this.messages.push(data);
        });
    },
    methods: {
        sendMessage() {
            // サーバーにメッセージを送信
            const socket = io('http://localhost:8081'); // サーバーのURLに合わせて変更してください
            socket.emit('message', this.message);
            this.message = '';
        },
    },
};
</script>