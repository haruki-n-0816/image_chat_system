<template>
  <div id="header-menu">
    <ul class="header-list">
      <b-img :src="logoPath" class="tag" />
      <li class="nav-item" ><router-link to="/roomIndex" id="nav-link">部屋一覧</router-link></li>
      <li class="bar-userName"> ユーザー名: {{ this.$store.getters.userName }}</li>
      <li class="nav-item"><a @click="logout" class="navbar-logout" style="cursor: pointer;">ログアウト</a></li>
    </ul>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        logoPath: require('@/assets/header-tag.png')
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
    height: 4rem;
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
    margin-left: 2rem;
  }

  #nav-link{
    color: rgb(0, 0, 0);
    text-decoration: none;
  }

  #nav-link:hover{
    color:blue
  }

  .navbar-logout {
    color: red;
    text-decoration: none;
  }

  body {
    margin: 0;
  }

  .bar-userName{
    flex-grow: 1;
    text-align: right;
  }

  li{
    white-space: nowrap; /* テキストの折り返しを制御 */
    overflow: hidden; /* テキストがはみ出た場合に隠す */
    text-overflow: ellipsis; /* テキストがはみ出した場合に省略記号を表示 */
  }

  .nav-link:hover{
    color: #f00; 
  }
</style>