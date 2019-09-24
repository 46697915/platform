import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import HelloWorld from '@/components/HelloWorld'
import login from '@/login/login'
import home from '@/components/home'

export const constantRoutes = [
  {
    path: '/',
    component: login
  },
  {
    path: '/home',
    component: home
  },
  {
    path: '/HelloWorld',
    component: HelloWorld
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router