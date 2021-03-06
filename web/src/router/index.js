import Vue from 'vue';
import Router from 'vue-router';

import Layout from '../views/Layout';
import Login from '../views/login/Login';
import HelloWorld from '../views/HelloWorld';
import MarketData from '../views/marketdata/MarketData';
import SystemLog from '../views/system/SystemLog';

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
      component: Layout,
      children: [
        {
          path: '/',
          name: 'Main',
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
          meta: {
            breadcrumbs: [
              { title: 'Data Management' },
              { title: 'Market Data' },
            ],
          },
        },
        {
          path: 'system/log',
          name: 'SystemLog',
          component: SystemLog,
          meta: {
            breadcrumbs: [
              { title: 'System Management' },
              { title: 'System Log' },
            ],
          },
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
