import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';

import router from './router'

Vue.use(ElementUI);

//包含全局配置的js-start
import httpOperate from './https.js'
//将访问后台的地址放在全局的Vue中
Vue.prototype.httpOperate=httpOperate


//阻止启动生产消息
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});