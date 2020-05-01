import Axios from 'axios'
// eslint-disable-next-line standard/object-curly-even-spacing
import {cloneDeep, debounce, extend, filter, find, findIndex, findKey, forIn, has, includes, isEmpty, keyBy, keys, omit, pickBy, remove, startsWith, without} from 'lodash'

export const env = process.env.NODE_ENV

const GOOGLE_MAP_KEY = process.env.VUE_APP_GOOGLE_MAP_KEY


console.log('Application started')
console.log('process.env.GOOGLE_MAP_KEY is: %o', GOOGLE_MAP_KEY)

if (window.uid) {
  Axios.defaults.headers.common.uid = window.uid
}

Axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
Axios.defaults.withCredentials = true


export const _ = { extend, forIn, isEmpty, find, findKey, findIndex, has, includes, filter, without, omit, keyBy, debounce, remove, keys, pickBy, cloneDeep, startsWith }
export const axios = Axios
export const googleMapKey = GOOGLE_MAP_KEY
export const dataLayer = window.dataLayer || []
