// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from "./store/store"
import Vant from 'vant';
import 'vant/lib/index.css';

Vue.use(Vant);
Vue.use(VueAxios, axios)
Vue.config.productionTip = false
Vue.use(router);
Vue.use(ElementUI);
new Vue({
  el: '#app',
  router: router,
  store: store,
  render: h => h(App)
});
