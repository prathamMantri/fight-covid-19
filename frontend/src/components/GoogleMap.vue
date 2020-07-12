<template>
	<v-container class="pa-0">
		<v-row align="center" sm="12">
			<GmapMap
				ref="mapRef"
				:options="{
					zoomControl: true,
					mapTypeControl: false,
					scaleControl: true,
					streetViewControl: false,
					rotateControl: false,
					fullscreenControl: true,
					disableDefaultUi: false
				}"
				:center="latLngPosition"
				:zoom="15"
				map-type-id="terrain"
				style="width:100%; height: 550px; "
			>
				<GmapMarker
					ref="myMarker"
					:key="index"
					v-for="(m, index) in markers"
					:position="m.position"
					:clickable="true"
					:draggable="true"
					@click="center=m.position"
				/>
			</GmapMap>
		</v-row>
	</v-container>
</template>

<script>
import { gmapApi } from 'vue2-google-maps'


export default {
	name: 'GoogleMap',
	computed: {
		google: gmapApi,
	},

	watch:{
		googleAddress: function (newValue, oldValue) {
			console.log('addressChanged')
			this.latlngPosition.lat = newValue.latitude
			this.latlngPosition.lng = newValue.longitude
			this.setPlace(newValue)
		}
	},

	props: {
		googleAddress: {
			default: function () {
				return {
					lat: 40.730610,
					lng: -73.935242
				}
			},
			type: Object,
		},
	},
	mounted() {
		this.$refs.mapRef.$mapPromise.then(map => {
			this.map = map
			//		this.initMap(map)
		//	this.addMarker()
		})
	},
	activated() {
	//	this.initMap()
	},
	data() {
		return {
			map: {},
			latLngPosition: {},
			markers: [],
			places: []
		}
	},
	methods: {
		initMap() {
			// eslint-disable-next-line @typescript-eslint/no-this-alias
			const vm = this
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function (position) {
					vm.latLngPosition = {
						lat: position.coords.latitude,
						lng: position.coords.longitude
					}
					vm.map.setCenter(vm.latLngPosition)
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
		addMarker() {
			if (this.googleAddress) {
				const marker = {
					lat: this.googleAddress.latitude,
					lng: this.googleAddress.longitude
				}
				this.markers.push({position: marker})
				this.places.push(this.currentPlace)
				this.center = marker
			}
		},

		setPlace(place) {
			this.currentPlace = place
		},

	}
}
</script>

<style scoped>

</style>
