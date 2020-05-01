import Vue from 'vue'
import {VuetifyGrid} from '@/types'
/**
 * A modified version of https://gist.github.com/cb109/b074a65f7595cffc21cea59ce8d15f9b
 */

/**
 * A Vue mixin to give the gutter sizes
 *
 *   <div v-if="$grid.gutters.xs">...</div>
 *
 */
export default Vue.extend({
    data() {
        return {
            gutter: 8
        }
    },
    computed: {
        grid(): VuetifyGrid {
            return {
                gutters: {
                    xs: (this.gutter / 4),
                    sm: (this.gutter / 2),
                    md: this.gutter,
                    lg: (this.gutter * 2),
                    xl: (this.gutter * 3)
                }
            }
        }
    },
})

