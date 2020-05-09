<template>
	<v-card class="px-5 elevation-0">
		<v-row sm="12">
			<v-col class="px-4" sm="12">
				<ValidationObserver ref="observer" v-slot="{ validate, reset }">
					<v-form ref="form" @submit.prevent="submit">
						<v-container fluid>
							<v-row>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="first name" rules="required|min:2">
										<v-text-field
											v-model="form.firstName"
											:error-messages="errors"
											label="First Name"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="last name" rules="required|min:2">
										<v-text-field
											v-model="form.lastName"
											:error-messages="errors"
											label="Last Name"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="username" rules="required|min:4|uniqueUserName">
										<v-text-field
											v-model="form.username"

											:error-messages="errors"
											label="Username"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="password" rules="required|min:8">
										<v-text-field
											v-model="form.password"
											label="Password (MD5 Encryption Protected)"
											:type="show ? 'text' : 'password'"
											:error-messages="errors"
											:append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
											@click:append="show = !show"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="phone number" rules="required|min:10|uniquePhone|validPhoneNumber|max:13">
										<v-text-field
											v-model="form.phoneNumber"
											:error-messages="errors"
											label="Phone number(US or India)"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="email address" rules="required|email|uniqueEmail">
										<v-text-field
											v-model="form.email"
											:error-messages="errors"
											label="Email Address"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider
										ref="gmapautocomplete"
										v-slot="{failedRules, errors, flags}"
										vid="address"
										name="address"
										mode="eager"
										:events="['none']"
										rules="requireAddress"
										:immediate="false"
										:persist="false"
										:bails="false"
										:debounce="10"
										tag="span"
										:slim="true"
									>
										<vuetify-google-autocomplete
											id="map"
											:v-model="form.address"
											append-icon="place"
											:error-messages="errors"
											label="Type your address"
											placeholder=""
											v-on:placechanged="getAddressData"
										>
										</vuetify-google-autocomplete>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="City" rules="required">
										<v-text-field
											v-model="form.city"
											:error-messages="errors"
											label="City"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="state" rules="required">
										<v-text-field
											v-model="form.state"
											:error-messages="errors"
											label="State"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="country" rules="required">
										<v-text-field
											v-model="form.country"
											:error-messages="errors"
											label="Country"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="zip" rules="required">
										<v-text-field
											v-model="form.zip"
											:error-messages="errors"
											label="Zip"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider
										ref="termsProvider"
										v-slot="{failedRules, errors, flags}"
										vid="termsAndConditions"
										name="termsAndConditions"
										mode="eager"
										:events="['none']"
										rules="requireTermsAndConditions"
										:immediate="false"
										:persist="false"
										:bails="false"
										:debounce="10"
										tag="span"
										:slim="true"
									>
										<v-checkbox
											v-model="form.terms"
											name="Terms"
											:error-messages="errors"
											type="checkbox"
											color="primary"
										>
											<template v-slot:label>
												<div @click.stop="">
													Do you accept the
													<a href="/#" @click.stop="terms = true">terms</a>
													and
													<a href="/#" @click.stop="conditions = true">conditions?</a>
												</div>
											</template>
										</v-checkbox>
									</ValidationProvider>
								</v-col>
							</v-row>
						</v-container>
						<v-card-actions>
							<v-row>
								<v-col class="text-right" sm="12" md="12" lg="4" xl="4">
									<v-row sm="12">
										<v-col class="text-left" sm="6" md="6" lg="6" xl="6">
											<v-btn @click="resetForm">CANCEL</v-btn>
										</v-col>
										<v-col class="text-left" sm="6" md="6" lg="6" xl="6">
											<v-btn
												:disabled="!formIsValid"
												color="primary"
												type="submit"
											>REGISTER
											</v-btn>
										</v-col>
									</v-row>
								</v-col>
							</v-row>
						</v-card-actions>
					</v-form>
				</ValidationObserver>
			</v-col>
		</v-row>
		<v-snackbar
			v-model="snackbar"
			:color="snackbarColor"
			:multi-line="true"
			:timeout="snackbarTimeout"
			:top="true"
		>
			{{ snackbarText }}
			<v-btn
				v-if="snackbarColor!='info'"
				dark
				text
				@click="snackbar = false"
			>
				Close
			</v-btn>
			<v-progress-circular
				v-else
				indeterminate
				color="amber"
			></v-progress-circular>
		</v-snackbar>
	</v-card>
</template>

<script>

import VuetifyGoogleAutocomplete from 'vuetify-google-autocomplete'

import {axios} from '../bootstrap'
import {required, email, min, max} from 'vee-validate/dist/rules'
import {extend, ValidationObserver, ValidationProvider, localize, setInteractionMode} from 'vee-validate'

setInteractionMode('eager')

const dictionary = {
	en: {
		messages: {
			requireTermsAndConditions: fieldName => 'You must agree to the terms and conditions.',
			requireAddress: fieldName => 'Please enter your address',
			uniqueEmail: fieldName => 'Email is already registered.',
			uniquePhone: fieldName => 'Phone number is already registered.',
			uniqueUserName: fieldName => 'Username is already registered.',
			validPhoneNumber: fieldName => 'Either phone number is not valid',
		}
	}
}

localize(dictionary)

extend('requireTermsAndConditions', value => {
	// do something and finally return an object like this one:
	return {
		valid: value === true, // or false
		data: {
			required: true // or false
		}
	}
}, {
	hasTarget: false,
	computesRequired: true
})
extend('requireAddress', value => {
	// do something and finally return an object like this one:
	return {
		valid: value !== '' || value !== null, // or false
		data: {
			required: true // or false
		}
	}
},)
extend('required', {
	...required,
	message: 'Please enter {_field_}',
})
extend('min', {
	...min,
	message: '{_field_} may not be less than {length} characters',
})
extend('max', {
	...max,
	message: '{_field_} may not be greater than {length} characters',
})
extend('email', {
	...email,
	message: 'Email must be valid',
})
extend('uniqueEmail', {
	getMessage() {
		return 'This email is already registered.'
	},
	validate(value) {
		return new Promise((resolve, reject) => {
			const endpoint = '/checkUniqueVolunteer'
			const data = {
				type: 'email',
				value: value
			}
			axios
				.post(endpoint, data)
				.then(response => {
					console.log('email is unique: %o', response.data)
					resolve(response.data)
				})
				.catch(error => {
					console.log('error in checking email')
					reject(error)
				})
		})
	}
})
extend('uniquePhone', {
	getMessage() {
		return 'This phone number is already registered.'
	},
	validate(value) {
		return new Promise((resolve, reject) => {
			const endpoint = '/checkUniqueVolunteer'
			const data = {
				type: 'phone',
				value: value
			}
			axios
				.post(endpoint, data)
				.then(response => {
					console.log('phone is unique: %o', response.data)
					resolve(response.data)
				})
				.catch(error => {
					console.log('error in checking email')
					reject(error)
				})
		})
	}
})
extend('validPhoneNumber', {
	getMessage: field => 'Invalid Phone Number.',
	validate(value) {
		return new Promise(resolve => {
			let valid = true
			// eslint-disable-next-line @typescript-eslint/no-var-requires
			const PhoneNumber = require('awesome-phonenumber')
			const phoneIN = new PhoneNumber(value, 'IN')
			const phoneUS = new PhoneNumber(value, 'US')
			if (phoneIN || phoneUS) {
				valid = phoneIN.isValid() || phoneUS.isValid()
			}
			resolve({valid})
		})
	}
})
extend('uniqueUserName', {
	getMessage() {
		return 'This phone number is already registered.'
	},
	validate(value) {
		return new Promise((resolve, reject) => {
			const endpoint = '/checkUniqueVolunteer'
			const data = {
				type: 'username',
				value: value
			}
			axios
				.post(endpoint, data)
				.then(response => {
					console.log('phone is unique: %o', response.data)
					resolve(response.data)
				})
				.catch(error => {
					console.log('error in checking email')
					reject(error)
				})
		})
	}
})

export default {
	name: 'VolunteerRegistration',
	components: {
		ValidationProvider,
		ValidationObserver,
		VuetifyGoogleAutocomplete
	},
	data() {
		const defaultForm = Object.freeze({
			firstName: '',
			lastName: '',
			username: '',
			password: '',
			phoneNumber: '',
			email: '',
			address: '',
			city: '',
			state: '',
			country: '',
			zip: '',
			terms: false
		})
		return {
			notifications: false,
			sound: true,
			widgets: false,
			form: Object.assign({}, defaultForm),
			show: false,
			conditions: false,
			snackbar: false,
			snackbarTimeout: 10000,
			snackbarText: 'Registration is in progress',
			snackbarColor: 'info',
			terms: false,
			googleAddress: '',
			defaultForm
		}
	},
	methods: {
		resetForm() {
			this.form = Object.assign({}, this.defaultForm)
			this.$refs.observer.reset()
		},
		submit() {
			this.$refs.observer.validate().then(result => {
				if (result) {
					this.snackbar = true
					this.registerVolunteer()
				}
			})
		},

		getAddressData: function (addressData, placeResultData, id) {
			this.googleAddress = addressData
			console.log(addressData)
			console.log(placeResultData)
			console.log(id)
			this.form.address = addressData.street_number + ' ' + addressData.route
			this.form.city = addressData.locality
			this.form.zip = addressData.postal_code
			this.form.state = addressData.administrative_area_level_1
			this.form.country = addressData.country
		},

		async registerVolunteer() {
			return new Promise((resolve, reject) => {
				this.snackbarColor = 'info'
				this.snackbarText = 'Registration is in progress.'
				const endpoint = '/insertupdateuser'
				axios
					.post(endpoint, this.form)
					.then(response => {
						console.log('saving the data')
						if (response && response.data) {
							console.log('response saved')
							this.snackbarColor = 'success'
							this.snackbarText = 'Registration is successful.'
						}
					})
					.catch(error => {
						this.snackbarColor = 'error'
						this.snackbarText = 'Something went wrong. We are looking into it.'
						this.snackbar = true
						console.log('error in saving  data')
						if (error) {
							console.log(error.stack)
						}
						reject(error)
					})
			})
		}
	},
}
</script>

<style scoped>

</style>
