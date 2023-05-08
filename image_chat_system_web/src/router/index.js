import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreateAccountPage from '../views/CreateAccountPage.vue'
import ChatPage from '../views/ChatPage.vue'
import ImageEditWindow from '../components/ImageEditWindow.vue'
import store from '../store'


import ChatIndexPage from '../views/ChatIndexPage.vue'
import RoomIndex from '../views/RoomIndexPage.vue'
import StartPage from '../views/StartPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'',
    name:'StartPage',
    component:StartPage
  },
  {
    path: '/login',
    name: 'loginPage',
    component: LoginPage
  },
  {
    path: '/create',
    name: 'CreateAccountPage',
    component: CreateAccountPage
  },
  // {
  //   path: '/chatpage',
  //   name: 'ChatPage',
  //   component: ChatPage,
  // //   meta: { requiresAuth: true }
  // },
  {
    path: '/chatpage/:roomId',
    name: 'ChatPage',
    component: ChatPage,
    props: true,
  },  
  {
    path: '/imageEditWindow',
    name: 'ImageEditWindow',
    component: ImageEditWindow,
  },
  // {
  //   path: '/chatindex',
  //   name:'ChatIndexPage',
  //   component:ChatIndexPage,
  //   meta: { requiresAuth: true }//ログイン制限
  //   // component: () => import('../views/ChatRoom.vue')
  // },
  {
    path:'/chatindex2',
    name:'RoomIndex',
    component:RoomIndex,
    // meta: { requiresAuth: true }
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

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isAuthenticated) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router