import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'
import Landing from '../views/Landing.vue'
import Layout from '../components/Layout.vue'


Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '',
    redirect: '/',
    name: 'index-redirect',
    pathToRegexpOptions: {
      strict: true
    },
  },

  {
    path: '/',
    component: Layout,
    children:
      [
        {
          meta: {
            title: 'Fight Covid 19',
            keepAlive: true,
            gtm: 'Landing Page'
          },
          path: '/landing',
          name: 'landing',
          component: Landing,
          pathToRegexpOptions: {
            strict: true
          },
        },
        {
          meta: {
            goBack: false,
            title: 'Page Not Found',
            keepAlive: true,
            gtm: 'Page Not Found'
          },
          path: '/page-not-found',
          name: 'page-not-found',
          component: () => import(/* webpackChunkName: "globalError" */ '../views/GlobalError.vue')
        },
      ],
    pathToRegexpOptions:
      {
        strict: true,
        sensitive: true,
        end: true
      }
  },


  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
];

const router = new VueRouter({
  mode: 'history',
  routes
});


export default router
