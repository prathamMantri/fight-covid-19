<template>
	<ValidationObserver ref="loginObserver" v-slot="{ validate, reset }">
		<v-form ref="loginForm" @submit.prevent="submit" autocomplete="off">
			<ValidationProvider v-slot="{ errors }" name="username" rules="required">
				<v-text-field
					v-model="user.username"
					:error-messages="errors"
					label="Username"
					required
				></v-text-field>
			</ValidationProvider>
			<ValidationProvider v-slot="{ errors }" name="password" rules="required">
				<v-text-field
					v-model="user.password"
					label="Password"
					:type="show ? 'text' : 'password'"
					:error-messages="errors"
					:append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
					@click:append="show = !show"
					required
				></v-text-field>
			</ValidationProvider>
			<v-btn
				color="primary"
				class="mr-4"
				type="submit"
			>
				Login
			</v-btn>
			<v-btn
				class="mr-4"
				@click="resetForm"
			>
				Reset
			</v-btn>
		</v-form>
	</ValidationObserver>
</template>

<script>


import {required} from 'vee-validate/dist/rules'
import {axios} from '../bootstrap'
import {extend, ValidationObserver, ValidationProvider, localize, setInteractionMode} from 'vee-validate'
import User from '../models/user'
import {mapActions, mapGetters, mapMutations, mapState} from 'vuex'

setInteractionMode('eager')


const dictionary = {
	en: {
		messages: {
			internalError: fieldName => 'Internal Error occured',
		}
	}
}

extend('required', {
	...required,
	message: 'Please enter {_field_}',
})
localize(dictionary)


export default {
	name: 'Login',
	components: {
		ValidationProvider,
		ValidationObserver,
	},
	computed: {
		...mapState('auth', ['status']),
		...mapState('home', ['snackbarProps']),
		loggedIn() {
			return this.status.loggedIn
		}

	},
	created() {
		if (this.loggedIn) {
			console.log('User already logged in')
		}
	},

	data: () => ({
		user: new User('', ''),
		show: false,
		username: '',
		password: '',
	}),

	methods: {
		...mapActions('auth',
			['login', 'logout']
		),

		...mapMutations('home', [
			'setSnackbarProps','resetSnackbarProps']),

		resetForm() {
			this.username = ''
			this.password = ''
			this.$refs.loginObserver.reset()
		},
		submit() {
			this.$refs.loginObserver.validate().then(result => {
				if (result) {

					this.setSnackbarProps = {
						snackbar: true,
						timeout: 5000,
						text: 'Logging in',
						color: 'info',
					}
					this.handlelogin()
				}
			})
		},

		async handlelogin() {
			return new Promise((resolve, reject) => {
				this.login(this.user)
					.catch(error => {
						this.setSnackbarProps = {
							snackbar: true,
							timeout: 5000,
							text: 'Something went wrong. We are looking into it.',
							color: 'error',
						}
						console.log('error in saving  data')
						if (error) {
							console.log(error.stack)
						}
						reject(error)
					})
				this.resetSnackbarProps()
			})
		},
		logout() {
			this.setSnackbarProps = {
				snackbar: true,
				timeout: 5000,
				text: 'Logging out',
				color: 'info',
			}
			this.logout()
			this.resetSnackbarProps()
		}
	},
}
</script>

<style scoped>

</style>
