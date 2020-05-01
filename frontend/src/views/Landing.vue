<template>
  <v-container class="pa-0">
    <v-row align="center" sm="12">
      <v-col cols="12" sm="6">
        <div class="text-center">
          <div class="my-1">
            <v-dialog v-model="needHelpDialog" fullscreen hide-overlay
                      transition="dialog-bottom-transition">
              <template v-slot:activator="{ on }">
                <v-btn color="primary" x-large dark v-on="on">I NEED HELP</v-btn>
              </template>
              <v-card>
                <v-toolbar dark color="primary">
                  <v-btn icon dark @click="needHelpDialog = false">
                    <v-icon>mdi-close</v-icon>
                  </v-btn>
                  <v-toolbar-title>I NEED HELP</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-row align="center" sm="12">
                  <v-col cols="12" sm="8">
                    <v-form ref="form" @submit.prevent="submit">
                      <v-container fluid>
                        <v-row>
                          <v-col cols="6" sm="12" md="6" lg="4" xl="4">
                            <v-text-field
                              v-model="form.first"
                              :rules="rules.name"
                              label="First name"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="4" xl="4">
                            <v-text-field
                              v-model="form.last"
                              :rules="rules.name"
                              label="Last name"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="4" xl="4">
                            <v-text-field
                              v-model="form.phone"
                              label="Phone number"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="4" xl="4">
                            <v-text-field
                              v-model="form.email"
                              label="Email Address"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="4" xl="4">
                            <v-slider
                              v-model="form.age"
                              label="Age"
                              hint="Be honest"
                              min="1"
                              max="100"
                              thumb-label
                            ></v-slider>
                          </v-col>
                          <v-col cols="12">
                            <v-checkbox
                              v-model="form.terms"
                              color="green"
                            >
                              <template v-slot:label>
                                <div @click.stop="">
                                  Do you accept the
                                  <a href="javascript:;" @click.stop="terms = true">terms</a>
                                  and
                                  <a href="javascript:;" @click.stop="conditions = true">conditions?</a>
                                </div>
                              </template>
                            </v-checkbox>
                          </v-col>
                        </v-row>
                      </v-container>
                      <v-card-actions>
                        <v-btn text @click="resetForm">Cancel</v-btn>
                        <v-spacer></v-spacer>
                        <v-btn
                          :disabled="!formIsValid"
                          text
                          color="primary"
                          type="submit"
                        >Register
                        </v-btn>
                      </v-card-actions>
                    </v-form>
                  </v-col>
                  <v-col cols="12" sm="4">
                    <GoogleMap/>
                  </v-col>
                </v-row>
              </v-card>
            </v-dialog>
          </div>
        </div>
      </v-col>
      <v-col cols="12" sm="6">
        <div class="text-center">
          <div class="my-1">
            <v-dialog v-model="canHelpDialog" fullscreen hide-overlay
                      transition="dialog-bottom-transition">
              <template v-slot:activator="{ on }">
                <v-btn color="primary" x-large dark v-on="on">I CAN HELP</v-btn>
              </template>
              <v-card>
                <v-toolbar dark color="primary">
                  <v-btn icon dark @click="canHelpDialog = false">
                    <v-icon>mdi-close</v-icon>
                  </v-btn>
                  <v-toolbar-title>I CAN HELP</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-row align="center" sm="12">
                  <v-col cols="12" sm="8">
                    <v-form ref="form" @submit.prevent="submit">
                      <v-container fluid>
                        <v-row>
                          <v-col cols="6" sm="12" md="6" lg="6" xl="6">
                            <v-text-field
                              v-model="form.first"
                              :rules="rules.name"
                              label="First name"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="6" xl="6">
                            <v-text-field
                              v-model="form.last"
                              :rules="rules.name"
                              label="Last name"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="6" xl="6">
                            <v-text-field
                              v-model="form.phone"
                              label="Phone number"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="6" xl="6">
                            <v-text-field
                              v-model="form.email"
                              label="Email Address"
                              required
                            ></v-text-field>
                          </v-col>
                          <v-col cols="6" sm="12" md="6" lg="6" xl="6">
                            <v-slider
                              v-model="form.age"
                              label="Age"
                              hint="Be honest"
                              min="1"
                              max="100"
                              thumb-label
                            ></v-slider>
                          </v-col>
                          <v-col cols="12">
                            <v-checkbox
                              v-model="form.terms"
                              color="green"
                            >
                              <template v-slot:label>
                                <div @click.stop="">
                                  Do you accept the
                                  <a href="javascript:;" @click.stop="terms = true">terms</a>
                                  and
                                  <a href="javascript:;" @click.stop="conditions = true">conditions?</a>
                                </div>
                              </template>
                            </v-checkbox>
                          </v-col>
                        </v-row>
                      </v-container>
                      <v-card-actions>
                        <v-btn text @click="resetForm">Cancel</v-btn>
                        <v-spacer></v-spacer>
                        <v-btn
                          :disabled="!formIsValid"
                          text
                          color="primary"
                          type="submit"
                        >Register
                        </v-btn>
                      </v-card-actions>
                    </v-form>
                  </v-col>
                  <v-col cols="12" sm="4">
                    <div class="pa-2">
                    <GoogleMap/>
                    </div>
                  </v-col>
                </v-row>
              </v-card>
            </v-dialog>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GoogleMap from '@/components/GoogleMap'

export default {
  name: 'Landing',
  components: { GoogleMap },
  computed: {
    formIsValid () {
      return (
        this.form.first &&
          this.form.last &&
          this.form.terms
      )
    }
  },
  data () {
    const defaultForm = Object.freeze({
      first: '',
      last: '',
      bio: '',
      favoriteAnimal: '',
      age: null,
      terms: false
    })
    return {
      needHelpDialog: false,
      canHelpDialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || '').length > 0 || 'This field is required']
      },
      conditions: false,
      snackbar: false,
      terms: false,
      defaultForm
    }
  },
  methods: {
    resetForm () {
      this.form = Object.assign({}, this.defaultForm)
      this.$refs.form.reset()
    },
    submit () {
      this.snackbar = true
      this.resetForm()
    }
  }
}
</script>

<style scoped>

</style>
