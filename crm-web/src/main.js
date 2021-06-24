import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuetify from './plugins/vuetify'

Vue.prototype.$axios = axios
Vue.prototype.$url = "http://112.74.61.205:8081/"
Vue.prototype.$user = JSON.parse(localStorage.getItem("userInfo"))

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
