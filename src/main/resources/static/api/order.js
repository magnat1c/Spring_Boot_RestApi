import Vue from 'vue'
const order = Vue.resource('/orders/{id}');

export default {
    get: () => order.get(),
    getById: id => order.get({id}),
    add: id => order.save({}, id),
    update: id => order.update({id: id.id}, id),
    remove: id => order.remove({id}),
}