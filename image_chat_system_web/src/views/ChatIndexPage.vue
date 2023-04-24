<template>
  <div>
    <h1>CreateChatRoom</h1>
    <button @click="showAddGroupPopup = true">部屋を追加する</button>
    <div v-if="showAddGroupPopup" class="popup-background">
      <div class="popup-container">
        <div class="popup">
          <h2>グループ名を記入してください:</h2>
          <input type="text" v-model="roomName">
          <button @click="addGroup">追加する</button>
          <button @click="showAddGroupPopup = false">閉じる</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
axios.defaults.baseURL='http://localhost:8081';
export default {
  data() {
    return {
      rooms: [],
      roomName: "",
      showAddGroupPopup: false,
    };
  },

  methods: {
    async addGroup() {
      const groupName = this.roomName;
      if (groupName) {
        this.roomName = ""; // テキストボックスをクリアする
        try {
          const response = await axios.post('/chatindex', {
            roomName: groupName // room_nameから変更
          });
          console.log(response.data);
        } catch (error) {
          console.log(error);
        }
        this.showAddGroupPopup = false;
      }
    },
  }
}
</script>
<style>
.popup-background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}
.popup-container {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
}
.popup {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.popup input {
  margin-bottom: 10px;
}
</style>

