import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreatePage from '../views/CreatePage.vue'
import ChatPage from '../views/ChatPage.vue'
import store from '../store'

import ImageEditCropper from '../components/ImageEditCropper.vue'
import ImageEditCanvas from '../components/ImageEditCanvas.vue'
import ImageEditWindow from '../components/ImageEditWindow.vue'


import ChatIndexPage from '../views/ChatIndexPage.vue'
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
    component: CreatePage
  },
  {
    path: '/chat/${groupName}',
    name: 'ChatPage',
    component: ChatPage,
    meta: { requiresAuth: true }
  },// ここからテスト
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
    path: '/imageEditWindow',
    name: 'ImageEditWindow',
    component: ImageEditWindow,
  },
  
  // ここまで
  {
    path: '/chatindex',
    name:'ChatIndexPage',
    component:ChatIndexPage
    // component: () => import('../views/ChatRoom.vue')
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