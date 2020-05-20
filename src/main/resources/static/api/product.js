import Vue from 'vue'
const reg = Vue.resource('/products/{id}');

export default {
    get: () => reg.get(),
    getById: id => reg.get({id}),
    add: id => reg.save({}, id),
    remove: id => reg.remove({id}),
}