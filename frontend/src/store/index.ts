import Vue from 'vue'
import Vuex from 'vuex'
import Modules from '@/store/modules'

Vue.use(Vuex)

const state = {}
const getters = {}
const actions = {}
const mutations = {}

const store = new Vuex.Store({
	modules: Modules,
	strict: false,
	state() {
		return state
	},
	getters: getters,
	actions: actions,
	mutations: mutations
})

export default store
