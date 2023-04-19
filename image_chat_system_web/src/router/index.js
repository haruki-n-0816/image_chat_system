import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import Createpage from '../views/CreatePage.vue'
import Chatpage from '../views/ChatPage.vue'
import ChatIndexPage from '../views/ChatIndexPage.vue'

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
    path: '/chat/${groupName}',
    name: 'ChatPage',
    component: Chatpage
    // component: () => import('../views/ChatPage.vue')
  },
  {
    path: '/chatindex',
    name:'ChatIndexPage',
    component:ChatIndexPage
    // component: () => import('../views/ChatRoom.vue')
  }
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
