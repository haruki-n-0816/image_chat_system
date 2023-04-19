import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import Createpage from '../views/CreatePage.vue'
import Chatpage from '../views/ChatPage.vue'
// import WebSocket from '../views/WebSocket.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'loginPage',
    component: LoginPage
  },
  {
    path: '/create',
    name: 'CreatePage',
    component: Createpage
    // component: () => import(/* webpackChunkName: "about" */ '../views/CreatePage.vue')
  },
  {
    path: '/chat',
    name: 'ChatPage',
    component: Chatpage
    // component: () => import('../views/ChatPage.vue')
  },
  {
    path: '/WebSocket',
    name: 'WebSocket',
    // component: WebSocket
    component: () => import('../views/WebSocket.vue')
  }
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
