import Vue from 'vue'
import Router from 'vue-router'
/* eslint-disable */
Vue.use(Router)

export default new Router({
  mode: 'hash',
  routes: [
    {
      path: '',
      component: () => import('@/views/home/index'),
      children: [
        // 首页
        {
          path: '',
          component: () => import('@/views/home/components/index'),
          meta: { title: '首页' }
        },
        // 标签页
        {
          path: 'tag',
          component: () => import('@/views/tag/index'),
          meta: { title: '标签' }
        },
        // 时间线
        {
          path: 'timeline',
          component: () => import('@/views/timeline/index'),
          meta: { title: '时间线' }
        },
        // 工具箱
        {
          path: 'tool',
          component: () => import('@/views/tool/index'),
          meta: { title: '工具箱' }
        },
        // 文章分类
        {
          path: 'sort',
          component: () => import('@/views/sort/index'),
          meta: { title: '文章分类' }
        },
        // 关于我
        {
          path: 'about',
          component: () => import('@/views/about/index'),
          meta: { title: '关于博客' }
        },
        // 博客详情
        {
          path: '/blog/:id',
          component: () => import('@/views/blog/index'),
          meta: { title: '博客详情' }
        },
        {
          path: '/blogsort/:name',
          component: () => import('@/components/Blog/blogsort'),
        },
        {
          path: '/blogtag/:name',
          component: () => import('@/components/Blog/blogtag'),
        }
      ]
    }
  ]
})
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}