define(['vue'], function (Vue) {
    var url = 'comp1';
    Vue.component(url,function(resolve){
        require([url], resolve);
    });
})

