import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuthenticated: false,
    user: null
  },
  getters: {
    isAuthenticated: state => state.isAuthenticated,
    user: state => state.user
  },
  mutations: {
    setAuthentication(state, status) {
      state.isAuthenticated = status;
    },
    setUser(state, user) {
      state.user = user;
    }
  },
  actions: {
    setAuthentication({commit}, status) {
      commit('setAuthentication', status);
    },
    setUser({commit}, user) {
      commit('setUser', user);
    }
  },
  modules: {
  }
})
