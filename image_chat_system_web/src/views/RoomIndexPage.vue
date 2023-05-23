<template>
  <div>
    <navbar></navbar>
    <div class="add-room">
      <b-img :src="logoPath" width="100px" class="add-button"  @click="showAddGroupPopup = true" style="cursor: pointer;"/>
      <b-modal v-model="showAddGroupPopup" title="新規部屋作成" hide-footer>
        <div class="popup">
         <b-form-input v-model="roomName" placeholder="10文字以内で入力してください"></b-form-input> 
          <div>
            <b-button variant="primary" @click="addGroup">追加する</b-button>
            <b-button variant="secondary" @click="showAddGroupPopup = false">閉じる</b-button>
          </div>
        </div>
      </b-modal>
    </div>

    <div>
      <h1 id="chatroom-header">ChatRoom</h1>    
      <div v-if="rooms.length > 0" class="room-list">
        <h2 class="room-text">入室可能な部屋はこちら！</h2>  
      </div> 
        <h2 v-else class="room-text">いまは部屋がありません...</h2>
    
      <div>
        <div v-for="chatIndex in rooms" :key="chatIndex.roomId" class="room-list-item">
          <router-link :to="{ path: '/chatpage/' + chatIndex.roomId }" class="room-link">
            <div @click="storeRoomName(chatIndex.roomName)">
              {{ chatIndex.roomName + "の部屋" }}
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  axios.defaults.baseURL = 'http://localhost:8081';

  import Vue from 'vue';

  import Navbar from '../components/Navbar.vue';
  Vue.component('navbar', Navbar);

  export default {
    components: {
        Navbar
      },
    data() {
      return {
        rooms: [],
        roomName: "",
        showAddGroupPopup: false,
        showLogoutPopup: false,
        logoPath: require('@/assets/ROOM2.png')
      };
    },
    mounted() {
      this.showAll();
          // ○秒ごとにチャット履歴を取得するために、setInterval()関数を使用する
      this.intervalId = setInterval(() => {
        this.showAll();
      }, 1000);
    },
      // コンポーネントが破棄される前に実行される「beforeDestroy」,インターバル処理を停止することができる
    beforeDestroy() {
      clearInterval(this.intervalId);
    },
    methods: {
      async showAll() {
        try {
          const response = await axios.get("/roomIndex");
          this.rooms = response.data;
        }
        catch (error) {
          console.log(error);
        }
      },

    async addGroup() {
      if (this.roomName.length > 10) {
        alert('部屋名は10文字以内で入力してください');
        return;
      }
      try {
        const response = await axios.post('/chatIndex', {
          roomName: this.roomName
        });
        if (response.data === true) {
          location.reload();
        } else {
          alert('既に登録されている部屋です');
        }
      } catch (error) {
        console.error(error);
      }
    },    
    async storeRoomName(roomName) {
      this.roomName = roomName;
      this.$store.dispatch('setRoomName', roomName);
    }
  },
};
</script>

<style>
  .room-list-item {
    margin-bottom: 1em;
    text-align: center;
    position: relative;
  }

  @media (min-width: 768px) {
    .room-list-item {
      margin-left: 20%;
      margin-right: 20%;
      min-width: 30%;
    }
  }

  @media (max-width: 767px) {
    #chatroom-header {
      top: 3.5rem; /* Adjust the value for mobile devices */
    }
    .room-list-item {
      margin-left: 20%;
      margin-right: 20%;
      min-width: 50%;
    }
  }

  .icon {
    text-align: right;
    padding: 0 40px 0 0;
  }

  .center {
    text-align: center;
  }

  h1 {
    text-align: center;
  }

  .popup {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .popup input {
    margin-bottom: 10px;
  }

  .room-list {
    padding: 0;
    text-align: center;
  }

  .room-link {
    display: inline-block;
    padding: 0.5em 2em;
    background-color: #f2f2f2;
    border-radius: 5px;
    text-decoration: none;
    color: #333;
    transition: background-color 0.3s ease;
    width: 100%;
  }

  .room-link:hover {
    background-color: #e6e6e6;
  }

  .room-text{
    font-size: 1.2em;
    margin-top: 1rem;
    margin-bottom: 1rem;
    color:#f2f2f2;
    top: 5rem; /* Adjust the value to match the height of your navbar */
    left: 0;
    width: 100%;
    color: #e6e6e6;
    z-index: 998; /* Set a lower z-index than the navbar */
    text-align: center;
  }

  .add-button {
    padding-left: 30px;
    position: fixed;
    top: 6rem;
    right: 0;
    z-index: 999;
    margin-right: 20%;
  }

  .add-room{
    padding-top: 5rem;
  }

  #chatroom-header {
    top: 5rem; /* Adjust the value to match the height of your navbar */
    left: 0;
    width: 100%;
    color: #e6e6e6;
    z-index: 998; /* Set a lower z-index than the navbar */
  }

  .create-text{
    text-align: right;
    margin-top: 5rem;
    color: #f2f2f2;
  }
</style>
