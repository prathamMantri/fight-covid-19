import Axios from 'axios'

// eslint-disable-next-line standard/object-curly-even-spacing
import {cloneDeep, debounce, extend, filter, find, findIndex, indexOf, findKey, forIn, has, includes, isEmpty, keyBy, keys, omit, pickBy, remove, startsWith, without} from 'lodash'

export const env = process.env.NODE_ENV

const GOOGLE_MAP_KEY = process.env.VUE_APP_GOOGLE_MAP_KEY
const BASE_URL = process.env.VUE_APP_BASE_PATH
const API_URL = BASE_URL + '/api'


console.log('Application started')

if (window.uid) {
	Axios.defaults.headers.common.uid = window.uid
}

Axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
Axios.defaults.withCredentials = true
//Axios.defaults.baseURL = API_URL
Axios.defaults.baseURL = '/api'


export const _ = { extend, forIn, isEmpty, find, findIndex, findKey, indexOf, has, includes, filter, without, omit, keyBy, remove, keys, pickBy, cloneDeep, startsWith }
export const axios = Axios
export const googleMapKey = GOOGLE_MAP_KEY
export const dataLayer = window.dataLayer || []
export const vueCliMode = process.env.VUE_APP_CLI_MODE || false
