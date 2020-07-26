<template>
	<v-app>
		<v-app-bar
			id="main-toolbar"
			ref="mainToolbar"
			app
			dense
			flat
			color="primary"
			clipped-left
			class="toolbar"
			max-width="100%"
			:height="$vuetify.breakpoint.xs ? 50 : $vuetify.breakpoint.sm ? 50 : 50"

		>
			<!--extended-->
			<!--:extension-height="$vuetify.breakpoint.xs ? $route.meta.tabs? 87 : 38 : $vuetify.breakpoint.sm ? $route.meta.tabs ? 87 : 46 : $route.meta.tabs ? 87 : 54"-->
			<nav-main attach="main-toolbar"  />
			<v-toolbar-title class="ml-1 px-2 pt-1 black--text">
				<router-link
          px-2
					:to="{ name: 'Home'}"
					class="title-link font-weight-medium black--text"
				>
					Lockdown वाली दुकान
				</router-link>
			</v-toolbar-title>
			<!--	<v-toolbar-title
				slot="extension"
				class="white&#45;&#45;text title-link"
			>
				<div :class="$route.meta.tabs?'text-xs-left pt-3 pl-2 ml-1':'text-xs-left'">
					<div :class="$route.meta.backComponentName?'':'route-name'">
						<router-link
							v-if="$route.meta.backComponentName"
							:to="{ name: $route.meta.backComponentName}"
							class="title-link white&#45;&#45;text pr-2"
						>
							<v-icon class="title-link white&#45;&#45;text">
								arrow_back
							</v-icon>
						</router-link>
						{{ $route.name }}
					</div>
				</div>
				<div v-show="$route.meta.tabs">
					<v-tabs
						slider-color="white"
						height="40"
						grow
						class="title-link white&#45;&#45;text"
					>
						<v-tab
							v-for="tab in $route.meta.tabs"
							:key="tab"
							class="title-link white&#45;&#45;text pl-0 font-weight-light"
							ripple
						>
							{{ tab }}
						</v-tab>
					</v-tabs>
				</div>
				<v-spacer />
			</v-toolbar-title>-->
			<v-spacer />
			<v-toolbar-items>
				<v-btn v-if="status.loggedIn" @click.stop="handleLogout()" icon text :ripple="false" color="white">
					<v-icon color="black">
						logout
					</v-icon>
				</v-btn>
				<v-dialog v-else v-model="signInDialog" max-width="500px">
					<template v-slot:activator="{ on }">
						<v-btn text icon color="black" :ripple="false" dark v-on="on">
							<v-icon>{{icons.mdiLogin}}</v-icon>
						</v-btn>
					</template>
					<v-card>
						<v-card-title>
							<span class="headline">SIGN IN</span>
						</v-card-title>
						<v-card-text>
							<login />
						</v-card-text>
					</v-card>
				</v-dialog>
			</v-toolbar-items>
		<!--	<v-toolbar-title slot="extension">
				<div class="white&#45;&#45;text pl-5 text-xs-right">Hello {{user && status.loggedIn ? user.username : 'Guest'}}</div>
			</v-toolbar-title>-->
		</v-app-bar>
		<v-content>
			<v-layout class="px-5 py-0">
				<keep-alive>
					<router-view :key="$route.fullPath" />
				</keep-alive>
			</v-layout>
		</v-content>
		<v-footer
			color="primary"
			app
		>
			<span class="black--text">&copy;  <a class="black--text" href="https://linkedin.com/in/prathamMantri/"> Prathamesh Mantri</a> - Lockdown वाली दुकान - 2020</span>
			<v-spacer />
			<span class="black--text"> Visit <a class="brown--text" href="https://github.com/prathamMantri/lockdownvalidukan"><v-icon class="pb-1">{{ icons.mdiGithub }}</v-icon></a> for this project</span>
		</v-footer>
	</v-app>
</template>

<script>
import NavMain from '@/components/nav/NavMain'
import Login from '@/views/Login'
import EventBus from '@/event-bus'
import {mapActions, mapGetters, mapMutations, mapState} from 'vuex'
import {
	mdiGithub,
	mdiLogin
} from '@mdi/js'

export default {
	props: {
		component: String
	},
	created() {
		const user = sessionStorage.getItem('user')
		if (user) {
			console.log('User already logged in', user)
		}
	},
	components: {
		// SidebarNav
		Login,
		NavMain
	},
	computed: {
		name: function () {
			return this.$route.name
		},
		list: function () {
			return this.$route.matched
		},

		...mapState('auth', ['status']),

	},

	data: function () {
		return {
			signInDialog: false,
			user: JSON.parse(sessionStorage.getItem('user')),
			icons: {mdiGithub,mdiLogin }
		}
	},


	methods: {
		...mapActions('auth',
			['login', 'logout']
		),
		handleLogout() {
			this.logout()
			this.signInDialog = false
		},
		login() {
			this.signInDialog = true
		}
	}
}
</script>


<style lang="stylus" scoped>
  >>> .v-toolbar {

    a {
      text-decoration: none;
    }

    &__title {
      font-weight: 300;
    }

    &__content {
      //box-shadow: 0px 3px 1px -2px rgba(0,0,0,0.2), 0px 2px 2px 0px rgba(0,0,0,0.14), 0px 1px 5px 0px rgba(0,0,0,0.12) !important;
      box-shadow: 0px 3px 6px 0px rgba(0, 0, 0, 0.35) !important;
      //need these or the box shadow won't show up on flexbox containers
      position: relative;
      z-index: 1;
    }

    &__extension {
      background: var(--v-primary-lighten1);

      .v-tabs__bar {
        background: var(--v-primary-lighten1);
      }

      margin-left: 0;

      > .v-toolbar {
        &__title {
          margin-left: 0px;
        }
      }
    }
  }

  .toolbar, .v-toolbar__content {

    //max-height: 56px !important;
  }

  .v-chip {
    &--x-small {
      font-size: 12px;
      height: 18px;
    }

  }

  .subheading-color {
    color: var(--v-secondary-lighten6);
  }

  .route-name {
    padding-left: 2px !important;
  }

  >>> .v-toolbar__extension {
    position: relative;
  }
</style>
