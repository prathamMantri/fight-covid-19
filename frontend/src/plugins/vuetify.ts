import Vue from 'vue'
import '@/assets/stylus/main.styl'
import '@/assets/scss/main.scss'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import '@mdi/font/css/materialdesignicons.css'

import theme from '@/plugins/theme'

Vue.use(Vuetify)

const opts = {
	theme: {
		themes: {
			light: {
				primary: theme.primary,
				secondary: theme.secondary,
				accent: theme.accent // #3F51B5
			},
		},
	}
}


// @ts-ignore
export default new Vuetify({
	icons: {
		iconfont: 'mdiSvg',
	},
	defaultAssets: {
		font: {
			family: 'Kalam'
		},
	},
	...opts,
})
