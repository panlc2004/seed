/**
 *点击菜单时，对应页面加载模式
 */
const pageLoadInTag = false;

// 主菜单栏
var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="item.id + \'\'">',
        '<template slot="title"><i :class="item.icon == null ? \'el-icon-menu\' : item.icon"></i>{{item.name}}</template>',
        '<el-menu-item v-if="item.children" v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="toPage(child)">{{child.name}}</el-menu-item>',
        '<menu-item v-if="!item.children" :item="subChild" v-for="subChild in item.children" :key="subChild.name"></menu-item>',
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
            loadPage(child, pageLoadInTag);
        }
    }

})

// 隐藏菜单栏
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

/**
 * 加载页面
 * @param child 菜单对象；
 * @param pageLoadInTag 加载模式：false.整页加载；true.tab加载；
 */
function loadPage(child, pageLoadInTag) {
    //以整页方式加载页面
    if (!pageLoadInTag) {
        $(".content-wrapper").load(child.url, function (data) {
            main_contain.title = child.name
            window.location.hash = child.url
        });
        //以tag方式加载页面
    } else {
        //打开tab
        var existTagId; //已经打开的tabID
        for(var i = 0; i < main_contain.editableTabs.length; i++) {//判断当前页面是否已经打开，如果打开，就焦点至对应tab，不再重复打开
            var tab = main_contain.editableTabs[i];
            if(tab.id == child.id) {
                existTagId = child.id + '';
                break;
            }
        }
        if(existTagId) {
            main_contain.editableTabsValue = existTagId;        //当前菜单已经打开，则直接激活对应tab页
        } else {//当前菜单未打开，则新建tab页
            main_contain.editableTabs.push(child);
            main_contain.editableTabsValue = child.id + '';
            // 加载页面
            var tabContentId = 'tab-content-' + child.id;
            var tabLoader = window.setInterval(function () {
                var tabContent = $("#" + tabContentId);
                if (tabContent) {
                    tabContent.load(child.url, function (data, status) {
                        if(status) {
                            window.location.hash = child.url
                        } else {
                            czy.msg.error("系统异常，请联系管理员");
                        }
                    })
                    window.clearInterval(tabLoader);
                }
            }, 100);
        }
    }
};

Vue.component('menuItem', menuItem);
Vue.component('menuItemHide', menuItemHide);

// 主页面
const main_contain = new Vue({
    el: "#main_contain",
    data: {
        //tab方式加载页面设置
        pageLoadInTag: pageLoadInTag,
        editableTabs: [],
        editableTabsValue: '1',
        tabIndex: 1,

        //菜单及登陆标识设置
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
        // 移除标签
        removeTab: function (targetName) {
            var tabs = this.editableTabs;
            var activeName = this.editableTabsValue;
            if (activeName == targetName) {
                tabs.forEach(function (tab, index) {
                    if (tab.id == targetName) {
                        var nextTab = tabs[index + 1] || tabs[index - 1];
                        if (nextTab) {
                            activeName = nextTab.id + '';
                        }
                    }
                });
            }
            this.editableTabs = tabs.filter(function (tab) {
                return tab.id != targetName
            });
            this.editableTabsValue = activeName;
        },
        getMenuList: function () {
            var _this = this;
            $.post("sys/resource/findResourceTreeForLoginUser",
                function (result, status) {
                    if (status) {
                        _this.menuList = result[0].children;
                        console.log(_this.menuList);

                    } else {
                        _this.$message({
                            type: 'error',
                            message: "系统异常，请联系管理员"
                        });
                    }
                }
            )
        },
        handleClose: function (key, keyPath) {
        },
        //退出登录
        logout: function () {
            var _this = this;
            this.$confirm('确认退出吗?', '提示', {
                type: 'warning'
            }).then(
                function () {
                    // sessionStorage.removeItem('user');
                    $.get("logout", function (data,status) {
                        if(status) {
                            window.location.href = "login";
                        }
                    });
                });
        },
        //折叠导航栏
        collapse: function () {
            this.collapsed = !this.collapsed;
        },
        //展示菜单
        showMenu: function (i, status) {
            this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
        }

    },
    created: function () {
        this.getMenuList();
    }
});

