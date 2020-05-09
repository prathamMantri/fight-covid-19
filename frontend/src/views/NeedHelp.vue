<template>
	<v-card class="px-5 elevation-0">
		<v-row sm="12">
			<v-col cols="12" sm="6" md="6" lg="4" xl="4">
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
								:v-model="form.address"
								:value = "form.address"
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
						v-model="form.zip"
						:error-messages="errors"
						label="Zip"
						required
					></v-text-field>
				</ValidationProvider>
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="12" sm="12" md="12" lg="12" xl="12">
				<v-card>
					<v-card-text class="pa-0">
						<keep-alive>
							<v-data-table
								v-if="searchResults.show"
								ref="searchResultsDataGrid"
								:headers="headers"
								:items="searchResults.data"
								:total-items="searchResults.length"
								:loading="searchResults.loading"
								:sortable="false"
								disable-pagination="true"
								hide-actions
								disable-initial-sort
								class="elevation-1"
								item-key="volunteerId"
							>
								<v-progress-linear
									slot="progress"
									color="primary"
									indeterminate
								/>

								<template
									slot="items"
									slot-scope="props"
								>
									<tr
										v-if="searchResults.length>0"
										:class="props.expanded?'activeRow':''"
										@click="props.expanded = !props.expanded"
									>
										<td>
											<v-icon
												size="10"
												class="uplusa"
												color="secondary base"
											>
												Column-1
											</v-icon>
										</td>
										<td
											class="text-xs-left column column-2 pr-3 pl-3 py-3"
										>
											Column-2
										</td>
										<td
											class="text-xs-left text-break-word column column-3 pr-3 pl-3 py-3"
											xs2
										>
											Column-3
										</td>
										<td
											class="text-xs-left align-start justify-center column Column-4  pr-3 pl-3 py-3"
											xs1
										>
											Column-4
										</td>
										<td
											xs1
											class="text-xs-right d-flex-inline align-start justify-center column pl-3 py-3 pr-2"
										>
											Column-5
										</td>
									</tr>
								</template>
								<template slot="no-data" color="#ffe57f">
									<v-layout class="align-center justify-center mt-1 mb-1 pt-1 pb-1">
										<v-flex
											class="pa-2 caption font-weight-medium elevation-1 ">
											We are sorry, no volunteers are available in your area. You can still post your request and check back if any volunteer becomes available in your area.
										</v-flex>
									</v-layout>
								</template>
							</v-data-table>
						</keep-alive>
					<!--<v-footer
          v-if="searchResults.data.length===0"
          color="#ffe57f"
          class="pa-2 caption elevation-1 "
        >
          <div
            v-if="searchResults.data.length===0"
            class="pl-3 font-weight-medium table-footer"
          >
            We are sorry, no volunteers are available in your area. You can still post your request and check back if any volunteer becomes available in your area.
          </div>
          <div
            v-else
            class="pl-3 font-weight-medium table-footer"
          >
            {{ searchResults.data.length }} {{ searchResults.data.length>1?'results': 'result' }} found.
          </div>
        </v-footer>-->
					</v-card-text>
				</v-card>
			</v-col>
		</v-row>
		<v-row sm="12">
			<v-col class="px-4" sm="12">
				<ValidationObserver ref="observer" v-slot="{ validate, reset }">
					<v-form ref="form" @submit.prevent="submit">
						<v-container fluid>
							<v-row>
								<v-col class="align-self-center" cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="first name" rules="required|min:2">
										<v-text-field
											v-model="form.firstName"
											:error-messages="errors"
											label="What is your first name?"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<v-col class="align-self-center"  cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider v-slot="{ errors }" name="last name" rules="required|min:2">
										<v-text-field
											v-model="form.lastName"
											:error-messages="errors"
											label="What is your last name?"
											required
										></v-text-field>
									</ValidationProvider>
								</v-col>
								<!--<v-col cols="12" sm="6" md="6" lg="4" xl="4">
                    <ValidationProvider v-slot="{ errors }" name="username" rules="required|min:4|">
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
                  </v-col>-->
								<v-col class="align-self-center"  cols="12" sm="6" md="6" lg="4" xl="4">
									<ValidationProvider
										ref="termsProvider"
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
											<!-- <v-flex>
                        <p class="v-messages theme&#45;&#45;light error&#45;&#45;text">
                          {{ errorMessageForCheckBox }}
                        </p>
                        <p class="v-messages theme&#45;&#45;light error&#45;&#45;text mt-4">
                          {{ errorMessage }}
                        </p>
                      </v-flex>-->
										</v-layout>
									</ValidationProvider>
								</v-col>
								<v-col class="align-self-center"  cols="12" sm="6" md="6" lg="4" xl="4">
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
								<v-col cols="12" sm="6" md="6" lg="4" xl="4">
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
								<v-col class="align-self-center"  cols="12" sm="6" md="6" lg="4" xl="4">
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
					</v-form>
				</ValidationObserver>
			</v-col>
		</v-row>
	</v-card>
</template>
<script>

import VuetifyGoogleAutocomplete from 'vuetify-google-autocomplete'
import {axios, _} from '../bootstrap'
import {required, email, min, max} from 'vee-validate/dist/rules'
import {extend, ValidationObserver, ValidationProvider, localize, setInteractionMode} from 'vee-validate'

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
		//VueGoogleAutocomplete
		VuetifyGoogleAutocomplete
	},
	computed: {},
	watch: {
		contactTypes: function (newValue, oldValue) {
			this.phoneDisabled = _.indexOf(this.contactTypes, 'phone') === -1
			this.emailDisabled = _.indexOf(this.contactTypes, 'email') === -1
		}
	},
	mounted() {
		this.form.address = 'xxxx'
	},
	data() {
		const defaultForm = Object.freeze({
			firstName: '',
			lastName: '',
			zip: '',
			phoneNumber: '',
			email: '',
			address:'',
			terms: ''
		})
		return {
			contactTypes: ['phone'],
			contactType: '',
			phoneDisabled: false,
			emailDisabled: true,
			checkboxValidate: false,
			needHelpDialog: false,
			canHelpDialog: false,
			notifications: false,
			sound: true,
			widgets: false,
			form: Object.assign({}, defaultForm),
			rules: {
				name: [val => (val || '').length > 0 || 'This field is required']
			},
			searchResults: {
				data : [],
				show: true
			},
			headers: [
				{
					text: 'STATUS',
					value: 'status',
					class: 'status-column pr-3 pl-4 pt-2 pb-1',
					sortable: false
				},
				{
					text: 'VOLUNTEER NAME',
					value: 'volunteerName',
					class: 'volunteer-column pr-3 pl-3 pt-2 pb-1',
					sortable: false
				},
				{
					text: 'PHONE NUMBER',
					value: 'phoneNumber',
					class: 'phone-number-column pr-3 pl-3 pt-2 pb-1',
					sortable: false
				},
				{
					text: 'EMAIL ADDRESS',
					value: 'vehicleIn',
					class: 'date-column pr-3 pl-3 pt-2 pb-1',
					sortable: false
				},
				{
					text: 'CAN HELP WITH',
					value: 'canHelpWith',
					class: 'help-category-column pr-3 pl-3 pt-2 pb-1',
					sortable: false
				},
			],
			conditions: false,
			snackbar: false,
			terms: false,
			defaultForm,
		}
	},

	methods: {
		resetForm() {
			this.form = Object.assign({}, this.defaultForm)
			console.log('cleared')
			this.$refs.form.reset()
		},
		submit() {
			this.$refs.observer.validate().then(result => {
				if (result) {
					//		this.snackbar = true
					console.log('Request posted')
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
			this.form.address = addressData.street_number ? addressData.street_number : '' + ' ' + addressData.route
			this.form.city = addressData.locality
			this.form.zip = addressData.postal_code
			this.form.state = addressData.administrative_area_level_1
			this.form.country = addressData.country
		},
		getGeoLocation() {
			// eslint-disable-next-line @typescript-eslint/no-this-alias
			const vm = this
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function (position) {

					vm.geocodeLatLng({lat:position.coords.latitude, lng:position.coords.longitude})

				},function() {
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

		test() {
			console.log('I am called')
		},

		geocodeLatLng(latlng) {
			// eslint-disable-next-line @typescript-eslint/no-this-alias
			const vm = this
			const geocoder = new google.maps.Geocoder()
			geocoder.geocode({location: latlng}, function(results, status) {
				if (status === 'OK') {
					if (results[0]) {
						console.log(results[0])
						if (!vm.form.address) {
							vm.form.address = results[0].formatted_address
						}
						vm.form.zip = results[0].address_components[7].long_name
					} else {
						window.alert('No results found')
					}
				} else {
					window.alert('Geocoder failed due to: ' + status)
				}
			})
		}
	}
}
</script>
<style scoped>
</style>
