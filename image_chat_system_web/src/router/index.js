import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreatePage from '../views/CreatePage.vue'
import ChatPage from '../views/ChatPage.vue'
import store from '../store'

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