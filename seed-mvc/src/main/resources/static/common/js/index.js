

/**
 * Created by PLC on 2017/8/6.
 */
//iframe自适应
$(window).on('resize', function () {
    var $content = $('.content');
    var width = $(this).width();
    var remove = 120;
    if (width <= 760) {  //宽度在760以下时，header会自动加高，因此要多减一部分
        remove = 170
    }
    //设置页面加载区域高度
    $content.height($(this).height() - remove);
    //设置页面加载frame高度
    $content.find('iframe').each(function () {
        $(this).height($content.height());
    });
}).resize();

// 定义菜单组件
Vue.component('menuItem', {
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li v-if="item.types == 1">',
        '   <a  :href="\'#\'+item.url">',
        '       <i v-if="item.icon != null" :class="item.icon"></i>',
        '       <span>{{item.name}}</span>',
        '   </a>',
        '</li>',
        '<li v-else-if="item.types == 2" class="treeview">',
        '   <a href="#">',
        '       <i v-if="item.icon != null" :class="item.icon"></i>',
        '       <i v-else class="fa fa-link"></i>',
        '           <span>{{item.name}}</span>',
        '       <span class="pull-right-container">',
        '           <i class="fa fa-angle-left pull-right"></i>',
        '       </span>',
        '   </a>',
        '   <ul class="treeview-menu">',
        '<menu-item :item="item" v-for="item in item.children"></menu-item>',
        '   </ul>',
        '</li>'
    ].join('')
});

var mainPanel = new Vue({
    el: '#main-panel',
    data: {
        loadUrl: '/homePage.html',
        menuList: {},
        navTitle: '',
        progressShow: true,
        percentage: 0,
        timer: null
    },
    methods: {
        getMenu: function () {
            $.get("/sys/resource/findResourceTreeForLoginUser?_" + $.now(), function (o) {
                console.log(o);
                mainPanel.menuList = o.data[0].children;
            });
        },


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
                mainPanel.loadUrl = url.replace('#', '');
                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");

                mainPanel.navTitle = $("a[href='" + url + "']").text();
                // 开启加载条
                mainPanel.$Loading.start();
            });
        }
    }
}

//设置进度加载条
mainPanel.$Loading.config({
    color: '#264e6a',
    height: 50
});