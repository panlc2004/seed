var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="item.id + \'\'">',
        '<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>',
        '<el-menu-item v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="toPage(child)">{{child.name}}</el-menu-item>',
        '</el-submenu>',
    ].join(''),
    // template:buildMenu(this.items,""),
    methods: {
        buildMenu(item, element){
            if (item.children.length > 0) {
                element += '<el-submenu :index="item.id + \'\'">'
                item.children.forEach(function (child) {
                    this.buildMenu(child, element);
                })
                element += '</el-submenu>'
            } else {
                element += '<el-menu-item v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="toPage(child)">{{child.name}}</el-menu-item>';
            }
        },
        toPage(child) {
            console.log(child)
            home.contentUrl = child.url;
            home.title=child.name
        }
    }

})

// function buildMenu(item, element) {
//     if (item.children.length > 0) {
//         element += '<el-submenu :index="item.id + \'\'">'
//         item.children.forEach(function (child) {
//             this.buildMenu(child, element);
//         })
//         element += '</el-submenu>'
//     } else {
//         element += '<el-menu-item v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="toPage(item.url)">{{child.name}}</el-menu-item>';
//     }
// }

var menuItemHide = Vue.extend({
    name: 'menu-item-hide',
    props: {item: {}},
    template: [
        '<li>',
        '<div class="el-submenu__title" @mouseover="showMenu(item.id,true)"  @mouseout="showMenu(item.id,false)"><i class="el-icon-message"/></div>',
        '<ul class="el-menu submenu" :class="\'submenu-hook-\'+item.id" @mouseover="showMenu(item.id,true)" @mouseout="showMenu(item.id,false)">',
        '<li v-for="child in item.children" :key="child.id" class="el-menu-item" style="padding-left: 22px;" @click="toPage(child)">',
        '{{child.name}}',
        '</li>',
        '</ul>',
        '</li>'
    ].join(''),
    methods: {
        showMenu(i, status){
            home.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        },
        toPage(child) {
            home.contentUrl = child.url;
            home.title=child.name
        }
    }
})

Vue.component('menuItem', menuItem);
Vue.component('menuItemHide', menuItemHide);


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
        contentUrl: '',
        title:'',
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
            this.$http.post("sys/resource/findResourceTreeForLoginUser").then(
                success => {
                    console.log(success)
                    this.menuList = success.body;
                }, failure => {
                    alert(failure.body)
                }
            )
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
        this.getMenuList();
    },
    // updated: function () {
    //     //路由
    //     var router = new Router();
    //     routerList(router, home.menuList);
    //     router.start();
    // },
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
//
// function routerList(router, menuList) {
//     for (var key in menuList) {
//         var menu = menuList[key];
//         if (menu.type == 0) {
//             routerList(router, menu.list);
//         } else if (menu.type == 1) {
//             router.add('#' + menu.url, function () {
//                 var url = window.location.hash;
//
//                 //替换iframe的url
//                 vm.main = url.replace('#', '');
//
//                 //导航菜单展开
//                 $(".treeview-menu li").removeClass("active");
//                 $("a[href='" + url + "']").parents("li").addClass("active");
//
//                 vm.navTitle = $("a[href='" + url + "']").text();
//             });
//         }
//     }
// }

