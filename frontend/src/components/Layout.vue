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
               <Landing/>
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
    miniVariant: true,
    expandOnHover: true
  })
}
</script>
