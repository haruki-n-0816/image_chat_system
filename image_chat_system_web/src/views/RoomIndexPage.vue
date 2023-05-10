<template>
  <div>
    <h1>ChatRoom</h1>

    <div >
      <input id="icon" type="image" :src="iconPath" style="width: 70px; height: 70px;" alt="Logout" @click="showLogoutPopup = true" />
      <div v-if="showLogoutPopup" class="popup-background">
        <div class="popup-container">
          <div class="popup">
            <p>ログインしているユーザー名: {{ this.$store.getters.userName }}</p>
            <p>ログインしているユーザーID: {{ this.$store.getters.userId }}</p>
            <p>ログアウトしますか？</p>
            <button @click="logout">ログアウト</button>
            <button @click="showLogoutPopup = false">キャンセル</button>
          </div>
        </div>
      </div>
    </div>

    <div class="center" @submit.prevent>
      <ul v-if="rooms.length > 0">
        <div>いま入れる部屋はこちら！</div>
        <li v-for="chatIndex in rooms" :key="chatIndex.roomId">
          <router-link :to="{ path: '/chatpage/' + chatIndex.roomId }">{{ chatIndex.roomName + "の部屋" }}</router-link>
        </li>
      </ul>
      <div v-else>いまは部屋がありません...</div>
    </div>
    <button @click="showAddGroupPopup = true">部屋を追加する</button>
    <div v-if="showAddGroupPopup">
      <h3>グループ名を記入してください:</h3>
      <input type="text" v-model="roomName">
      <button @click="addGroup">追加する</button>
      <button @click="showAddGroupPopup = false">閉じる</button>
    </div>
  </div>
</template>
 
<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';

export default {
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
      const groupName = this.roomName;
        try {
          const response = await axios.get('/roomIndex');
          const existingRooms = response.data;
          const roomExists = existingRooms.some(room => room.roomName === groupName);
          //someメソッド...指定された関数で実装されているテストに、配列の中の少なくとも 1 つの要素が 合格するかどうかを判定します。
          //配列の中で指定された関数が true を返す要素を見つけた場合は true を返し、そうでない場合は false を返します。それ以外の場合は false を返します。配列は変更しません。
          if (!roomExists) {
            const newRoom = {
              roomName: groupName
            };
            const createResponse = await axios.post('/chatindex', newRoom);
            console.log(createResponse.data);
            location.reload();
          } else {
            alert("その名前の部屋はすでに存在します。");
          }
        } catch (error) {
          console.log(error);
        }
        this.showAddGroupPopup = false;
    },
  },
  // components: { router }
};
</script>

<style>
li {
  list-style: none
}

ul {
  padding-left: 0
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