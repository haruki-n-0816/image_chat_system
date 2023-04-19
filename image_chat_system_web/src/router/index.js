import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreatePage from '../views/CreatePage.vue'
import ChatPage from '../views/ChatPage.vue'
import store from '../store'

import ImageEditCropper from '../components/ImageEditCropper.vue'
import ImageEditCanvas from '../components/ImageEditCanvas.vue'

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
    path: '/chat',
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