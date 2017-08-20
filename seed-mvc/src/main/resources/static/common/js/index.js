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
var routes = [];
var routeCache = [];
var router = new VueRouter({
    routes: routes
});

/**
 * 打开tab页
 * @param child 菜单项
 */
function openTab(child) {
    var newTab = true;
    //查找要打开的页面是否已经打开
    for(var i = 0; i < seed.pageTables.length; i++) {
        if(seed.pageTables[i].id == child.id) {
            newTab = false;
            break;
        }
    }
    //如果页面未打开，则打开
    if(newTab) {
        seed.pageTables.push({
            id: child.id,
            label: child.name,
            name: child.id
        });
    }
    //激活对应的tab
    seed.activeName = child.id;//tab选中
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
    //根据url计算组件名称
    var component_name = buildComponentNameByUrl(url);
    //路由路径
    var router_path = '/' + component_name;
    //更新路由
    routeUpdate(url, component_name, router_path);
}

/**
 * 页面跳转
 * @param component_name    路由名称
 * @param router_path   路由路径
 */
function routeUpdate(url, component_name, router_path) {
    //组件已经加载，直接跳转
    if (routeCache.indexOf(component_name) != -1) {
        router.push(router_path);
        //设置updated方法不再执行
        seed.canUpdate = false;
        return;
    }
    //组件未加载，先加载组件，再跳转
    $("#component-cache").load(ctx + url, function (data, status) {
        if (status == 'success') {
            //缓存组件名称
            routeCache.push(component_name);
            //更新路由
            addRoutes(component_name, router_path);
            //路由跳转
            router.push(router_path);
            //设置updated方法不再执行
            seed.canUpdate = false;
        } else {
            alert('加载url：' + url + '失败');
        }
    });
}

/**
 * 更新路由
 * @param component_name
 * @param router_path
 */
function addRoutes(component_name, router_path) {
    //动态增加路由
    var component = eval(component_name)
    var routeName = seed.activeName;
    var componentRoute = {};
    componentRoute['view' + routeName] = component;
    router.addRoutes([{path: router_path, components: {view2: component}}]);
}

/**
 * 计算组件名称
 * @param url
 */
function buildComponentNameByUrl(url) {
    if (!url.startWith('/')) {
        alert("url必须以'/'开头")
        return;
    }
    var component_name = url.replace('/', '').replaceAll('\/', '$').replaceAll('-', '_');
    if (url.endWith('.html')) {
        component_name = component_name.replace('.html', '_html');
    }
    return component_name
}

/**
 * 根据window.location.hash计算应该加载的url
 * @param locationHash
 * @returns {*}
 */
function buildUrlByWindowLocationHash(locationHash) {
    locationHash = locationHash.replace('#', '')
    if (!locationHash.startWith('/')) {
        alert("url必须以'/'开头")
        return;
    }
    if (locationHash.endWith('_html')) {
        locationHash = locationHash.replace('_html', '.html');
    }
    var url = locationHash.replaceAll('\\$', '/').replaceAll('_', '-');
    return url;
}

seed = new Vue({
    el: '#main-panel',
    router: router,
    data: {
        loadUrl: 'homePage.html',
        menuList: {},
        collapse: false,
        defaultActive: '1',
        pageTables: [],
        tabshow: {},
        activeName: '0',
        updateExe:true
    },
    computed:{
        canUpdate:{
            get:function () {
                return this.updateExe;
            },
            set:function (v) {
                this.updateExe = v;
            }
        }
    },
    methods: {
        getMenu: function () {
            $.get("/sys/resource/findResourceTreeForLoginUser?_" + $.now(), function (o) {
                seed.menuList = o.data[0].children;
                seed.initPage();
            });
        },
        /**
         * 尝试根据url加载对应功能页
         */
        initPage:function () {
            //根据url加载对应页面
            var url = buildUrlByWindowLocationHash(window.location.hash);
            if (url != '/') {
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
                } else if(item[i].types == 1 && item[i].url == url) {
                    return item[i];
                }
            }
        },

    },
    created: function () {
        this.getMenu();
    }

});

