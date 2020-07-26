<template>
	<v-container class="px-0 pt-4">
		<vue-headful
			title="Home"
		/>
		<v-row align="center" sm="12">
			<v-carousel
				height="650"
				interval="2000"
				hide-delimiter-background
				show-arrows-on-hover
			>
				<v-carousel-item
					v-for="(slide,i) in carousalSlides"
					:key="i"
					reverse-transition="fade-transition"
					transition="fade-transition"
				>
					<v-img
						:src="require('@/assets/images/'+slide.src)"
						height="650"
						contain
					>
					</v-img>
				</v-carousel-item>
			</v-carousel>
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
import {mapState} from 'vuex'

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
			carousalSlides: [
				{
					src: 'lockdownvalidukan.png',
				},
				{
					src: 'lockdownvalidukan-logo.png',
				},
				{
					src: 'lockdownvalidukan-features.png',
				},
				{
					src: 'lockdownvalidukan-contact.png',
				},

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
