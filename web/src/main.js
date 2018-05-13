import Vue from 'vue';
import App from './App';
import router from './router';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'normalize.css';

Vue.use(Element)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
});
