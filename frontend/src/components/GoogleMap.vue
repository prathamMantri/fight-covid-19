<template>
  <v-container class="pa-0">
    <v-row align="center" sm="12">
      <GmapMap
        ref="mapRef"
        :options="{
               zoomControl: true,
               mapTypeControl: false,
               scaleControl: false,
               streetViewControl: false,
               rotateControl: false,
               fullscreenControl: true,
               disableDefaultUi: false
            }"
        :center="latLngPosition"
        :zoom="7"
        map-type-id="terrain"
        style="width:100% ; height: 500px; "
      >
        <GmapMarker
          ref="myMarker"
          :key="index"
          v-for="(m, index) in markers"
          :position="google && new google.maps.LatLng(latLngPosition.lat, latLngPosition.lng)"
          :clickable="true"
          :draggable="true"
          @click="center=m.position"
        />
      </GmapMap>
    </v-row>
  </v-container>
</template>

<script>
import Vue from 'vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import { gmapApi } from 'vue2-google-maps'
import { googleMapKey } from '@/bootstrap'

Vue.use(VueGoogleMaps, {
  load: {
    key: googleMapKey,
    libraries: 'places' // This is required if you use the Autocomplete plugin
    // OR: libraries: 'places,drawing'
    // OR: libraries: 'places,drawing,visualization'
    // (as you require)

    /// / If you want to set the version, you can do so:
    // v: '3.26',
  }
})
export default {
  name: 'GoogleMap',
  computed: {
    google: gmapApi
  },
  mounted () {
    // eslint-disable-next-line @typescript-eslint/no-this-alias
    const vm = this
    vm.$refs.mapRef.$mapPromise.then((map) => {
      vm.map = map
      vm.initMap(map)
    })
  },
  activated () {
    this.initMap()
  },
  data () {
    return {
      latLngPosition: {
        lat: 40.6971494,
        lng: -74.2598677
      },
      map: {}
    }
  },
  methods: {
    initMap () {
      // eslint-disable-next-line @typescript-eslint/no-this-alias
      const vm = this
      const infoWindow = vm.google.maps.InfoWindow
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          vm.latLngPosition = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          }

          infoWindow.setPosition(vm.latLngPosition)
          infoWindow.setContent('Location found.')

          infoWindow.open(vm.map)
          vm.map.setCenter(vm.latLngPosition)
        }, function () {
          vm.handleLocationError(true, infoWindow)
        })
      } else {
        // Browser doesn't support Geolocation
        vm.handleLocationError(false, infoWindow)
      }
    },
    handleLocationError (browserHasGeolocation, infoWindow) {
      // eslint-disable-next-line @typescript-eslint/no-this-alias
      const vm = this
      infoWindow.setPosition(vm.latLngPosition)
      infoWindow.setContent(browserHasGeolocation
        ? 'Error: The Geolocation service failed.'
        : 'Error: Your browser doesn\'t support geolocation.')
      infoWindow.open(vm.map)
    }

  }
}
</script>

<style scoped>

</style>
