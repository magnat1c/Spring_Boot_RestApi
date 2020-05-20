import Vue from 'vue'
const customer = Vue.resource('/customers/{id}');

export default {
    get: () => customer.get(),
    getById: id => customer.get({id}),
    add: id => customer.save({}, id),
}