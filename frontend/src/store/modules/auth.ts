import AuthService from '../../services/auth.service'

const user = JSON.parse(<string>localStorage.getItem('user'))

const initialState = user
	? {status: {loggedIn: true}, user}
	: {status: {loggedIn: false}, user: null}


const getters = {}
const actions = {
	async login({commit}, user) {

		return AuthService.login(user).then(
			user => {
				commit('loginSuccess', user)
				return Promise.resolve(user)
			},
			error => {
				commit('loginFailure')
				return Promise.reject(error)
			}
		)

		/*return context.dispatch('auth/login', user).then(
      user => {
        context.commit('loginSuccess', user);
        return Promise.resolve(user);
      },
      error => {
        context.commit('loginFailure');
        return Promise.reject(error);
      }
    );*/
	},

	logout({commit}) {
		//context.dispatch('auth/logout')
		AuthService.logout()
		commit('logout')
	},
}
const mutations = {
	loginSuccess(state, user) {
		state.status.loggedIn = true
		state.user = user
	},
	loginFailure(state) {
		state.status.loggedIn = false
		state.user = null
	},
	logout(state) {
		state.status.loggedIn = false
		state.user = null
	},
}

export default {
	namespaced: true,
	state() {
		return initialState
	},
	getters: getters,
	actions: actions,
	mutations: mutations
}
