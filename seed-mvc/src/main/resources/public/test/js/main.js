require.config({
    // baseUrl:'/',
    paths: {
        vue: "/lib/vue/2.4.2/vue",
        vueRouter: "/lib/vue/vue-router",
        elementui:"lib/elementui/1.4.2/index.js"
    }
});

require(['vue','vueRouter','async'],function (Vue, VueRouter, async) {
    Vue.use(VueRouter);
});