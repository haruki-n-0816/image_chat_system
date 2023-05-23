<template>
  <div id="header-menu">
    <ul class="header-list">
      <b-img :src="logoPath" class="tag" /> 
      <li class="bar-user-name"> ユーザー名: {{ this.$store.getters.userName }}</li>
      <li class="nav-item"><a @click="logout" class="navbar-logout" style="cursor: pointer;">ログアウト</a></li>
      <li v-if="shouldShowRoomName" class="bar-room-name">{{ "入室中の部屋："+this.$store.getters.roomName }}</li>
      <li v-if="shouldShowRoomName" class="nav-item" ><router-link to="/roomIndex" id="nav-link-exit">退室</router-link></li>
    </ul>
  </div>
</template>

<!-- eslint-disable  -->
<script>
  export default {
    computed: {
      currentRoute() {
        return this.$route.path;
      },
      shouldShowRoomName() {
        return this.currentRoute !== '/roomIndex'; // 表示しないURLを指定
      }
    },
    data() {
      return {
        logoPath: require('/public/assets/header-tag.png')
      }
    },
    methods:{
      async logout() {
      try {
        this.$store.dispatch('clearUserData');
        this.$router.push('/login');
      } catch (error) {
        console.error(error);
      }
    },
    }
  }
</script>

<style>
  #header-menu {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 3rem;
    background-color: #e6f3ff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    z-index: 999;
  }

  .header-list {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    height: 100%;
    margin: 0;
    padding: 0 1rem;
    list-style: none;
  }

  .tag {
    width: 25rem;
    height: auto;
  }

  .nav-item {
    margin-left: 1rem;
  }

  #nav-link-exit{
    color: rgb(255, 0, 0);
    text-decoration: none;
  }

  #nav-link-exit:hover{
    color:rgb(1, 1, 172)
  }

  .navbar-logout {
    color: red;
    text-decoration: none;
  }

  body {
    margin: 0;
  }

  li{
    white-space: nowrap; /* テキストの折り返しを制御 */
    overflow: hidden; /* テキストがはみ出た場合に隠す */
    text-overflow: ellipsis; /* テキストがはみ出した場合に省略記号を表示 */
  }

  .nav-link:hover{
    color: #f00; 
  }

  .bar-room-name{
    margin-left: 2rem;
    text-align: right;
  }

  .bar-user-name{
    margin-left: 2rem;
    text-align: right;
  }
</style>