// vue.config.js

/*const https = require('https')
const fs = require('fs')
const options = {
	key: fs.readFileSync(''),
	cert: fs.readFileSync('')
}
const server = https.createServer(options, app).listen(port)*/



module.exports = {
	// proxy all webpack dev-server requests starting with /api
	// to our Spring Boot backend (localhost:8088) using http-proxy-middleware
	// see https://cli.vuejs.org/config/#devserver-proxy
	lintOnSave: false,

	devServer: {

		proxy: {
			'/api': {
				target: 'https://localhost:80', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
				ws: true,
				changeOrigin: true
			}
		}
	},

	/* prodServer: {
    proxy: {
      '/api': {
        target: 'https://fightcovid19.care', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
        ws: true,
        changeOrigin: true
      }
    }
  },*/
	// Change build paths to make them Maven compatible
	// see https://cli.vuejs.org/config/
	outputDir: 'target/dist',
	assetsDir: 'static'
}
