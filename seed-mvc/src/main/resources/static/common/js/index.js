/**
 * Created by PLC on 2017/8/6.
 */
// 菜单栏
Vue.component('menuItem',{
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
        loadInTab:function (child) {

        },
        loadPage: function (child) {
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
var router = new VueRouter({
    routes: routes
});

/**
 * 加载组件并设置路由
 * @param menu
 */
function loadComponent(url) {
    $("#component-cache").load(ctx + url,function (data,status) {
        if(status=='success') {
            var component_name = buildComponentNameByUrl(url);
            var router_path = '/' + component_name;
            var component = eval(component_name)
            router.addRoutes([{path: router_path, component: component}])
            router.push(router_path);
        } else {
            alert('加载url：' + url + '失败');
        }
    });
}

String.prototype.startWith=function(str){
    var reg=new RegExp('^'+str);
    return reg.test(this);
}

String.prototype.endWith=function(str){
    var reg=new RegExp(str+"$");
    return reg.test(this);
}

String.prototype.replaceAll=function(str,value){
    var regExp = new RegExp(str,'gm');
    return this.replace(regExp, value);
}

/**
 * 计算组件名称
 * @param url
 */
function buildComponentNameByUrl(url) {
    if(!url.startWith('/')) {
        alert("url必须以'/'开头")
        return;
    }
    var component_name = url.replace('/','').replaceAll('\/', '$').replaceAll('-', '_');
    if(url.endWith('.html')){
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
    locationHash = locationHash.replace('#','')
    if(!locationHash.startWith('/')) {
        alert("url必须以'/'开头")
        return;
    }
    if(locationHash.endWith('_html')) {
        locationHash = locationHash.replace('_html','.html');
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
        navTitle: '',
        collapse: false,
        defaultActive:'1'
    },
    methods: {
        getMenu: function () {
            $.get("/sys/resource/findResourceTreeForLoginUser?_" + $.now(), function (o) {
                seed.menuList = o.data[0].children;
            });
        },
        openWin: function (title, url, area) {
            czy.win._open(title, url, area)
        }
    },
    created: function () {
        this.getMenu();
    },
    mounted:function () {
        //根据url加载对应页面
        var url = buildUrlByWindowLocationHash(window.location.hash);
        loadComponent(url);
        this.defaultActive = '2';   //让指定菜单置为激活状态 TODO
    }

});

