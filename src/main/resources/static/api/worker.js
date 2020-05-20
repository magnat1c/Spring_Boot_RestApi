import Vue from 'vue'
const user = Vue.resource('/workers/{id}');

export default {
    get: () => user.get(),
    getById: id => user.get({id}),
    add: id => user.save({}, id),
}