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
                                <v-form ref="form" @submit.prevent="submit">
                                    <v-container fluid>
                                        <v-row>
                                            <v-col cols="12" sm="6">
                                                <v-text-field
                                                        v-model="form.first"
                                                        :rules="rules.name"
                                                        color="purple darken-2"
                                                        label="First name"
                                                        required
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6">
                                                <v-text-field
                                                        v-model="form.last"
                                                        :rules="rules.name"
                                                        color="blue darken-2"
                                                        label="Last name"
                                                        required
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="12">
                                                <v-textarea
                                                        v-model="form.bio"
                                                        color="teal"
                                                >
                                                    <template v-slot:label>
                                                        <div>
                                                            Bio <small>(optional)</small>
                                                        </div>
                                                    </template>
                                                </v-textarea>
                                            </v-col>
                                            <v-col cols="12" sm="6">
                                                <v-select
                                                        v-model="form.favoriteAnimal"
                                                        :items="animals"
                                                        :rules="rules.animal"
                                                        color="pink"
                                                        label="Favorite animal"
                                                        required
                                                ></v-select>
                                            </v-col>
                                            <v-col cols="12" sm="6">
                                                <v-slider
                                                        v-model="form.age"
                                                        :rules="rules.age"
                                                        color="orange"
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
                                        >Register</v-btn>
                                    </v-card-actions>
                                </v-form>
                                <v-divider></v-divider>
                                <GoogleMap/>
                            </v-card>
                        </v-dialog>
                    </div>
                </div>
            </v-col>
            <v-col cols="12" sm="6">
                <div class="text-center">
                    <div class="my-1">
                        <v-dialog v-model="canHelpDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                            <template v-slot:activator="{ on }">
                                <v-btn color="primary" dark x-large v-on="on">I CAN HELP</v-btn>
                            </template>
                            <v-card>
                                <v-toolbar dark color="primary">
                                    <v-btn icon dark @click="canHelpDialog = false">
                                        <v-icon>mdi-close</v-icon>
                                    </v-btn>
                                    <v-toolbar-title>Settings</v-toolbar-title>
                                    <v-spacer></v-spacer>
                                    <v-toolbar-items>
                                        <v-btn dark text @click="canHelpDialog = false">Save</v-btn>
                                    </v-toolbar-items>
                                </v-toolbar>
                                <v-list three-line subheader>
                                    <v-subheader>User Controls</v-subheader>
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-title>Content filtering</v-list-item-title>
                                            <v-list-item-subtitle>Set the content filtering level to restrict apps that
                                                can be downloaded
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-title>Password</v-list-item-title>
                                            <v-list-item-subtitle>Require password for purchase or use password to
                                                restrict purchase
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                </v-list>
                                <v-divider></v-divider>
                                <v-list three-line subheader>
                                    <v-subheader>General</v-subheader>
                                    <v-list-item>
                                        <v-list-item-action>
                                            <v-checkbox v-model="notifications"></v-checkbox>
                                        </v-list-item-action>
                                        <v-list-item-content>
                                            <v-list-item-title>Notifications</v-list-item-title>
                                            <v-list-item-subtitle>Notify me about updates to apps or games that I
                                                downloaded
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-list-item-action>
                                            <v-checkbox v-model="sound"></v-checkbox>
                                        </v-list-item-action>
                                        <v-list-item-content>
                                            <v-list-item-title>Sound</v-list-item-title>
                                            <v-list-item-subtitle>Auto-update apps at any time. Data charges may apply
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-list-item-action>
                                            <v-checkbox v-model="widgets"></v-checkbox>
                                        </v-list-item-action>
                                        <v-list-item-content>
                                            <v-list-item-title>Auto-add widgets</v-list-item-title>
                                            <v-list-item-subtitle>Automatically add home screen widgets
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                </v-list>
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
                    this.form.favoriteAnimal &&
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
        age: [
          val => val < 10 || 'I don\'t believe you!'
        ],
        animal: [val => (val || '').length > 0 || 'This field is required'],
        name: [val => (val || '').length > 0 || 'This field is required']
      },
      animals: ['Dog', 'Cat', 'Rabbit', 'Turtle', 'Snake'],
      conditions: false,
      content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. Sed dignissim lacinia nunc.',
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
