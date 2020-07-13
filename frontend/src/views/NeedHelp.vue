<template>
	<v-container fluid class="px-0 mx-0">
		<v-card class="px-0 elevation-0">
			<v-card-text>
				<!--<v-banner
					single-line
				>
					Please enter zip code and/or address to get the list of volunteers available in your area.
				</v-banner>-->
				<v-row sm="12">
					<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
						<v-row>
							<v-col class="align-self-center" cols="12" sm="2" md="2" lg="1" xl="1">
								<v-icon color="primary" @click="getGeoLocation" class="text-left">place</v-icon>
							</v-col>
							<v-col class="align-self-center" cols="12" sm="10" md="10" lg="11" xl="11">
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
										ref="googleAddress"
										:v-model="address"
										:value="address"
										:error-messages="errors"
										required="true"
										:clearable="true"
										label="Where are you located?"
										placeholder=""
										v-on:placechanged="getAddressData"
									>
									</vuetify-google-autocomplete>
								</ValidationProvider>
							</v-col>
						</v-row>
					</v-col>
					<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
						<ValidationProvider v-slot="{ errors }" name="zip" rules="required">
							<v-text-field
								v-model="zip"
								:error-messages="errors"
								label="Zip"
								required
							></v-text-field>
						</ValidationProvider>
					</v-col>
				</v-row>
				<v-row sm="12">
					<v-col class="align-self-center justify-start" cols="12" sm="12" md="12" lg="12" xl="12">
						<v-card outlined>
							<v-card-title>
								<span class="primary--text">Registered Volunteers</span>
							</v-card-title>
							<v-card-text class="pa-0">
								<keep-alive>
									<v-data-table
										:headers="headers"
										:items="volunteers.list"
										:server-items-length="volunteers.list.length"
										:disable-pagination="true"
										:loading="volunteerListLoading"
										hide-default-footer
										class="elevation-1"
									>
										<template v-slot:item.volunteerName="{ item }">
											{{ item.firstName }} {{item.lastName}}
											<v-tooltip bottom>
												<template v-slot:activator="{ on }">
													<v-icon :color="item.verified === 1?'success': 'primary'" dark size="small" left v-on="on">
														{{item.verified === 1 ? 'mdi-checkbox-marked-circle' : 'info'}}
													</v-icon>
												</template>
												<span>{{item.verified === 1 ? 'Verified' : 'Not verified'}}</span>
											</v-tooltip>
										</template>
										<template v-slot:no-data>
											<div class="text-center subtitle-1">
												<span class="primary--text">{{noDataText}}</span>
											</div>
										</template>
										<template v-slot:progress>
											<v-progress-linear color="primary" :height="6" :indeterminate="progress"></v-progress-linear>
										</template>
										<template v-slot:loading>
											<div class="text-center subtitle-1">
												<span class="primary--text">Retrieving volunteer list from your area</span>
											</div>
										</template>
									</v-data-table>
								</keep-alive>
							</v-card-text>
						</v-card>
					</v-col>
				</v-row>
				<v-row sm="12">
					<v-col class="align-start justify-start" cols="12" sm="12" md="12" lg="12" xl="12">
						<v-dialog v-model="dialog" persistent max-width="1000px">
							<template v-slot:activator="{ on }">
								<v-btn color="primary" dark v-on="on">POST REQUEST</v-btn>
							</template>
							<v-card>
								<v-card-title>
									<span class="headline">POST REQUEST</span>
								</v-card-title>
								<v-card-text>
									<ValidationObserver ref="observer" v-slot="{ validate, reset }">
										<v-form ref="form" @submit.prevent="submit">
											<v-row>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="first name" rules="required|min:2">
														<v-text-field
															v-model="form.firstName"
															:error-messages="errors"
															label="Firstname"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="last name" rules="required|min:2">
														<v-text-field
															v-model="form.lastName"
															:error-messages="errors"
															label="Lastname"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="username" rules="required|min:4|">
														<v-text-field
															v-model="form.username"
															:error-messages="errors"
															label="Username"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="password" rules="required|min:8">
														<v-text-field
															v-model="form.password"
															label="Password (MD5 Encryption Protected)"
															:type="passwordShow ? 'text' : 'password'"
															:error-messages="errors"
															:append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
															@click:append="passwordShow = !passwordShow"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider
														ref="contactTypeProvider"
														v-slot="{failedRules, errors, flags}"
														vid="contactType"
														name="contactType"
														mode="eager"
														:events="['none']"
														:rules="{ required: { allowFalse: false } }"
														:immediate="false"
														:persist="false"
														:bails="false"
														:debounce="10"
														tag="span"
														:slim="true"
													>
														<header class="text-left pl-2 pb-1">Volunteer may reach you through</header>
														<v-layout
															row
															wrap
														>
															<v-checkbox
																v-model="contactTypes"
																type="checkbox"
																:ripple="false"
																:error-messages="errors"
																on-icon="mdi-checkbox-marked"
																off-icon="mdi-checkbox-blank-outline"
																transition="fade"
																class="check-box-label pr-4 py-0 pl-4 ma-0"
																name="contactType"
																label="Phone"
																value="phone"
																hide-details
																@change="resetCheckboxValidate"
															/>
															<v-checkbox
																v-model="contactTypes"
																type="checkbox"
																:error-messages="errors"
																:ripple="false"
																on-icon="mdi-checkbox-marked"
																off-icon="mdi-checkbox-blank-outline"
																class="check-box-label pl-4 py-0 pr-0 ma-0"
																name="contactType"
																label="Email"
																value="email"
																hide-details
																@change="resetCheckboxValidate"
															/>
														</v-layout>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="phone number"
														rules="required|min:10|validPhoneNumber|max:13"
														:disabled="phoneDisabled">
														<v-text-field
															v-model="form.phoneNumber"
															:error-messages="errors"
															:disabled="phoneDisabled"
															label="Phone number(US or India)"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="email address" rules="required|email"
														:disabled="emailDisabled">
														<v-text-field
															v-model="form.email"
															:disabled="emailDisabled"
															:error-messages="errors"
															label="Email Address"
															required
														></v-text-field>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
													<ValidationProvider v-slot="{ errors }" name="help categories" rules="required">
														<v-combobox
															v-model="form.helpCategorySelected"
															:filer="filter"
															:items="helpCategory"
															:search-input.sync="search"
															:error-messages="errors"
															item-text="helpCategoryText"
															item-value="helpCategoryId"
															label="I can help with (Please type to add more)"
															multiple
															small-chips
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
																	>close
																	</v-icon>
																</v-chip>
															</template>
															<template v-slot:no-data>
																<v-list-item>
																	<v-list-item-content>
																		<v-list-item-title>
																			No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to
																			create a new one
																		</v-list-item-title>
																	</v-list-item-content>
																</v-list-item>
															</template>
														</v-combobox>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="12" md="12" lg="12" xl="12">
													<ValidationProvider v-slot="{ errors }" name="help description" rules="required">
														<v-textarea
															v-model="form.helpDesc"
															:error-messages="errors"
															background-color="grey lighten-2"
															label="Please describe your request"
															counter="1000"
															dense
															required
														></v-textarea>
													</ValidationProvider>
												</v-col>
												<v-col class="align-self-center" cols="12" sm="6" md="6" lg="6" xl="6">
													<ValidationProvider
														ref="termsProvider"
														v-slot="{failedRules, errors, flags}"
														vid="termsAndConditions"
														name="Terms And Conditions"
														mode="eager"
														:events="['none']"
														:rules="{ required: { allowFalse: false } }"
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
												<v-col class="text-right" sm="6" md="6" lg="6" xl="6">
													<v-row sm="12">
														<v-col class="text-left" sm="6" md="6" lg="6" xl="6">
															<v-btn @click="resetForm">CANCEL</v-btn>
														</v-col>
														<v-col class="text-left" sm="6" md="6" lg="6" xl="6">
															<v-btn color="primary" type="submit">REGISTER</v-btn>
														</v-col>
													</v-row>
												</v-col>
											</v-row>
										</v-form>
									</ValidationObserver>
								</v-card-text>
							</v-card>
						</v-dialog>
					</v-col>
				</v-row>
			</v-card-text>
		</v-card>
	</v-container>
</template>
<script>

import VuetifyGoogleAutocomplete from 'vuetify-google-autocomplete'
import {axios, _} from '../bootstrap'
import {required, email, min, max} from 'vee-validate/dist/rules'
import {extend, ValidationObserver, ValidationProvider, localize, setInteractionMode} from 'vee-validate'
import {mapActions, mapGetters, mapMutations, mapState} from 'vuex'

setInteractionMode('eager')

const dictionary = {
	en: {
		messages: {
			requireTermsAndConditions: fieldName => 'You must agree to the terms and conditions.',
			requiredContactType: fieldName => 'You must select either contact type.',
			requireAddress: fieldName => 'Please enter your address',
			validPhoneNumber: fieldName => 'Phone number is not valid',
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
extend('requiredContactType', value => {
	// do something and finally return an object like this one:
	return {
		valid: this.value.length > 0, // or false
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

export default {
	name: 'NeedHelp',
	components: {
		ValidationProvider,
		ValidationObserver,
		VuetifyGoogleAutocomplete
	},
	computed: {},
	watch: {
		contactTypes: function (newValue, oldValue) {
			this.phoneDisabled = _.indexOf(this.contactTypes, 'phone') === -1
			this.emailDisabled = _.indexOf(this.contactTypes, 'email') === -1
		},
		zip: function (newValue, oldValue) {
			this.progress = true
			this.volunteerListLoading = true
			this.getVolunteers(newValue)
		},
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
	data() {
		const defaultForm = Object.freeze({
			userType: 'REQUESTER',
			firstName: '',
			lastName: '',
			zip: '',
			phoneNumber: '',
			email: '',
			address: '',
			terms: '',
			helpCategorySelected: []
		})
		return {
			contactTypes: ['phone'],
			contactType: '',
			phoneDisabled: false,
			emailDisabled: true,
			checkboxValidate: false,
			notifications: false,
			sound: true,
			widgets: false,
			dialog: false,
			form: Object.assign({}, defaultForm),
			rules: {
				name: [val => (val || '').length > 0 || 'This field is required']
			},
			volunteers: {
				list: [],
				show: true
			},
			passwordShow: false,
			helpCategory: [],
			noDataText: 'Please enter zip and/or address code to get the list of volunteers available in your area.',
			progress: false,
			volunteerListLoading: false,
			headers: [
				{
					text: 'VOLUNTEER NAME',
					value: 'volunteerName',
					class: 'volunteer-column',
					sortable: false
				},
				/*	{
					text: 'PHONE',
					value: 'phoneNumber',
					class: 'phone-number-column',
					sortable: false
				},
				{
					text: 'EMAIL',
					value: 'emailAddress',
					class: 'email-column',
					sortable: false
				},*/
				{
					text: 'CAN HELP WITH',
					class: 'help-category-column',
					value: 'helpCategoryList',
					sortable: false
				},
			],
			conditions: false,
			terms: false,
			defaultForm,
			zip: '',
			address: '',
			search: ''
		}
	},
	methods: {
		resetForm() {
			this.form = Object.assign({}, this.defaultForm)
			console.log('cleared')
			this.$refs.form.reset()
			this.dialog = false
		},
		submit() {
			this.$refs.observer.validate().then(result => {
				if (result) {
					console.log('Request posted')
					this.dialog = false
				} else {
					console.log('Invalid details')
				}
			})
		},
		resetCheckboxValidate() {
			if (this.contactType.length > 0) {
				this.checkboxValidate = false
				this.errorMessageForCheckBox = ''
			}
		},
		getAddressData: function (addressData, placeResultData, id) {
			console.log(addressData)
			console.log(placeResultData)
			console.log(id)
			this.address = (addressData.street_number ? addressData.street_number : '') + ' ' + addressData.route
			this.form.city = addressData.locality
			this.zip = addressData.postal_code
			this.form.state = addressData.administrative_area_level_1
			this.form.country = addressData.country
		},
		getGeoLocation() {
			// eslint-disable-next-line @typescript-eslint/no-this-alias
			const vm = this
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function (position) {

					vm.geocodeLatLng({lat: position.coords.latitude, lng: position.coords.longitude})

				}, function () {
					vm.handleLocationError()
				})
			} else {
				// Browser doesn't support Geolocation
				vm.handleLocationError()
			}
		},
		handleLocationError() {
			this.latLngPosition.lat = 40.730610
			this.latLngPosition.lng = -73.935242
			this.map.setCenter(this.latLngPosition)
		},

		getVolunteers(zip) {
			return new Promise((resolve, reject) => {
				this.color = 'info'
				this.text = 'Registration is in progress.'
				const endpoint = '/getvolunteers'
				if (zip) {
					axios.get(endpoint, {
						responseType: 'json',
						params: {zip: zip}
					})
						.then(response => {
							console.log('data retrieved')
							if (response && response.data) {
								this.volunteers.list = response.data
								this.progress = false
								this.volunteerListLoading = false
								if (!this.volunteers.list || this.volunteers.list.length === 0) {
									this.noDataText = 'Sorry, currently there are no volunteers available in your area.'
								}
								console.log(this.volunteers.list)
							}
						})
						.catch(error => {
							this.color = 'error'
							this.text = 'Something went wrong. We are looking into it.'
							this.progress = false
							this.volunteerListLoading = false
							console.log('error in retrieving  data')
							if (error) {
								console.log(error.stack)
							}
							reject(error)
						})
				} else {
					this.progress = false
					this.volunteerListLoading = false
					this.noDataText = 'Please enter zip and/or address code to get the list of volunteers available in your area.'
				}
			})
		},

		geocodeLatLng(latlng) {
			// eslint-disable-next-line @typescript-eslint/no-this-alias
			const vm = this
			const geocoder = new google.maps.Geocoder()
			geocoder.geocode({location: latlng}, function (results, status) {
				if (status === 'OK') {
					if (results[0]) {
						console.log(results[0])
						if (!vm.address) {
							vm.address = results[0].formatted_address
						}
						vm.zip = results[0].address_components[7].long_name
					} else {
						window.alert('No results found')
					}
				} else {
					window.alert('Geocoder failed due to: ' + status)
				}
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
  >>> .v-data-table__mobile-row__header {
    font-weight: 400;
  }

  >>> .v-data-table-header-mobile {
    display: none;
  }
</style>
