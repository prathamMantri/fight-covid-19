// src/theme.js
import colors from 'vuetify/lib/util/colors'
import _ from 'lodash'

let lockdownYellowTheme = _.clone(colors.yellow)
lockdownYellowTheme = _.assign(lockdownYellowTheme, {
	base: '#FFC400', // main header background color
	lighten1: '#FFD740', //secondary header background color
	accent1: '#FFE57F' //checkbox color
})

let lockdownBrownTheme = _.clone(colors.brown)
lockdownBrownTheme = _.assign(lockdownBrownTheme, {
	base: '#5D4037',
	lighten1: '#8D6E63',
})


let lockdownAccentTheme = _.clone(colors.yellow)

lockdownAccentTheme = _.assign(lockdownAccentTheme, {
	base: '#009de1', // main header background color
})

export default {
	primary: lockdownYellowTheme,
	secondary: lockdownBrownTheme,
	accent: lockdownAccentTheme,
	cwGray: '#7f7f7f',
	white: '#fff',
	black: '#000',
	progress: '#41BB00',
	progressBackground: '#eee',
	systemAvatar: '#757575',
	conversationShopNameColor: '#666666',
	textFieldInput: '#CCCCCC',
	error: '#E53935'
	//cwGrey: fightCovid19GrayTheme
	// All keys will generate theme styles,
	// Here we add a custom `tertiary` color
	//tertiary: fightCovid19BlueTheme,
}
