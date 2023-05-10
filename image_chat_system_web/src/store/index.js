import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuthenticated: false,
    userId: null,
    userName: null
  },
  getters: {
    isAuthenticated: state => state.isAuthenticated,
    userId: state => state.userId,
    userName: state => state.userName,
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
    }
  },
  actions: {
    setAuthentication({commit}, status) {
      commit('setAuthentication', status);
    },
    setUserId({commit}, userId) {
      commit('setUserId', userId);
    },
    setUserName({commit}, userName){
      commit('setUserName', userName);
    },
    clearUserData({ commit }) {
      commit('clearAuthentication');
      commit('setUserId', null);
      commit('setUserName', null);
    }
  },
  modules: {
  },
  plugins: [createPersistedState()]
})
