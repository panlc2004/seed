/**
 * 引用文件加载器
 * Created by PLC on 2017/8/6.
 */

// function getRootPath_web() {
//     //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
//     var curWwwPath = window.document.location.href;
//     //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
//     var pathName = window.document.location.pathname;
//     var pos = curWwwPath.indexOf(pathName);
//     //获取主机地址，如： http://localhost:8083
//     var localhostPaht = curWwwPath.substring(0, pos);
//     //获取带"/"的项目名，如：/uimcardprj
//     var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//     alert(projectName);
//     return (localhostPaht + projectName);
// }
//
// getRootPath_web();

// function getRootPath_dc() {
//     var pathName = window.location.pathname.substring(1);
//     var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
//     if (webName == "") {
//         return window.location.protocol + '//' + window.location.host;
//     }
//     else {
//         return window.location.protocol + '//' + window.location.host + '/' + webName;
//     }
// }
//
// alert(getRootPath_dc());


//全局css定义
var headerCss = [
    "/lib/bootstrap/css/bootstrap.min.css",
    "/lib/awesome/css/font-awesome.min.css",
    "/lib/adminlte/css/AdminLTE.min.css",
    "/lib/adminlte/css/skins/_all-skins.min.css",
    "/lib/elementui/1.4.1/theme-default/index.css"
]
for ( var i = 0; i < headerCss.length; i++) {
    document.writeln('<link rel="stylesheet" href="' +  headerCss[i] + '"></link>');
};

//全局js定义
var headerJs = [
    "/lib/jquery/jquery.min.js",
    "/lib/bootstrap/js/bootstrap.min.js",
    "/lib/adminlte/js/adminlte.min.js",
    "/lib/html5shiv/3.7.3/html5shiv.min.js", //ie8适配
    "/lib/respond/1.4.2/respond.min.js",      //ie8适配
    "/lib/vue/2.4.2/vue.js",
    "/lib/elementui/1.4.1/index.js",
    "/lib/router.js"
]
for ( var i = 0; i < headerJs.length; i++) {
    document.writeln('<script type="text/javascript" src="' +  headerJs[i] + '"></script>');
};