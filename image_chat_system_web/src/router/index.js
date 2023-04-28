import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreatePage from '../views/CreatePage.vue'
import ChatPage from '../views/ChatPage.vue'
import store from '../store'

import ImageEditCropper from '../components/ImageEditCropper.vue'
import ImageEditCanvas from '../components/ImageEditCanvas.vue'
import ChatIndexPage from '../views/ChatIndexPage.vue'
import RoomIndex from '../views/RoomIndex.vue'

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
    component: CreatePage
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
    path: '/imageEditCropper',
    name: 'ImageEditCropper',
    component: ImageEditCropper,
  },
  {
    path: '/imageEditCanvas',
    name: 'ImageEditCanvas',
    component: ImageEditCanvas,
  },
  {
    path: '/chatindex',
    name:'ChatIndexPage',
    component:ChatIndexPage,
    // meta: { requiresAuth: true }//ログイン制限
    // component: () => import('../views/ChatRoom.vue')
  },
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