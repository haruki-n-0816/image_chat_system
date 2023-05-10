<template>
  <div>
    <h1>ChatRoom</h1>

    <div id="icon">
      <input type="image"
        src="data:image/png;base64,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"
        style="width: 40px; height: 40px;" alt="Logout" @click="showLogoutPopup = true" />
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
      <ul v-if="chatIndexes.length > 0">
        <div>いま入れる部屋はこちら！</div>
        <li v-for="chatIndex in chatIndexes" :key="chatIndex.roomId">
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
      chatIndexes: [],
      showLogoutPopup: false,
    };
  },
  mounted() {
    this.showAll();
  },
  methods: {
    async showAll() {
      try {
        const response = await axios.get("/roomIndex");
        this.chatIndexes = response.data;
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
      if (groupName) {
        this.roomName = "";
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
      }
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
  /* text-align: right; */
  float: right;
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