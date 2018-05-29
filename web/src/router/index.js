import Vue from 'vue';
import Router from 'vue-router';

import Login from '../views/Login';
import Main from '../views/Main';
import HelloWorld from '../views/HelloWorld';
import MarketData from '../views/marketdata/MarketData';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: { permitted: true },
    },
    {
      path: '/',
      name: 'Main',
      component: Main,
      children: [
        {
          path: '/',
          redirect: { name: 'HelloWorld' },
        },
        {
          path: 'hello',
          name: 'HelloWorld',
          component: HelloWorld,
        },
        {
          path: 'market',
          name: 'MarketData',
          component: MarketData,
        },
      ],
    },
  ],
});

function authenticated() {
  const token = JSON.parse(localStorage.getItem('user-token'));
  return token && 'sessionId' in token;
}

router.beforeEach((to, from, next) => {
  if (to.matched.every(r => r.meta.permitted) || authenticated()) {
    next();
  } else {
    next({
      path: '/login',
      query: { redirect: to.fullPath },
    });
  }
});

export default router;
