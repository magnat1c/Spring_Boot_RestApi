import Vue from 'vue'
import 'api/resource'
import App from 'pages/app.vue'
import router from './router/router'
import vuetify from 'vuetify'

//Vue.use(Vuetify)

new Vue({
    vuetify,
    el: '#app',
    router,
    render: a => a(App)
})