var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="item.id + \'\'">',
        '<template slot="title"><i :class="item.icon == null ? \'el-icon-menu\' : item.icon"></i>{{item.name}}</template>',
        '<el-menu-item v-if="child.children.length == 0" v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="toPage(child)">{{child.name}}</el-menu-item>',
        '<menu-item v-if="subChild.children.length > 0" :item="subChild" v-for="subChild in item.children" :key="subChild.name"></menu-item>',
        '</el-submenu>',
    ].join(''),
    methods: {
        buildMenu: function (item, element) {
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
        toPage: function (child) {
            // home.contentUrl = child.url;
            $(".content-wrapper").load(child.url, function (data) {
                main_contain.title = child.name
                window.location.hash = child.url
            })
        }
    }

})

var menuItemHide = Vue.extend({
    name: 'menu-item-hide',
    props: {item: {}},
    template: [
        '<li  class="el-submenu item">',
        '<div class="el-submenu__title" @mouseover="showMenu(item.id,true)"  @mouseout="showMenu(item.id,false)"><i class="el-icon-message"/></div>',
        '<ul class="el-menu submenu" :class="\'submenu-hook-\'+item.id" @mouseover="showMenu(item.id,true)" @mouseout="showMenu(item.id,false)">',
        '<li v-for="child in item.children" :key="child.id" class="el-menu-item" style="padding-left: 22px;" @click="toPage(child)">',
        '{{child.name}}',
        '</li>',
        '</ul>',
        '</li>'
    ].join(''),
    methods: {
        showMenu: function (i, status) {
            main_contain.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        },
        toPage: function (child) {
            // home.contentUrl = child.url;
            $(".content-wrapper").load(child.url, function (data) {
                main_contain.title = child.name
                window.location.hash = child.url
            })
        }
    }
})


Vue.component('menuItem', menuItem);
Vue.component('menuItemHide', menuItemHide);


const main_contain = new Vue({
    el: "#main_contain",
    data: {
        sysName: 'SEEDADMIN',
        collapsed: false,
        sysUserName: '',
        sysUserAvatar: '',
        menuList: {},
        contentUrl: '',
        title: '',
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
                function(success) {
                    this.menuList = success.body[0].children;
                    console.log(this.menuList);
                },
                function(failure) {
                    alert(failure.body)
                }
            )
        },
        handleClose: function (key, keyPath) {
            console.log(key, keyPath);
        },
        //退出登录
        logout: function() {
            var _this = this;
            this.$confirm('确认退出吗?', '提示', {
                type: 'warning'
            }).then(
                function() {
                    // sessionStorage.removeItem('user');
                    // _this.$router.push('/login');
                });
        },
        //折叠导航栏
        collapse: function () {
            this.collapsed = !this.collapsed;
            if (this.collapsed) {
                this
            }
        },
        showMenu: function (i, status) {
            this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        }

    },
    created: function () {
        this.getMenuList();
    }
})

