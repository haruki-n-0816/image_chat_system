<template>
  <div>
    <h1>ChatRoom</h1>
    <ul>
      <li v-for="group in groups" :key="group.id">
        <span class="groupname">{{ group.name }}</span>
        <button @click="openGroupUrl(group)">部屋に入る</button>
        <button @click="deleteGroup(group.id)">部屋削除</button>
      </li>
    </ul>
    <div>
      <label for="group-name-input">グループ名を記入してください:</label>
      <input type="text" id="group-name-input" v-model="roomName">
      <button @click="addGroup">部屋を追加する</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL='http://localhost:8081';

export default {
  data() {
    return {
      groups: [],
      roomName: "",
    };
  },
  methods: {
    async addGroup() {
      try{
        const respones = await axios.post('/chatindex',{
          roomIndexName:this.roomName,
        });
        console.log(respones);
      }catch(error){
        console.log(error);
      }

      const groupName = this.roomName;
      if (groupName) {
          const newGroup = {
            name: groupName,
            url: `/chat/${groupName}`, // 新しいプロパティを追加
            names: new Array(3).fill(""),
          };
          this.groups.push(newGroup);
          this.roomName = ""; // テキストボックスをクリアする
        }
    },
    // addGroup(){
    //   const groupName = this.roomName;

    //   if (groupName) {
    //       const newGroup = {
    //         id: this.roomId++,
    //         name: groupName,
    //         url: `/chat/${groupName}`, // 新しいプロパティを追加
    //         names: new Array(3).fill(""),
    //       };
    //       this.groups.push(newGroup);
    //       this.roomName = ""; // テキストボックスをクリアする
    //     }
    // },
    openGroupUrl(group) {
      this.$router.push({ name: "ChatPage", params: { roomName: group.name } });
    },
    deleteGroup(groupId) {
      this.groups = this.groups.filter((group) => group.id !== groupId);
    },
  },
};
</script>

<style>
li {
  list-style: none;
}

ul {
  padding-left: 0;
}

.groupname {
  font-size: 40px;
}
</style>
<!-- const groupName = this.roomName;

if (groupName) {
    const newGroup = {
      id: this.roomId++,
      name: groupName,
      url: `/chat/${groupName}`, // 新しいプロパティを追加
      names: new Array(3).fill(""),
    };
    this.groups.push(newGroup);
    this.roomName = ""; // テキストボックスをクリアする
} -->