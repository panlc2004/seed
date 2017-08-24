define(['vue'], function (Vue) {
    var a = Vue.extend({
        template: '<h1>com1</h1>',
        created: function () {
            console.log('cre');
        },
        destroyed: function () {
            console.log('des');
        },
        // 在编译结束和 $el 第一次插入文档之后调用
        ready: function () {
            console.log('ready');
        }
    });
    return a;
})
