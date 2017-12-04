/**
 * Created by PLC on 2017/8/6.
 */
String.prototype.startWith = function (str) {
    var reg = new RegExp('^' + str);
    return reg.test(this);
}

String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
}

String.prototype.replaceAll = function (str, value) {
    var regExp = new RegExp(str, 'gm');
    return this.replace(regExp, value);
}

// 菜单栏
Vue.component('menuItem', {
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<el-submenu :index="item.id + \'\'">',
        '<template slot="title"><i :class="item.icon == null ? \'el-icon-menu\' : item.icon"></i><span slot="title">{{item.name}}</span></template>',
        '<el-menu-item v-if="item.children" v-for="child in item.children" :index="child.id + \'\'" :key="child.id" @click="loadPage(child)">',
        '<span slot="title">{{child.name}}</span>',
        '</el-menu-item>',
        '<menu-item v-if="!item.children" :item="subChild" v-for="subChild in item.children" :key="subChild.name"></menu-item>',
        '</el-submenu>',
    ].join(''),
    methods: {
        loadInFrame: function (child) {
        },
        loadInTab: function (child) {

        },
        loadPage: function (child) {
            openTab(child);
            loadComponent(child.url);
            // if(window.pageloadInTab) {
            //     this.loadInTab(child);
            // } else {
            //     this.loadInFrame(child);
            // }
        }
    }
})

// 定义路由
var _routes = [];
var seedRouter = new VueRouter({
    routes: _routes
});

/**
 * 打开tab页
 * @param child 菜单项
 */
function openTab(child) {
    var newTab = true;
    //查找要打开的页面是否已经打开
    for (var i = 0; i < seedMainPanel.pageTables.length; i++) {
        if (seedMainPanel.pageTables[i].id == child.id) {
            newTab = false;
            break;
        }
    }
    //如果页面未打开，则打开
    if (newTab) {
        seedMainPanel.pageTables.push({
            id: child.id,
            label: child.name,
            name: 'view' + child.id
        });
    }
    //激活对应的tab
    seedMainPanel.activeName = 'view' + child.id;//tab选中
}

/**
 * 加载组件并设置路由
 * @param url 请求url
 */
function loadComponent(url) {
    //进入首页时不执行动作
    if (url == '' || url == '/') {
        return;
    }
    var componentRoute = {};    //定义路由组件
    var jsPath = url.replace('.html', '');
    var router_path = '/' + jsPath;         //路由名称
    require([jsPath], function (o) {
        componentRoute[seedMainPanel.activeName] = o.component
        seedRouter.addRoutes([{
            path: router_path,
            components: componentRoute,
            children: o.subRoute
        }]);
        seedMainPanel.tabRouterPath[seedMainPanel.activeName] = router_path;
    });
    seedMainPanel.transition = getRandomTransition();
    seedRouter.push(router_path);
}

/**
 * 根据window.location.hash计算应该加载的url
 * @param locationHash
 * @returns {*}
 */
function buildUrlByWindowLocationHash(locationHash) {
    if ('#/' == locationHash) {
        return locationHash;
    }
    locationHash = locationHash.replace('#', '')
    if (locationHash.startWith('/')) {
        locationHash = locationHash.replace('/', '')
    }
    var url = locationHash + '.html';
    return url;
}

seedMainPanel = new Vue({
    el: '#main-panel',
    router: seedRouter,
    data: {
        menuList: {},
        collapse: false,
        // logo:'test',
        defaultActive: '1',
        pageTables: [],
        activeName: '0',
        transition: '',  //加载动画
        tabRouterPath: {}  //tab页对应的路由
    },
    computed: {
        logo: function () {
            if(this.collapse) {
                return appName_simple;
            } else {
                return appName_full;
            }
        }
    },
    methods: {
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
                            window.location.href = "login_page";
                        }
                    });
                });
        },
        getMenu: function () {
            $.get("sys/resource/findResourceTreeForLoginUser?_" + $.now(), function (o) {
                seedMainPanel.menuList = o.data[0].children;
                seedMainPanel.initPage();
            });
        },
        /**
         * 尝试根据url加载对应功能页
         */
        initPage: function () {
            //根据url加载对应页面
            var url = buildUrlByWindowLocationHash(window.location.hash);
            if (url != '/' && url != '#/') {
                var menu = this.findMenuByUrl(url);
                if (menu) {
                    openTab(menu);
                    loadComponent(url);
                    this.defaultActive = menu.id + '';   //让指定菜单置为激活状态 TODO
                } else {
                    alert("无法对输入地址进行功能定位");
                }
            }
        },
        collapseMenu: function () {
            this.collapse = !this.collapse;
        },
        /**
         * 关闭tab页
         * @param targetName
         */
        removeTab: function (targetName) {
            var active = this.activeName;
            var tabs = this.pageTables;
            if (this.activeName == targetName) {
                tabs.forEach(function (tab, index) {
                    if (tab.name == targetName) {
                        var nextTab = tabs[index + 1] || tabs[index - 1];
                        if (nextTab) {
                            active = nextTab.name;
                        }
                    }
                })
            }
            this.activeName = active;
            this.pageTables = tabs.filter(function (tab) {
                return tab.name !== targetName
            });
            if (tabs.length > 1) {
                seedRouter.push(this.tabRouterPath[this.activeName]);   //跳转页面
            } else {
                seedRouter.push('/');       //tab页面全部关闭时，路由重置
            }
        },
        findMenuByUrl: function (url) {
            var _this = this;
            var menu;
            menu = this.findMenu(_this.menuList, url);
            return menu;
        },
        findMenu: function (item, url) {
            var _this = this;
            for (var i = 0; i < item.length; i++) {
                if (item[i].types == 2) {
                    return _this.findMenu(item[i].children, url)
                } else if (item[i].types == 1 && item[i].url == url) {
                    return item[i];
                }
            }
        },
        /**
         * 点击tab时，设置动画效果，并切换路由
         * @param tab
         */
        tabClick: function (tab) {
            this.transition = getRandomTransition();
            seedRouter.push(this.tabRouterPath[this.activeName]);
        }

    },
    created: function () {
        this.getMenu();
    }

});

// 取随机动画
function getRandomTransition() {
    var all = ['el-zoom-in-top', 'el-zoom-in-center', 'el-zoom-in-bottom'];
    var i = getRandomNum(0, 2);
    return all[i];
}

/**
 * 取随机数
 * @param Min 最小值
 * @param Max 最大值
 * @returns {*}
 * @constructor
 */
function getRandomNum(Min, Max) {
    var range = Max - Min + 1;
    var rand = Math.random();
    var num = rand * range + Min;
    var randInt = parseInt(num, 10);
    return randInt;
}
