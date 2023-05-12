<template>
  <div id="header-menu">
    <ul class="header-list">
      <li><b-img :src="logoPath" class="tag"/></li>
      <li><a href="URL">部屋一覧</a></li>
      <li @click="logout">ログアウト</li>
  </ul>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        logoPath: require('@/assets/header-tag.png')
      }
    }
  }
</script>

<style>
  #header-menu{
  display: block; 
  position: fixed; /*ポジションを固定する*/
  top: 0px; /*一番上に設置*/
  left: 0px;
  z-index: 9999;  /*どの要素よりも上にくるように*/
  background-color: rgb(255, 255, 255); /*メニューバー背景色*/
  width: 100%; /*横幅を100%に*/
  height: 10%;
  }
  .header-list {
    display: flex; /* li要素を横並びにする */
    list-style: none;
    padding: 0;
  }
  #header_menu a {
  float: left; /*メニューを横並びにする*/
  font-size: 16px;
  color: #FFF;
  }
  #header_menu a:after { /*floatでデザインが崩れないように*/
  display:block;
  clear: both;
  content:"";
  }
  .header-list li {
    padding-left: 5em;
  }
  .tag{
    width: 70%;
    height: 80%;
  }
</style>