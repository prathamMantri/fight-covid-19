<template>
	<v-card class="px-5 elevation-0">
		<v-row sm="12">
			<v-col class="px-4" sm="12">
				<ValidationObserver ref="observer" v-slot="{ validate, reset }">
					<v-form ref="form" @submit.prevent="submit" autocomplete="off">
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
									<ValidationProvider v-slot="{ errors }" name="phone number"
										rules="required|min:10|uniquePhone|validPhoneNumber|max:13">
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
											:v-model="form.address1"
											:value="form.address1"
											append-icon="place"
											:error-messages="errors"
											required="true"
											:clearable="true"
											label="Where are you located"
											placeholder=""
											v-on:placechanged="getAddressData"
										>
										</vuetify-google-autocomplete>
									</ValidationProvider>
								</v-col>
								<!--<v-col cols="12" sm="6" md="6" lg="4" xl="4">
                  <ValidationProvider v-slot="{ errors }" name="City" rules="required">
                    <v-text-field
                      v-model="form.city"
                      :error-messages="errors"
                      label="City"
                      required
                    ></v-text-field>
                  </ValidationProvider>
                </v-col>-->
								<!--<v-col cols="12" sm="6" md="6" lg="4" xl="4">
                  <ValidationProvider v-slot="{ errors }" name="state" rules="required">
                    <v-text-field
                      v-model="form.state"
                      :error-messages="errors"
                      label="State"
                      required
                    ></v-text-field>
                  </ValidationProvider>
                </v-col>-->
								<!--<v-col cols="12" sm="6" md="6" lg="4" xl="4">
                  <ValidationProvider v-slot="{ errors }" name="country" rules="required">
                    <v-text-field
                      v-model="form.country"
                      :error-messages="errors"
                      label="Country"
                      required
                    ></v-text-field>
                  </ValidationProvider>
                </v-col>-->
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
								<!--<v-col cols="12" sm="6" md="6" lg="4" xl="4">
                  <ValidationProvider v-slot="{ errors }" name="help categories" rules="required">
                    <v-combobox
                      v-model="form.helpCategorySelected"
                      :filer="filter"
                      :items="helpCategory"
                      :search-input.sync="search"
                      :error-messages="errors"
                      item-text="helpCategoryText"
                      item-value="helpCategoryId"
                      label="I can help with (Please type to add more options)"
                      multiple
                      small-chipsV
                    >
                      <template v-slot:selection="{ attrs, item, parent, selected }">
                        <v-chip
                          v-if="item === Object(item)"
                          v-bind="attrs"
                          :input-value="selected"
                          label
                          color="primary"
                          small
                        >
                          <span class="pr-2">
                            {{ item.helpCategoryText }}
                          </span>
                          <v-icon
                            small
                            @click="parent.selectItem(item)"
                          >close</v-icon>
                        </v-chip>
                      </template>
                      <template v-slot:no-data>
                        <v-list-item>
                          <v-list-item-content>
                            <v-list-item-title>
                              No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                            </v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </template>
                    </v-combobox>
                  </ValidationProvider>
                </v-col>-->
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
												color="primary"
												type="submit"
											>REGISTER
											</v-btn>
										</v-col>
									</v-row>
								</v-col>
							</v-row>
						</v-card-actions>
						<input autocomplete="false" name="hidden" type="text" v-show="addressAutocompleteFix">
					</v-form>
				</ValidationObserver>
			</v-col>
		</v-row>
		<v-snackbar
			v-model="snackbarProps.snackbar"
			:color="snackbarProps.color"
			:multi-line="true"
			:timeout="snackbarProps.timeout"
			:top="true"
		>
			{{ snackbarProps.text }}
			<v-btn
				v-if="snackbarProps.color!='info'"
				dark
				text
				@click="snackbarProps.snackbar = false"
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
			internalError: fieldName => 'Internal Error occured',
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
			userType: 'VOLUNTEER',
			firstName: '',
			lastName: '',
			username: '',
			password: '',
			phoneNumber: '',
			email: '',
			address1: '',
			address2: '',
			city: '',
			state: '',
			country: '',
			zip: '',
			terms: false,
			helpCategorySelected: []
		})
		return {
			addressAutocompleteFix: false,
			notifications: false,
			sound: true,
			widgets: false,
			form: Object.assign({}, defaultForm),
			show: false,
			conditions: false,
			terms: false,
			googleAddress: '',
			helpCategory: [],
			search: '',
			defaultForm
		}
	},
	beforeMount() {
		this.getHelpCategories()
	},
	watch: {
		'form.helpCategorySelected'(val, prev) {
			if (val.length === prev.length) return
			this.form.helpCategorySelected = val.map(v => {
				if (typeof v === 'string') {
					v = {
						helpCategoryText: v.toUpperCase(),
						helpCategoryDesc: '',
						helpCategoryStatus: 'PENDING',
						helpCategoryVisibility: 0
					}
					this.helpCategory.push(v)
					this.form.helpCategorySelected.push(v)
					this.nonce++
				}
				return v
			})
		},
	},
	computed: {
		...mapState('home', ['snackbarProps']),
	},
	methods: {
		resetForm() {
			this.form = Object.assign({}, this.defaultForm)
			this.$refs.observer.reset()
		},
		submit() {
			this.$refs.observer.validate().then(result => {
				if (result) {
					this.snackbarProps.snackbar = true
					this.registerVolunteer()
				}
			})
		},
		getAddressData: function (addressData, placeResultData, id) {
			this.googleAddress = addressData
			console.log(addressData)
			console.log(addressData.locality)
			console.log(addressData.administrative_area_level_1)
			console.log(addressData.postal_code)
			console.log(addressData.country)
			this.form.address1 = addressData ? addressData.street_number + ' ' + (addressData.route ? addressData.route : '') : this.form.address1
			this.form.address2 = addressData.name
			this.form.city = addressData.locality ? addressData.locality : ''
			this.form.zip = addressData.postal_code ? addressData.postal_code : this.form.zip
			this.form.state = addressData.administrative_area_level_1 ? addressData.administrative_area_level_1 : this.form.state
			this.form.country = addressData.country ? addressData.country : this.form.country
		},
		async registerVolunteer() {
			return new Promise((resolve, reject) => {
				this.snackbarProps.color = 'info'
				this.snackbarProps.text = 'Registration is in progress.'
				const endpoint = '/registeruser'
				axios.post(endpoint, this.form)
					.then(response => {
						console.log('saving the data')
						if (response && response.data) {
							console.log('response saved')
							this.snackbarProps.color = 'success'
							this.snackbarProps.text = 'Registration is successful.'
						}
					})
					.catch(error => {
						/*this.$refs.observer.validate().then(result => {
                if (result) {
                  this.snackbar = true
                  this.registerVolunteer()
                }
              })*/
						this.snackbarProps.color = 'error'
						this.snackbarProps.text = 'Something went wrong. We are looking into it.'
						this.snackbarProps.snackbar = true
						console.log('error in saving  data')
						if (error) {
							console.log(error.stack)
						}
						reject(error)
					})
			})
		},
		getHelpCategories() {
			return new Promise((resolve, reject) => {
				const endpoint = '/gethelpcategories'
				axios.get(endpoint, {
					responseType: 'json',
					params: {}
				})
					.then(response => {
						console.log('data retrieved')
						if (response && response.data) {
							this.helpCategory = response.data
							console.log(this.helpCategory)
						}
					})
					.catch(error => {
						console.log('error in retrieving  data')
						if (error) {
							console.log(error.stack)
						}
						reject(error)
					})
			})
		},
		filter(item, queryText, itemText) {
			const hasValue = val => val != null ? val : ''
			const text = hasValue(itemText)
			const query = hasValue(queryText)
			return text.toString()
				.toUpperCase()
				.indexOf(query.toString().toUpperCase()) > -1
		},
	}
}
</script>

<style lang="stylus" scoped>

</style>
