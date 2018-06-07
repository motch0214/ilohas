import Vue from 'vue';
import App from './App';
import router from './router';
import Element from 'element-ui';
import './styles/theme/index.css';
import 'normalize.css';

import locale from 'element-ui/lib/locale/lang/en';

Vue.use(Element, { locale });

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
});
