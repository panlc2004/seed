var routes = [
    {
        path: '/login',
        name: '',
        hidden: true
    },
    {
        path: '/404',
        name: '',
        hidden: true
    },
    {
        path: '/',
        name: '导航一',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            {path: '/main', name: '主页', hidden: true},
            {path: '/table', name: 'Table'},
            {path: '/form', name: 'Form'},
            {path: '/user', name: '列表'},
        ]
    },
    {
        path: '/',
        name: '导航二',
        iconCls: 'fa fa-id-card-o',
        children: [
            {path: '/page4', name: '页面4'},
            {path: '/page5', name: '页面5'}
        ]
    },
    {
        path: '/',
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            {path: '/page6', name: '导航三'}
        ]
    },
    {
        path: '/',
        name: 'Charts',
        iconCls: 'fa fa-bar-chart',
        children: [
            {path: '/echarts', name: 'echarts'}
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: {path: '/404'}
    }
];

var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="index" v-if="!item.leaf">',
        '<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>',
        '<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">{{child.name}}</el-menu-item>',
        '</el-submenu>',
        '<el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path"><i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item>'
    ]
})

Vue.component('menuItem', menuItem);



var home = new Vue({
    el: "#home",
    data: {
        sysName: 'SEEDADMIN11',
        collapsed: false,
        sysUserName: '',
        sysUserAvatar: '',
        menuList: {},
        form: {
            name: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
        }
    },
    methods: {
        getMenuList: function () {
            this.menuList = routes;
        },
        onSubmit() {
            console.log('submit!');
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleselect: function (a, b) {
        },
        //退出登录
        logout: function () {
            var _this = this;
            this.$confirm('确认退出吗?', '提示', {
                //type: 'warning'
            }).then(() => {
                sessionStorage.removeItem('user');
                _this.$router.push('/login');
            }).catch(() => {

            });
        },
        //折叠导航栏
        collapse: function () {
            this.collapsed = !this.collapsed;
        },
        showMenu(i, status){
            this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        }
    },
    created: function () {
        // this.getMenuList();
    },
    mounted() {
        var user = sessionStorage.getItem('user');
        if (user) {
            user = JSON.parse(user);
            this.sysUserName = user.name || '';
            this.sysUserAvatar = user.avatar || '';
        }
    }
})

test();
function test() {
    alert(1)
    home.menuList=[{
        path: '/login',
        name: '123',
        hidden: true
    },{
        path: '/login',
        name: '345',
        hidden: true
    }]
}

// const router = new VueRouter({
//     routes
// })
//
// router.beforeEach((to, from, next) => {
//     //NProgress.start();
//     if (to.path == '/login') {
//         sessionStorage.removeItem('user');
//     }
//     let user = JSON.parse(sessionStorage.getItem('user'));
//     if (!user && to.path != '/login') {
//         next({path: '/login'})
//     } else {
//         next()
//     }
// })

