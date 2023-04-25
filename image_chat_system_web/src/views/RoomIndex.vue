<template>
  <div>
    <h1>ChatRoom</h1>
    <ul>
      <li v-for="chatIndex in chatIndexes" :key="chatIndex.roomId">
        <div><router-link :to="{ path: '/chat/' + chatIndex.roomName }">{{ chatIndex.roomName + "の部屋"}}</router-link></div>
      </li>
    </ul>
  </div>
</template>
  
<script>
  import router from '@/router';
  import axios from 'axios';
  
  axios.defaults.baseURL='http://localhost:8081';
  
  export default {
    data() {
      return {
          chatIndexes: [],
      };
    },
    created() {
      this.showAll();
    },
    methods: {
      async showAll() {
        try {
          const response = await axios.get("/chatindex2");
          this.chatIndexes = response.data;
        }
        catch (error) {
          console.log(error);
        }
      },
    },
    components: { router }
  };
</script>

<style>
  ul{
      list-style:none 
  }
  
  li{
      padding-left:0
  }
</style>