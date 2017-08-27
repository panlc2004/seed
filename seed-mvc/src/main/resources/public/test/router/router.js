define(['vue', 'vueRouter'], function (Vue, VueRouter) {

    Vue.use(VueRouter);


// 可以从其他文件 import 进来
    const Foo = {template: '<div>foo</div>'}
    const Bar = {template: '<div>bar</div>'}
    const User = {
        template: '<div>User {{ $route.params.id }}' +
        '<router-view></router-view></div>'
    }
    const UserHome = { template: '<div>Home</div>' }
    const UserProfile = { template: '<div>Profile</div>' }
    const UserPosts = { template: '<div>Posts</div>' }

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
    const routes = [
        {path: '/foo', components: {default:Foo,v2:Bar}},
        {path: '/bar', component: Bar},


        {
            path: '/user/:id', components: {default:Bar,v2:User},
            children: [
                {
                    // 当 /user/:id/profile 匹配成功，
                    // UserProfile 会被渲染在 User 的 <router-view> 中
                    path: 'profile',
                    component: UserProfile
                },
                {
                    // 当 /user/:id/posts 匹配成功
                    // UserPosts 会被渲染在 User 的 <router-view> 中
                    path: 'posts',
                    component: UserPosts
                }
            ]
        }
    ]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
    const router = new VueRouter({
        routes: routes // （缩写）相当于 routes: routes
    })

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
    const app = new Vue({
        router: router
    }).$mount('#app')

    // var a = Vue.extend({
    //     template: [
    //         '<div>',
    //         '<h1>Hello App!</h1>',
    //         '<p>',
    //         '<!-- 使用 router-link 组件来导航. -->',
    //         '<!-- 通过传入 `to` 属性指定链接. -->',
    //         '<!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->',
    //         '<router-link to="foo">Go to Foo</router-link>',
    //         '<router-link to="bar">Go to Bar</router-link>',
    //         '<router-link to="user/2">Go to User</router-link>',
    //         '</p>',
    //         '<!-- 路由出口 -->',
    //         '<!-- 路由匹配到的组件将渲染在这里 -->',
    //         '<router-view></router-view>',
    //         '</div>'
    //     ].join(''),
    //     router: router
    // })
    //
    // return a;

})