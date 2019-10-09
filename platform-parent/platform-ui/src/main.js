import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';

import store from './store'
import router from './router'

Vue.use(ElementUI);

//包含全局配置的js-start
import httpOperate from './utils/httpOperate.js'
//将访问后台的地址放在全局的Vue中
Vue.prototype.httpOperate=httpOperate

//阻止启动生产消息
Vue.config.productionTip = false

//将code 转换成 汉字，或者格式化日期等
import codeTransf from './utils/codeTransf'
Vue.use(codeTransf)

//包含全局配置的js-start
import constant from './utils/constant.js'
//将访问后台的地址放在全局的Vue中
Vue.prototype.constant=constant


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});