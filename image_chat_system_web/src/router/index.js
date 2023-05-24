import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import CreateUserPage from '../views/CreateUserPage.vue'
import ChatPage from '../views/ChatPage.vue'
// import ImageEditWindow from '../components/ImageEditWindow.vue'
import MainNavigationBar from '../components/MainNavigationBar.vue'
import RoomIndex from '../views/RoomIndexPage.vue'
// import store from '../store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'loginPage',
    component: LoginPage
  },
  {
    path: '/create',
    name: 'CreateUserPage',
    component: CreateUserPage
  },
  {
    path: '/chatpage/:roomId',
    name: 'ChatPage',
    component: ChatPage,
    props: true,
  },  
  // {
  //   path: '/imageEditWindow',
  //   name: 'ImageEditWindow',
  //   component: ImageEditWindow,
  // },
  {
    path:'/roomIndex',
    name:'RoomIndex',
    component:RoomIndex,
    // meta: { requiresAuth: true }
  },
  {
    path:'/mainNavigationBar',
    name:'MainNavigationBar',
    component:MainNavigationBar
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!sessionStorage.getItem('setAuthentication')) {
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