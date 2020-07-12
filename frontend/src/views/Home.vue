<template>
	<v-container class="pa-0">
		<vue-headful
			title="Home"
		/>
		<v-row align="center" sm="12">
			<!--	<v-carousel
          cycle
          height="400"
          interval="2000"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item
            v-for="(slide, i) in slides"
            :key="i"
          >
            <v-sheet
              :color="colors[i]"
              height="100%"
            >
              <v-row
                class="fill-height"
                align="center"
                justify="center"
              >
                <div class="display-3">STAY {{slide}}</div>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>-->
		</v-row>
		<v-row align="center" sm="12">
			<v-col cols="12" sm="6">
				<div class="text-center">
					<div class="my-1">
						<v-btn color="primary" x-large dark to="/needhelp">I NEED HELP</v-btn>
					</div>
				</div>
			</v-col>
			<v-col cols="12" sm="6">
				<div class="text-center">
					<div class="my-1">
						<v-btn color="primary" x-large dark to="/registervolunteer">I CAN HELP</v-btn>
					</div>
				</div>
			</v-col>
		</v-row>
		<v-row align="center" sm="12">
			<div>
				<p class="display-3 text-sm-center">Under construction.. Please keep patience!</p>
			</div>
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
				v-if="snackbarProps.color!=='info'"
				dark
				text
				@click="snackbarProps.color = false"
			>
				Close
			</v-btn>
			<v-progress-circular
				v-else
				indeterminate
				color="amber"
			></v-progress-circular>
		</v-snackbar>
	</v-container>
</template>

<script>

import vueHeadful from 'vue-headful'
import {mapActions, mapGetters, mapMutations, mapState} from 'vuex'

export default {
	name: 'Home',
	component: {vueHeadful},
	computed: {
		...mapState('auth', ['status']),
		...mapState('home', ['snackbarProps']),
	},
	data() {
		return {
			needHelpDialog: false,
			canHelpDialog: false,
			items: [
				{title: 'Dashboard', icon: 'mdi-view-dashboard'},
				{title: 'Contact', icon: 'mdi-phone-in-talk '},
				{title: 'About', icon: 'mdi-help-box'}
			],
			colors: [
				'indigo',
				'warning',
				'pink darken-2',
				'red lighten-1',
				'deep-purple accent-4'
			],
			slides: [
				'SAFE',
				'SECURE',
				'INSIDE',
				'HEALTHY',
				'AWARE'
			],
			miniVariant: true,
			expandOnHover: true,
		}
	},
	methods: {}
}
</script>

<style scoped>

</style>
