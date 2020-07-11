import Vue from 'vue'
import {Promise} from 'es6-promise'
import {_, axios, dataLayer} from "@/bootstrap"


const state = {
  userLoggedIn: false
}

const getters = {}
const actions = {}
const mutations = {}

export default {
  namespaced: true,
  state() {
    return state
  },
  getters: getters,
  actions: actions,
  mutations: mutations
}
