import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuthenticated: false,
    userId: null,
    userName: null,
    roomName:''
  },
  getters: {
    isAuthenticated: state => state.isAuthenticated,
    userId: state => state.userId,
    userName: state => state.userName,
    roomName:state => state.roomName
  },
  mutations: {
    setAuthentication(state, status) {
      state.isAuthenticated = status;
    },
    setUserId(state, userId) {
      state.userId = userId;
    },
    setUserName(state, userName){
      state.userName = userName;
    },
    clearAuthentication(state) {
      state.isAuthenticated = false;
    },
    setRoomName(state, roomName){
      state.roomName = roomName;
    }
  },
  actions: {
    setAuthentication({commit}, status) {
      commit('setAuthentication', status);
      sessionStorage.setItem('setAuthentication',JSON.stringify(status));
    },
    setUserId({commit}, userId) {
      commit('setUserId', userId);
      sessionStorage.setItem('userId',JSON.stringify(userId));
    },
    setUserName({commit}, userName){
      commit('setUserName', userName);
      sessionStorage.setItem('userName',JSON.stringify(userName));
    },
    clearUserData({ commit }) {
      commit('clearAuthentication');
      commit('setUserId', null);
      commit('setUserName', null);
    },
    setRoomName({commit}, roomName){
      commit('setRoomName', roomName);
    },
    // initializeStore({ commit }) {
    //   // セッションストレージからデータを取得し、ストアにセットする
    //   const userIdData = sessionStorage.getItem('userId');
    //   const userNameData = sessionStorage.getItem('userName');
    //   if (userIdData && userNameData) {
    //     const userId = JSON.parse(userIdData);
    //     const userName = JSON.parse(userNameData);
    //     // commit('setUserId', userId);
    //     // commit('setUserName',userName);
    //   }
    // }
  },
  modules: {
  },
  plugins: [createPersistedState()]
});
