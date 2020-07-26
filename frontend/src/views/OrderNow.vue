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
					<v-data-table
						:headers="headers"
						:items="desserts"
						sort-by="itemName"
						class="elevation-0 col-sm-12"
					>
						<template v-slot:top>
							<v-toolbar flat color="white">
								<v-toolbar-title class="secondary--text">My Grocery List</v-toolbar-title>
								<v-divider
									class="mx-4"
									inset
									vertical
								></v-divider>
								<div class="kalam-font pt-1 font-weight-bold title secondary--text">Online Kirana - Offline Corona</div>
								<v-spacer></v-spacer>
								<v-dialog v-model="dialog" max-width="500px">
									<template v-slot:activator="{ on, attrs }">
										<v-btn
											color="secondary"
											dark
											class="mb-2"
											v-bind="attrs"
											v-on="on"
										>New Item
										</v-btn>
									</template>
									<v-card>
										<v-card-title>
											<span class="headline">{{ formTitle }}</span>
										</v-card-title>

										<v-card-text>
											<v-container>
												<v-row>
													<v-col cols="12" sm="6" md="4">
														<v-text-field v-model="editedItem.itemName" label="Item Name"></v-text-field>
													</v-col>
													<v-col cols="12" sm="6" md="4">
														<v-text-field v-model="editedItem.brand" label="Brand"></v-text-field>
													</v-col>
													<v-col cols="12" sm="6" md="4">
														<v-text-field v-model="editedItem.quantity" label="Quantity (Kg/Unit)"></v-text-field>
													</v-col>
													<v-col cols="12" sm="6" md="4">
														<v-text-field v-model="editedItem.notes" label="Notes/Comment"></v-text-field>
													</v-col>
												</v-row>
											</v-container>
										</v-card-text>

										<v-card-actions>
											<v-spacer></v-spacer>
											<v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
											<v-btn color="blue darken-1" text @click="save">Save</v-btn>
										</v-card-actions>
									</v-card>
								</v-dialog>
							</v-toolbar>
						</template>
						<template v-slot:item.actions="{ item }">
							<v-icon
								small
								class="mr-2"
								@click="editItem(item)"
							>
								mdi-pencil
							</v-icon>
							<v-icon
								small
								@click="deleteItem(item)"
							>
								mdi-delete
							</v-icon>
						</template>
						<template v-slot:no-data>
							<v-btn color="primary" @click="initialize">Reset</v-btn>
						</template>
					</v-data-table>
					<!--<v-col class="align-start justify-start" cols="12" sm="12" md="12" lg="12" xl="12">
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
          </v-col>-->
				</v-row>
			</v-card-text>
		</v-card>
	</v-container>
</template>
<script>

import {_, axios} from '../bootstrap'
import {email, max, min, required} from 'vee-validate/dist/rules'
import {extend, localize, setInteractionMode} from 'vee-validate'

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
	name: 'OrderNow',
	components: {},
	computed: {
		formTitle() {
			return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
		},
	},
	watch: {
		dialog(val) {
			val || this.close()
		},
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
			/*headers: [
          {
            text: 'VOLUNTEER NAME',
            value: 'volunteerName',
            class: 'volunteer-column',
            sortable: false
          },
          /!*	{
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
            },*!/
          {
            text: 'CAN HELP WITH',
            class: 'help-category-column',
            value: 'helpCategoryList',
            sortable: false
          },
        ],*/
			conditions: false,
			terms: false,
			defaultForm,
			zip: '',
			address: '',
			search: '',
			dialog: false,
			headers: [
				{
					text: 'Item',
					align: 'start',
					sortable: false,
					value: 'itemName',
				},
				{text: 'Brand', value: 'brand'},
				{text: 'Quantity (Kg/Unit)', value: 'quantity'},
				{text: 'Note/Comment', value: 'notes'},
				{text: 'Actions', value: 'actions', sortable: false},
			],
			desserts: [],
			editedIndex: -1,
			editedItem: {
				itemName: '',
				brand: '',
				quantity: 0,
				notes: '',
			},
			defaultItem: {
				itemName: '',
				brand: '',
				quantity: 0,
				notes: '',
			},


		}
	},
	created () {
		this.initialize(
			console.log('Initialised')
		)
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



		initialize () {
			this.desserts = [
				{
					itemName: 'Frozen Yogurt',
					brand: 159,
					quantity: 6.0,
					notes: '',
				},
				{
					itemName: 'Ice cream sandwich',
					brand: 237,
					quantity: 9.0,
					notes: '',

				},
				{
					itemName: 'Eclair',
					brand: 262,
					quantity: 16.0,
					notes: '',

				},
				{
					itemName: 'Cupcake',
					brand: 305,
					quantity: 3.7,
					notes: '',

				},
				{
					itemName: 'Gingerbread',
					brand: 356,
					quantity: 16.0,
					notes: '',

				},
				{
					itemName: 'Jelly bean',
					brand: 375,
					quantity: 0.0,
					notes: '',

				},
				{
					itemName: 'Lollipop',
					brand: 392,
					quantity: 0.2,
					notes: '',

				},
				{
					itemName: 'Honeycomb',
					brand: 408,
					quantity: 3.2,
					notes: '',

				},
				{
					itemName: 'Donut',
					brand: 452,
					quantity: 25.0,
					notes: '',

				},
				{
					itemName: 'KitKat',
					brand: 518,
					quantity: 26.0,
					notes: '',

				},
			]
		},

		editItem (item) {
			this.editedIndex = this.desserts.indexOf(item)
			this.editedItem = Object.assign({}, item)
			this.dialog = true
		},

		deleteItem (item) {
			const index = this.desserts.indexOf(item)
			confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
		},

		close () {
			this.dialog = false
			this.$nextTick(() => {
				this.editedItem = Object.assign({}, this.defaultItem)
				this.editedIndex = -1
			})
		},

		save () {
			if (this.editedIndex > -1) {
				Object.assign(this.desserts[this.editedIndex], this.editedItem)
				console.log(this.editedIndex)
			} else {
				this.desserts.push(this.editedItem)
			}
			this.close()
		},






	}
}
</script>
<style lang="stylus" scoped>

  .kalam-font{
    font-family : Kalam, Roboto !important
    font-weight : 700;
  }

  >>> .v-data-table__mobile-row__header {
    font-weight: 400;
  }


  >>> .v-data-table-header-mobile {
    display: none;
  }
</style>
