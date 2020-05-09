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
			extended
			:height="$vuetify.breakpoint.xs ? 50 : $vuetify.breakpoint.sm ? 50 : 50"
			:extension-height="$vuetify.breakpoint.xs ? $route.meta.tabs? 87 : 38 : $vuetify.breakpoint.sm ? $route.meta.tabs ? 87 : 46 : $route.meta.tabs ? 87 : 54"
		>
			<nav-main attach="main-toolbar" />
			<v-toolbar-title class="ml-1 white--text pl-2">
				<router-link
					:to="{ name: 'Home'}"
					class="title-link white--text"
				>
					Fight COVID-19
				</router-link>
			</v-toolbar-title>
			<v-toolbar-title
				slot="extension"
				class="white--text title-link"
			>
				<div :class="$route.meta.tabs?'text-xs-left pt-3 pl-2 ml-1':'text-xs-left'">
					<div :class="$route.meta.backComponentName?'':'route-name'">
						<router-link
							v-if="$route.meta.backComponentName"
							:to="{ name: $route.meta.backComponentName}"
							class="title-link white--text pr-2"
						>
							<v-icon class="title-link white--text">
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
						class="title-link white--text"
					>
						<v-tab
							v-for="tab in $route.meta.tabs"
							:key="tab"
							class="title-link white--text pl-0 font-weight-light"
							ripple
						>
							{{ tab }}
						</v-tab>
					</v-tabs>
				</div>
			</v-toolbar-title>
			<v-spacer />
			<v-toolbar-items>
				<v-icon
					dark
					@click.stop="logout()"
				>
					logout
				</v-icon>
			</v-toolbar-items>
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
			<span class="white--text">&copy; Prathamesh Mantri - Fight COVID-19 - 2020</span>
		</v-footer>
	</v-app>
</template>

<script>
import NavMain from '@/components/nav/NavMain'

export default {
	props: {
		component: String
	},
	components: {
		// SidebarNav
		NavMain
	},
	computed: {
		name: function () {
			return this.$route.name
		},
		list: function () {
			return this.$route.matched
		}
	},

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
