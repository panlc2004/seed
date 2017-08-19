/**
 * Created by PLC on 2017/8/6.
 */
// iframe自适应
// $(window).on('resize', function () {
//     var $content = $('.content');
//     console.log($content.width())
//     // var remove = 120;
//     // //设置页面加载区域高度
//     // $content.height($(this).height() - remove);
//     // //设置页面加载frame高度
//     $content.find('iframe').each(function () {
//         $(this).width($content.width());
//     });
// }).resize();

// 主菜单栏
var menuItem = Vue.extend({
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
            console.log(child)
            window.location.hash = child.url
        },
        loadInTab:function (child) {

        },
        loadPage: function (child) {
            if(window.pageloadInTab) {
                this.loadInTab(child);
            } else {
                this.loadInFrame(child);
            }
        }
    }

})


Vue.component('menuItem', menuItem);

window.mainPanel = new Vue({
    el: '#main-panel',
    data: {
        loadUrl: 'homePage.html',
        menuList: {},
        navTitle: '',
        collapse: false,
        tempValue:{}
    },
    methods: {
        getMenu: function () {
            $.get("/sys/resource/findResourceTreeForLoginUser?_" + $.now(), function (o) {
                mainPanel.menuList = o.data[0].children;
            });
        },
        openWin: function (title, url, area) {
            czy.win._open(title, url, area)
        }
    },
    created: function () {
        this.getMenu();
    },
    updated: function () {
        //路由
        var router = new Router();
        routerList(router, mainPanel.menuList);
        router.start();
    }
});

function routerList(router, menuList) {
    for (var key in menuList) {
        var menu = menuList[key];
        if (menu.types == 2) {
            routerList(router, menu.children);
        } else if (menu.types == 1) {
            router.add('#' + menu.url, function () {
                var url = window.location.hash;
                //替换iframe的url
                mainPanel.loadUrl = url.replace('#', '') + ".html";
                //导航菜单展开
                $(".active").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");

                mainPanel.navTitle = $("a[href='" + url + "']").text();
                // 开启加载条
                // mainPanel.$Loading.start();
            });
        }
    }
}
