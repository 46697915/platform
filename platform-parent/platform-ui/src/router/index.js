import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import HelloWorld from '@/components/HelloWorld'
import login from '@/login/login'
import home from '@/components/home'
import SysUser from '@/components/system/user/SysUser'
import SysRole from '@/components/system/role/SysRole'
import SysPermission from '@/components/system/permission/SysPermission'

export const constantRoutes = [
    {
        path: '/',
        component: login
    },
    {
        path: '/home',
        component: home,
        children: [
            {
                path: 'sysUser',
                component: SysUser,
                name: 'sysUser',
                meta: { title: '用户管理', icon: 'dashboard', affix: false }
            },
            {
                path: 'sysRole',
                component: SysRole,
                name: 'sysRole',
                meta: { title: '角色管理', icon: 'dashboard', affix: false }
            },
            {
                path: 'sysPermission',
                component: SysPermission,
                name: 'SysPermission',
                meta: { title: '权限管理', icon: 'dashboard', affix: false }
            },
            {
                path: 'HelloWorld',
                component: HelloWorld,
                name: 'HelloWorld',
                meta: { title: '主页', icon: 'dashboard', affix: true }
            }
        ]
    },
    {
        path: '/HelloWorld',
        component: HelloWorld
    }
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
