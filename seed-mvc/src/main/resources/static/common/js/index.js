

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
                // mainPanel.$Loading.start();

                if(mainPanel.timer==null) {
                    mainPanel.timer = setInterval(function () {
                        mainPanel.percentage = mainPanel.percentage + 1;
                        // 父组件数据加载完前进度条最多到stopVal的这个百分值
                        if (mainPanel.percentage >= 90) {
                            clearInterval(mainPanel.timer)
                        }
                    }, 10);
                }

            });
        }
    }
}

/**
 * 启动进度条
 * @param stopPercent
 */
function startProgress(stopPercent) {
    mainPanel.progressShow = true;
    if (mainPanel.timer == null) {
        mainPanel.timer = setInterval(function () {
            mainPanel.percentage = mainPanel.percentage + 10;
            // 父组件数据加载完前进度条最多到stopVal的这个百分值
            if (mainPanel.percentage >= stopPercent) {
                clearInterval(mainPanel.timer)
                // return timer;
            }
        }, 100);
    }
}

/**
 * 关闭进度条
 */
function finishProgress() {
    mainPanel.percentage = 100;
    mainPanel.progressShow = false;
}

function test123() {
    var iframe = $("#page-load-progress");
    //先为iframe 添加一个 onreadystatechange
    iframe.attachEvent("onreadystatechange", function(){
        //此事件在内容没有被载入时候也会被触发，所以我们要判断状态
        //有时候会比拟怪异 readyState状态会跳过 complete 所以我们loaded状态也要判断
        if(iframe.readyState === "complete" || iframe.readyState == "loaded"){
            //代码能执行到这里说明已载入胜利完毕了
            //要清除掉事件
            iframe.detachEvent( "onreadystatechange", arguments.callee);
            //这里是回调函数
        }

    });
}


// test123();