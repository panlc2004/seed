require.config({
    // baseUrl: '/',
    paths: {
        text: 'lib/requirejs/text',
        vue: 'lib/vue/2.4.2/vue',
        vueRouter: 'lib/vue/vue-router',
        ELEMENT: 'lib/elementui/1.4.2/index',
        jquery: 'lib/jquery/jquery.min',
        seedcomp: 'lib/czyseed/index',
        seedutil: 'common/util/czy.util',
        elementCss: 'lib/elementui/1.4.2/theme-default/index.css',
        awesomeCss: 'lib/awesome/css/font-awesome.min.css'
    }

});

require(['text','index']);  //在此处引用index.js,防止因加载顺序出错而报错
