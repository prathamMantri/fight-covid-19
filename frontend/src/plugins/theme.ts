// src/theme.js
import colors from 'vuetify/lib/util/colors'
import _ from 'lodash'

let fightCovid19BlueTheme = _.clone(colors.blue);
fightCovid19BlueTheme = _.assign(fightCovid19BlueTheme, {
	base: '#1976D2', // main header background color
	lighten1: '#039BE5', //secondary header background color
	accent1: '#009de1' //checkbox color
});

let fightCovid19GrayTheme = _.clone(colors.grey);
fightCovid19GrayTheme = _.assign(fightCovid19GrayTheme, {
	lighten6: '#B6DFF4',
	lighten5: '#fafafa',
	lighten4: '#f5f5f5', //card heading backgrounds
	lighten3: '#eee', //main page background color
	lighten2: '#cfcfcf', //dividers between items
	lighten1: '#cccccc',
	base: '#BDBDBD', //card heading border color
	darken1: '#999999', //label color
	darken2: '#707070', //card headings and footer text
	darken3: '#616161',
	darken4: '#333' //body text
});

let fightCovid19AccentTheme = _.clone(colors.blue);

fightCovid19AccentTheme = _.assign(fightCovid19AccentTheme, {
	base: '#009de1', // main header background color
});

export default {
	primary: fightCovid19BlueTheme,
	secondary: fightCovid19GrayTheme,
	accent: fightCovid19AccentTheme,
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
