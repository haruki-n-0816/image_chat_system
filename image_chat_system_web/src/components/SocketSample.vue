<template>
    <div>
        <input v-model="message" type="text" placeholder="メッセージを入力">
        <button @click="sendMessage">送信</button>
        <ul>
            <li v-for="(msg, index) in messages" :key="index">{{ msg }}</li>
        </ul>
    </div>
</template>

<script>
import io from 'socket.io-client';

export default {
    data() {
        return {
            message: '',
            messages: []
        };
    },
    created() {
        const socket = io('http://localhost:8081'); 
        socket.on('connect', () => {
            console.log('Connected to server');
        });
        socket.on('/topic/messages', (message) => {
            this.messages.push(message);
        });
    },
    methods: {
        sendMessage() {
            const socket = io('http://localhost:8081');
            socket.emit('/app/message', this.message);
            this.message = '';
        }
    }
};
</script>