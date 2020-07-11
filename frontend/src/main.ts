import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VuetifyGoogleAutocomplete from 'vuetify-google-autocomplete'
import VueGoogleAutocomplete from 'vue-google-autocomplete'
import * as VueGoogleMaps from 'vue2-google-maps'
import {googleMapKey} from '@/bootstrap'
import {accessToken} from '@/bootstrap'

Vue.use(VuetifyGoogleAutocomplete, {
	apiKey: googleMapKey, // Can also be an object. E.g, for Google Maps Premium API, pass `{ client: <YOUR-CLIENT-ID> }`
	version: '3.40', // Optional
	language: '', // Optional
})

Vue.use(VueGoogleAutocomplete, {
	apiKey: googleMapKey, // Can also be an object. E.g, for Google Maps Premium API, pass `{ client: <YOUR-CLIENT-ID> }`
	version: '3.40', // Optional
	language: '', // Optional
})

Vue.config.productionTip = false

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App)
}).$mount('#app')
