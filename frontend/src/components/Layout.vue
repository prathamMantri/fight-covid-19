<template>
  <v-app>
    <v-navigation-drawer
      v-model="drawer"
      :expand-on-hover="expandOnHover"
      :mini-variant="miniVariant"
      :src="require('@/assets/images/covid-19-virus.jpg')"
      dark
      floating
      permanent
      app
    >
      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          class="py-0"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      app
      color="primary"
      dark
      dense
      absolute
      :src="require('@/assets/images/covid-19-virus.jpg')"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"/>
      <v-toolbar-title>Fight Covid 19</v-toolbar-title>
    </v-app-bar>
    <v-content>
      <v-container
        class="fill-height"
      >
        <v-row align="center" sm="12">
          <v-carousel
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
          </v-carousel>
        </v-row>
        <v-row align="center" sm="12">
          <Landing/>
        </v-row>
        <v-row align="center" sm="12">
          <div>
            <p class="display-3 text-sm-center">Under Construction.. Please be patience. Thanks!</p>
          </div>
        </v-row>
      </v-container>
    </v-content>
    <v-footer
      color="primary"
      app
    >
      <span class="white--text">&copy; Prathamesh Mantri - Fight Covid-19 - 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import Landing from '@/views/Landing'

export default {
  components: { Landing },
  props: {
    component: String

  },
  data: () => ({
    drawer: true,
    items: [
      { title: 'Dashboard', icon: 'mdi-view-dashboard' },
      { title: 'Contact', icon: 'mdi-phone-in-talk ' },
      { title: 'About', icon: 'mdi-help-box' }
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
    expandOnHover: true
  })
}
</script>
