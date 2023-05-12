<template>
  <div>
    <navbar></navbar>
    <h1 class="text-center">ChatRoom</h1>

    <!-- <div class="d-flex justify-content-end">
      <b-img id="icon" :src="iconPath" alt="Logout" @click="showLogoutPopup = true" />
      <b-modal v-model="showLogoutPopup" hide-footer>
        <div class="popup">
          <p>ログインしているユーザー名: {{ this.$store.getters.userName }}</p>
          <p>ログインしているユーザーID: {{ this.$store.getters.userId }}</p>
          <p>ログアウトしますか？</p>
          <b-button variant="danger" @click="logout">ログアウト</b-button>
          <b-button variant="secondary" @click="showLogoutPopup = false">キャンセル</b-button>
        </div>
      </b-modal>
    </div> -->

    <div class="center" @submit.prevent>
      <ul v-if="rooms.length > 0">
        <div>いま入れる部屋はこちら！</div>
        <li v-for="chatIndex in rooms" :key="chatIndex.roomId">
          <router-link :to="{ path: '/chatpage/' + chatIndex.roomId }">{{ chatIndex.roomName + "の部屋" }}</router-link>
        </li>
      </ul>
      <div v-else>いまは部屋がありません...</div>
    </div>
    <b-button variant="primary" id="addButton" @click="showAddGroupPopup = true">部屋を追加する</b-button>
    <b-modal v-model="showAddGroupPopup" hide-footer>
      <div class="popup">
        <h3>グループ名を記入してください:</h3>
        <b-form-input v-model="roomName"></b-form-input>
        <div>
          <b-button variant="primary" @click="addGroup">追加する</b-button>
          <b-button variant="secondary" @click="showAddGroupPopup = false">閉じる</b-button>
        </div>
      </div>
    </b-modal>
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
      // chatIndexes: [],
      showLogoutPopup: false,
      iconPath: require('@/assets/icon2.jpeg')
    };
  },
  mounted() {
    this.showAll();
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
    async logout() {
      try {
        this.$store.dispatch('clearUserData');
        this.$router.push('/login');
      } catch (error) {
        console.error(error);
      }
    },
    async addGroup() {
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
  },
};
</script>

<style>
li {
  list-style: none
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
</style>