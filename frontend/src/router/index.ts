import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Layout from '../components/Layout.vue'

Vue.use(VueRouter)

const routes: RouteConfig[] = [
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
		name: 'Index',
		component: Layout,
		children:
      [
      	{
      		meta: {
      			title: 'Fight COVID-19',
      			keepAlive: true,
      			gtm: 'Home Page'
      		},
      		path: '/home',
      		name: 'Home',
      		component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue'),
      		alias: '/',
      	},
      	{
      		meta: {
      			title: 'Fight COVID-19',
      			keepAlive: true,
      			gtm: 'Login Page'
      		},
      		path: '/login',
      		name: 'Login',
      		component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue'),
      		alias: '/login',
      	},
      	{
      		meta: {
      			title: 'Need Help',
      			keepAlive: true,
      			backComponentName: 'Home',
      		},
      		caseSensitive: false,
      		path: 'needhelp',
      		name: 'Need Help',
      		component: () => import(/* webpackChunkName: "needHelp" */ '../views/NeedHelp.vue')
      	},
      	{
      		meta: {
      			title: 'VolunteerRegistration',
      			keepAlive: true,
      			backComponentName: 'Home',
      		},
      		path: '/registervolunteer',
      		name: 'Volunteer Registration',
      		component: () => import(/* webpackChunkName: "volunteerRegistration" */ '../views/VolunteerRegistration.vue')
      	},
      	{
      		meta: {
      			title: 'Contact Us',
      			keepAlive: true,
      			backComponentName: 'Home',
      		},
      		path: '/contactus',
      		name: 'Contact Us',
      		// route level code-splitting
      		// this generates a separate chunk (about.[hash].js) for this route
      		// which is lazy-loaded when the route is visited.
      		component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
      	}
      ],
		pathToRegexpOptions:
      {
      	strict: true,
      }
	},

]

const router = new VueRouter({
	base: '/',
	mode: 'history',
	routes
})


export default router
