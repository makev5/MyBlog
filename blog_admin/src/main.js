import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import locale from 'element-ui/lib/locale/lang/en' // 如果使用中文语言包请默认支持，无需额外引入，请删除该依赖

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import moment from 'moment' // 日期工具类
Vue.prototype.$moment = moment // 赋值使用 就是调用一下
moment.locale('zh-cn') // 这里是进行了汉化

import '@/icons' // icon
import '@/permission' // permission control

import mavonEdit from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEdit)

Vue.use(ElementUI, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
