<template>
	<v-navigation-drawer
		:value="show"
		:clipped="$vuetify.breakpoint.mdAndUp"
		fixed
		app
	>
		<v-list-tile>
			<v-list-tile-action />
			<v-list-tile-content>
				<v-list-tile-title />
			</v-list-tile-content>
		</v-list-tile>
		<v-list
			v-for="item in items"
			:key="item.text"
			dense
		>
			<template>
				<v-layout
					v-if="item.heading"
					:key="item.heading"
					row
					align-center
				>
					<v-flex xs6>
						<v-subheader v-if="item.heading">
							{{ item.heading }}
						</v-subheader>
					</v-flex>
				<!--<v-flex
						xs6
						class="text-xs-center"
					>
						<v-btn small flat>edit</v-btn>
					</v-flex>-->
				</v-layout>
				<v-list-group
					v-else-if="item.children"
					:key="item.text"
					v-model="item.model"
					:prepend-icon="item.model ? item.icon : item['icon-alt']"
					append-icon=""
				>
					<v-list-tile slot="activator">
						<v-list-tile-content class="ml-0">
							<v-list-tile-title>
								{{ item.text }}
							</v-list-tile-title>
						</v-list-tile-content>
					</v-list-tile>
					<v-list-tile
						v-for="(child, i) in item.children"
						:key="i"
						@click="void(0)"
					>
						<!--<v-list-tile-action v-if="child.icon">
							<v-icon>{{ child.icon }}</v-icon>
						</v-list-tile-action>-->
						<v-list-tile-content>
							<v-list-tile-title>
								<router-link
									v-if="child.url"
									:to="child.url"
								>
									{{ child.text }}
								</router-link>
							</v-list-tile-title>
						</v-list-tile-content>
					</v-list-tile>
				</v-list-group>
				<v-list-tile
					v-else
					:key="item.text"
					@click="void(0)"
				>
					<!--<v-list-tile-action>
						<v-icon>{{ item.icon }}</v-icon>
					</v-list-tile-action>-->
					<v-list-tile-content class="pl-2">
						<v-list-tile-title>
							<router-link
								v-if="item.url"
								:to="item.url"
							>
								{{ item.text }}
							</router-link>
						</v-list-tile-title>
					</v-list-tile-content>
				</v-list-tile>
			</template>
		</v-list>
	</v-navigation-drawer>
</template>

<script>
import config from './config'
import EventBus from '@/event-bus'

export default {
	name: 'SidebarNav',
	components: {},
	props: {
		show: {
			type: Boolean,
			default: true
		},
		items: {
			type: Array,
			required: true,
			default: config.items
		}
	},
	data: function () {
		return {
			mini: false,
			psSettings: {
				maxScrollbarLength: 200,
				minScrollbarLength: 40,
				suppressScrollX: true,
				wheelPropagation: false,
				interceptRailY: styles => ({ ...styles, height: 0 })
			}
		}
	},
	methods: {
		scrollHandle (evt) {
			// console.log(evt)
		},
		handleInputEvent (e) {
			EventBus.$emit('sidebar.change', {
				component: this.$options.name,
				value: e ? 'show' : 'hide'
			})
		}
	}
}
</script>

<style scoped lang="css">
	.scroll-area {
		position: absolute;
		height: 100%;
		margin: auto;
	}
</style>
