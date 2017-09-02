/**
 * 引用文件加载器
 * Created by PLC on 2017/8/6.
 */
//全局css定义
var headerCss = [
    "/seed/lib/awesome/css/font-awesome.min.css",
    "/seed/lib/elementui/1.4.3/theme-default/index.css"
]
for ( var i = 0; i < headerCss.length; i++) {
    document.writeln('<link rel="stylesheet" href="' +  headerCss[i] + '"/>');
};

//全局js定义
var headerJs = [
    "/seed/title.js",
    "/seed/lib/jquery/jquery.min.js",
    "/seed/lib/vue/2.4.2/vue.js",
    "/seed/lib/elementui/1.4.3/index.js",
    "/seed/lib/layer/layer-3.0.3.js",
    "/common/util/czy.util.js",
    "/seed/lib/czyseed/index.js"
]

for ( var i = 0; i < headerJs.length; i++) {
    document.writeln('<script type="text/javascript" src="' +  headerJs[i] + '"></script>');
};