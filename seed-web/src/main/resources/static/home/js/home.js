var routes = [
    {
        index: "10",
        path: '/',
        name: '导航一',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            {path: '/main', name: '主页', hidden: true},
            {path: '/index2.html', name: 'Table'},
            {path: '/form', name: 'Form'},
            {path: '/user', name: '列表'},
        ]
    },
    {
        index: '3',
        path: '/',
        name: '导航二',
        iconCls: 'el-icon-date',
        children: [
            {path: '/page4', name: '页面4'},
            {path: '/page5', name: '页面5'}
        ]
    },
    {
        index: '4',
        path: '/',
        name: '57685678',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            {path: '/page6', name: '导航三'}
        ]
    },
    {
        index: '5',
        path: '/',
        name: 'Charts',
        iconCls: 'el-icon-message',//图标样式class

        children: [
            {path: '/echarts', name: 'echarts'}
        ]
    }
];

var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="item.index" v-if="!item.leaf">',
        '<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>',
        '<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden" @click="toPage(\'/index2.html\')">{{child.name}}</el-menu-item>',
        '</el-submenu>',
        '<el-menu-item v-else-if="item.leaf && item.children.length>0" :index="item.children[0].path"><i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item>'
    ].join(''),
    methods: {

        toPage(url) {
            // this.$http.get(url).then(
            //     successRes => {
            //         console.log(successRes);
            //         home.pageContent = successRes.body
            //     }, falseRes => {
            //         console.log(falseRes);
            //         home.pageContent = falseRes.body
            //     });
            $("#pageContent").empty();
            $("#pageContent").load(url);

        }
    }

})

var menuItemHide = Vue.extend({
    name: 'menu-item-hide',
    props: {item: {}},
    template: [
        '<li v-if="!item.hidden" class="el-submenu item" >',
        '<div v-if="!item.leaf" class="el-submenu__title" @mouseover="showMenu(item.index,true)"  @mouseout="showMenu(item.index,false)"><i :class="item.iconCls"/></div>',
        '<ul v-if="!item.leaf" class="el-menu submenu" :class="\'submenu-hook-\'+item.index" @mouseover="showMenu(item.index,true)" @mouseout="showMenu(item.index,false)">',
        '<li v-if="!child.hidden" v-for="child in item.children" :key="child.path" class="el-menu-item" style="padding-left: 40px;">',
        // ':class="$route.path==child.path?\'is-active\':\'\'" @click="$router.push(child.path)">{{child.name}}',
        '{{child.name}}',
        '</li>',
        '</ul>',
        '<li v-else class="el-submenu">',
        '<div class="el-submenu__title el-menu-item " style="padding-left: 17px;height: 56px;line-height: 56px;padding: 0 20px;" :class="item.path==item.children[0].path ? \'is - active\':\'\'" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>',
        '</li>',
        '</li>'
    ].join(''),
    methods: {
        showMenu(i, status){
            home.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        }
    }
})

Vue.component('menuItem', menuItem);
Vue.component('menuItemHide', menuItemHide);

// Vue.use(VueRouter);


var home = new Vue({
    el: "#home",
    // router,
    // render: h => h(home),
    data: {
        sysName: 'SEEDADMIN',
        collapsed: false,
        sysUserName: '',
        sysUserAvatar: '',
        menuList: {},
        pageContent: '',
        form: {
            name: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: '',
            pageContent: ""
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
        },
        toPage(url) {
            // Vue.get(url).then(
            //     successRes => {
            //         console.log(successRes);
            //         home.pageContent = successRes
            //     }, falseRes => {
            //         console.log(falseRes);
            //         home.pageContent = falseRes
            //     });
            alert(url);
            $("#pageContent").load("cn.html");

        }
    },
    created: function () {
        this.getMenuList();
    },
    updated: function () {
        //路由
        var router = new Router();
        routerList(router, home.menuList);
        router.start();
    },
    mounted()
    {
        var user = sessionStorage.getItem('user');
        if (user) {
            user = JSON.parse(user);
            this.sysUserName = user.name || '';
            this.sysUserAvatar = user.avatar || '';
        }
    }
})

// const router = new VueRouter();

function toPage(url) {
    // Vue.get(url).then(
    //     successRes => {
    //         console.log(successRes);
    //         home.pageContent = successRes
    //     }, falseRes => {
    //         console.log(falseRes);
    //         home.pageContent = falseRes
    //     });

    alert(1)
    $("#pageContent").load("cn.html");


}

function routerList(router, menuList) {
    for (var key in menuList) {
        var menu = menuList[key];
        if (menu.type == 0) {
            routerList(router, menu.list);
        } else if (menu.type == 1) {
            router.add('#' + menu.url, function () {
                var url = window.location.hash;

                //替换iframe的url
                vm.main = url.replace('#', '');

                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");

                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    }
}

