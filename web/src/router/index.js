import Vue from 'vue';
import Router from 'vue-router';

import Login from '../views/Login';
import HelloWorld from '../views/HelloWorld';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/login', name: 'Login', component: Login,
    },
    {
      path: '/', name: 'HelloWorld', component: HelloWorld,
    },
  ],
});
