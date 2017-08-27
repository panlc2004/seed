define(['vue','vueRouter'], function (Vue,VueRouter) {
    Vue.use(VueRouter);

    var a = Vue.extend({
        template: [
            '<div class="user">',
            '<h2>User</h2>',
            '<router-view></router-view>',
            '<h2>User2</h2>',
            '<router-view name="u2"></router-view>',
            '</div>'

        ].join(''),
        // router:router,
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

    var children =  [
        {
            path: 't1',
            component: function (resolve) {
                require([ctx + 'test/js/comp2'], function (o) {
                    resolve(o)
                    // componentRoute.children = o.children;
                });
            }

        },
        {
            path: 't2',
            components: {
                u2: function (resolve) {
                    require([ctx + 'test/js/comp2'], function (o) {
                        resolve(o)
                        // componentRoute.children = o.children;
                    });
                }
            }
        }
    ]

    // var c =  [
    //     {
    //         // 当 /user/:id/profile 匹配成功，
    //         // UserProfile 会被渲染在 User 的 <router-view> 中
    //         path: 't1',
    //         component: t1
    //     },
    //     {
    //         // 当 /user/:id/posts 匹配成功
    //         // UserPosts 会被渲染在 User 的 <router-view> 中
    //         path: 't2',
    //         component: t2
    //     }
    // ]


    return {
        comp:a,
        childRoute:children
    };

    // return {
    //     a1: a
    //     // children: c
    // };


})
