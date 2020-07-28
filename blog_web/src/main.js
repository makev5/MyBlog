import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'

// 引入iconfont图标
import './assets/font/iconfont.css'

// 引入element-ui
import './plugins/element.js'

import moment from 'moment' // 日期工具类
Vue.prototype.$moment = moment // 赋值使用 就是调用一下
moment.locale('zh-cn') // 这里是进行了汉化

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
