import Vue from 'vue'
import '@/assets/stylus/main.styl'
import '@/assets/scss/main.scss'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import '@mdi/font/css/materialdesignicons.css'

Vue.use(Vuetify)

const opts = {}

export default new Vuetify({
	icons: {
		iconfont: 'mdiSvg',
	},
	opts
})
