<template>
  <div>
    <div class="line__container">
      <h2>チャットページ</h2>
      <div class="line__contents scroll">
        <div v-for="message in messages" :key="message.id">
          {{ message }}
        </div>
      </div>

        <!-- 相手の吹き出し -->
        <div class="balloon1-left" style="text-align: left;">
          <div class="name">name:次郎１</div>
          <div class="text">こんにちは</div>
        </div>

        <div class="bms_clear"></div>


        <!-- 自分の吹き出し -->
        <div class="balloon1-right">
          <p>userId:{{ userId }} 
            userName:{{ userName }}
            部屋番号:{{ chat_room_id }}</p>
          <div class="text">hello!</div>
        </div>
        <div class="bms_clear"></div>

      </div>
      <form @submit.prevent="sendMessage">
        <input type="text" v-model="message">
        <button type="submit">送信</button>
        <!-- <span class="date">{{ formatDate(timestamp) }}</span> -->
      </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      message: '',
      userId: '',
      userName: '',
      image_path: '',
      messages: [],
      chat_room_id:'',
    }
  },

  // Vueインスタンスが生成された直後に呼ばれるメソッド
  created() {
    this.userId = this.$store.getters.userId;
    this.userName = this.$store.getters.userName;
    axios.defaults.baseURL = 'http://localhost:8081';
    this.getChatHistoryAll();
    this.chat_room_id = this.$route.params.roomId;
  },
  
  methods: {
  //   formatDate(timestamp) {
  //     const date = new Date(timestamp);
  //     const year = date.getFullYear();
  //     const month = date.getMonth() + 1;
  //     const day = date.getDate();
  //     const hours = date.getHours();
  //     const minutes = date.getMinutes();
  //     const seconds = date.getSeconds();
  //     return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  //   },

    // async fetchData() {
    //   try {
    //     const response = await axios.get('/data');
    //     this.timestamp = response.data.timestamp;
    //     alert(this.timestamp)
    //   } catch (error) {
    //     alert("タイム取れませーーーーん！")
    //     this.$store.dispatch('setError', 'タイム取得に失敗しました。');
    //     console.error(error);
    //   }
    // },

    async sendMessage() {   //ここから送信してDBに登録するためのメソッド
      try {
        const response = await axios.post('/chatpage', {
          chat_room_id:this.chat_room_id,
          chat_poster:this.userName,
          userId:this.userId,
          message:this.message,
          image_path:this.image_path
        });
        console.log(response);
        //const { userId, userName } = response.data;
        this.$store.dispatch('setAuthentication', response.data.result);
        this.message = '';    //ボックスをからにする
        alert(this.userName + '\n' + this.message + '\n' + this.chat_room_id);
      } catch (error) {
        alert("エラー出てるよ！")
        console.error(error);
      }
    },
    async getChatHistoryAll(){
      alert("①はおけ")
      try{
        alert("出とるやんけいえーい！")
        const response = await axios.post('/chatpagenext',{
          chat_room_id:this.chat_room_id
        });
        console.log(response);
        this.messages = response.data;
      } catch (error) {
        alert("エラー出とるやんけ！"+ '\n'+this.chat_room_id)
        console.error(error);
      }
    }
  }
}
</script>

<style>
.line__container {
  padding: 0;
  background: #7494c0;
  overflow: hidden;
  max-width: 500px;
  margin: 20px auto;
  font-size: 80%;
}

.balloon1-left {
  position: relative;
  display: inline-block;
  margin: 1.5em 0 1.5em 15px;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #e0edff;
  text-align: left;
  float: left; 
}

.balloon1-left:before {
  content: "";
  position: absolute;
  top: 50%;
  left: -30px;
  margin-top: -15px;
  border: 15px solid transparent;
  border-right: 15px solid #e0edff;
}

.balloon1-left p {
  margin: 0;
  padding: 0;
}

.balloon1-right {
  position: relative;
  display: inline-block;
  margin: 1.5em 15px 1.5em 0;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #e0edff;
  text-align: right;
  float: right; 
}

.balloon1-right:before {
  content: "";
  position: absolute;
  top: 50%;
  left: 100%;
  margin-top: -15px;
  border: 15px solid transparent;
  border-left: 15px solid #e0edff;
}

.balloon1-right p {
  margin: 0;
  padding: 0;
}

h2 {
	background-color: #fff; /* 背景色 */
	border: 1px solid #ef858c; /* 枠線 */
	border-right: 20px solid #ef858c; /* 右側の太い線 */
	box-shadow: 1px 1px 1px rgba(0,0,0,.1);
	color: #ef858c; /* 文字色 */
	padding: 10px 20px; /* 上下・左右の余白 */
	position:relative;
}
h2:after {
	box-shadow: 0 15px 10px rgba(0, 0, 0, .1); /* 付箋の影 */
	content: '';
	position: absolute;
	transform: rotate(1deg);
	-moz-transform: rotate(1deg);
	-webkit-transform: rotate(1deg);
	-o-transform: rotate(1deg);
	bottom: 15px;
	right: -3px;
	width: 85%;
	height: 10px;
	z-index: -1;
}

.bms_clear {
        clear: both; /* 左メッセージと右メッセージの回り込み(float)の効果の干渉を防ぐために必要（これが無いと、自分より下のメッセージにfloatが影響する） */

    }

</style> 