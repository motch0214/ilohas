import Vue from 'vue';
import Router from 'vue-router';

import Login from '../views/Login';
import HelloWorld from '../views/HelloWorld';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/', redirect: { name: 'HelloWorld' },
    },
    {
      path: '/login', name: 'Login', component: Login,
      meta: { permitted: true },
    },
    {
      path: '/hello', name: 'HelloWorld', component: HelloWorld,
    },
  ],
});

function authenticated() {
  return false; // TODO
}

router.beforeEach((to, from, next) => {
  if (to.matched.every(r => r.meta.permitted) || authenticated()) {
    next();
  } else {
    next({
      path: '/login',
      query: { redirect: to.fullPath }
    });
  }
});

export default router;
