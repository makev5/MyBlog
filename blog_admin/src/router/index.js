import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  // 登录
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  // 404
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  // 首页
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
    }]
  },
  // 用户管理
  {
    path: '/user',
    component: Layout,
    redirect: '/user/list',
    meta: { title: '用户管理', icon: 'peoples' },
    children: [
      {
        path: 'profile',
        name: 'Profile',
        component: () => import('@/views/user/profile/index'),
        meta: { title: '个人中心', icon: 'user' }
      },
      {
        path: 'list',
        name: 'UserList',
        component: () => import('@/views/user/list'),
        meta: { title: '用户列表', icon: 'nested' }
      },
      {
        path: 'add',
        name: 'UserAdd',
        component: () => import('@/views/user/edit'),
        meta: { title: '添加用户', icon: 'edit' }
      },
      {
        path: '/:userId/edituser',
        name: 'EditUser',
        hidden: true,
        component: () => import('@/views/user/edit'),
        meta: { title: '编辑用户', icon: 'edit' }
      }
    ]
  },
  // 博客管理
  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/list',
    name: 'Blog',
    meta: { title: '博客管理', icon: 'guide' },
    children: [
      {
        path: 'list',
        name: 'blogList',
        component: () => import('@/views/blog/list'),
        meta: { title: '博客列表', icon: 'nested' }
      },
      {
        path: 'add',
        name: 'BlogAdd',
        component: () => import('@/views/blog/edit'),
        meta: { title: '添加博客', icon: 'edit' }
      },
      {
        path: '/:blogId',
        name: 'BlogDetail',
        hidden: true,
        component: () => import('@/views/blog/edit'),
        meta: { title: '博客详情', icon: 'edit' }
      },
      {
        path: '/:blogId/editblog',
        name: 'BlogEdit',
        hidden: true,
        component: () => import('@/views/blog/edit'),
        meta: { title: '编辑博客', icon: 'edit' }
      },
      {
        path: 'tab',
        name: 'tabList',
        component: () => import('@/views/blog/tab'),
        meta: { title: '标签管理', icon: 'tab' }
      },
      {
        path: 'sort',
        name: 'sortList',
        component: () => import('@/views/blog/sort'),
        meta: { title: '分类管理', icon: 'sort' }
      },
      {
        path: 'about',
        name: 'About',
        component: () => import('@/views/blog/about'),
        meta: { title: '关于博客', icon: 'people' }
      }
    ]
  },
  // 资源管理
  {
    path: '/file',
    component: Layout,
    alwaysShow: true,
    redirect: '/file/picture',
    meta: { title: '资源管理', icon: 'guide' },
    children: [
      {
        path: 'picture',
        name: 'Picture',
        component: () => import('@/views/file/picture/list'),
        meta: { title: '图片管理', icon: 'edit' }
      }
    ]
  },
  // 网站配置
  {
    path: '/config',
    component: Layout,
    alwaysShow: true,
    redirect: '/config/info',
    meta: { title: '网站设置', icon: 'guide' },
    children: [
      {
        path: 'info',
        name: 'WebInfo',
        component: () => import('@/views/config/index'),
        meta: { title: '网站设置', icon: 'edit' }
      }
    ]
  },
  // 返回前台
  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'localhost:7979',
        meta: { title: '返回前台', icon: 'link' }
      }
    ]
  },

  // 404页面必须放在最后
  { path: '*', redirect: '/404', hidden: true }
]

export const asyncRoutes = []

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
