import Vue from 'vue'
import {Promise} from 'es6-promise'
import {_, axios, dataLayer} from "@/bootstrap"


const state = {
  snackbarProps : {
    snackbar: false,
    timeout: 5000,
    text: '',
    color: '',
  }
}

const getters = {}
const actions = {}
const mutations = {
  setSnackbarProps(state: { snackbarProps: any }, payload: any){
    state.snackbarProps = payload
  },
  resetSnackbarProps(state: { snackbarProps: any }, payload: any){
    state.snackbarProps =  {
      snackbar: false,
      timeout: 5000,
      text: '',
      color: '',
    }
  }
}

export default {
  namespaced: true,
  state() {
    return state
  },
  getters: getters,
  actions: actions,
  mutations: mutations
}
