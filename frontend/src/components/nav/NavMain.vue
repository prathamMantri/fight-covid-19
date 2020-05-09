<template>
	<v-menu
		transition="slide-y-transition"
		:max-width="computeMenuList.length>1?'400':'200'"
		content-class="dropdown-menu"
		absolute
		:position-y="$vuetify.breakpoint.xs ? 50 : $vuetify.breakpoint.sm ? 50 : 50"
		:position-x="0"
		:offset-x="true"
	>
		<template
			v-slot:activator="{ on }"
		>
			<v-icon dark v-on="on">menu</v-icon>
		</template>
		<v-container
			fluid
			justify-start
			v-bind="{ [`grid-list-xs`]: true }"
			class="pa-0 ma-0"
		>
			<v-card
				class="pa-0 ma-0"
			>
				<v-layout
					row
					wrap
					class="pa-0 ma-0"
				>
					<v-flex
						v-for="item in computeMenuList"
						:key="item.text"
						:class="computeMenuList.length>1?'xs6':'xs12'"
					>
						<v-list
							class="py-0"
						>
							<v-list-item @click="$router.push({path: item.url})">
								<v-icon
									class="pr-1"
									color=""
								>
									{{ item.icon }}
								</v-icon>
								{{ item.text }}
							</v-list-item>
						</v-list>
					</v-flex>
				</v-layout>
			</v-card>
		</v-container>
	</v-menu>
</template>

<script lang="ts">

import Vue from 'vue'
import config from './config'

export default Vue.extend({
	props: {
		attach: {
			type: String,
			default: null
		}
	},
	data: function () {
		return {
			loading: true,
			//items: config.items,
			//permissions: store.state.user.authorities
		}
	},
	computed: {
		computeMenuList() {
			const computedItems: any[] = []
			config.items.forEach(function (item) {
				computedItems.push(item)
			})
			return computedItems
		}
	},
	methods: {
		/*checkPermission(permission) {
                const vm = this
                let isPermitted = false



                vm.permissions.forEach(function (val) {
                    if (val.authority === permission) {
                        isPermitted = true
                        return false
                    }
    })
                return isPermitted
            }*/
	}
})
</script>

<style scoped lang="scss">
	.v-menu__content {
		top: 50px !important;
		left: 0 !important;
		position: fixed;
		min-width: 400px !important;
	}
</style>
