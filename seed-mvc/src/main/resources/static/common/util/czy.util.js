(function () {
    window.czy = {
        /**
         * 子组件操作
         */
        //子组件缓存
        _compCache: {},
        /**
         * 尝试加载子组件，已经加载过的不再加载
         * @param url 组件定义文件url
         */
        loadComponent: function (url, targetDiv, callback) {
            //根据url计算组件名称
            var component_name = buildComponentNameByUrl(url);
            if (czy._compCache[component_name] == undefined) {  //未加载过的组件执行加载
                $("#component-cache").load(url, function (data, status) {
                    if (status == 'success') {
                        var componentType = eval(component_name);       //取得组件定义
                        var component = new componentType();            //创建组件实例
                        component.$mount(targetDiv);                     //挂载组件到指定dom
                        czy._compCache[component_name] = component;    //缓存组件名称及组件实例

                        //调用回调方法
                        callback(component);
                    } else {
                        alert('加载url：' + url + '失败');
                    }
                });

            } else {    //组件已经加载，直接跳转
                var component = czy._compCache[component_name];    //取得缓存组件
                //调用回调方法
                callback(component);
            }
        },

        /**
         * ajax封装
         */
        ajax: {
            postJson: function (options) {
                var defaults = {
                    type: "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json"
                };
                var _options = $.extend({}, defaults, options);
                _options.data = JSON.stringify(options.data);
                $.ajax(_options);
            },
            post: function (options) {
                var defaults = {};
                var callbackOption = {
                    beforeSend: function (XMLHttpRequest) {
                        if (options.beforeSend && typeof options.beforeSend == 'function') {
                            options.beforeSend(XMLHttpRequest);
                        }
                    },
                    success: function (result) {
                        if (options.success && typeof options.success == 'function') {
                            options.success(result);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (options.error && typeof options.error == 'function') {
                            options.error(XMLHttpRequest, textStatus, errorThrown);
                        }
                        //跳转登陆页面    toDo
                        //展示错误信息    toDo
                    }
                }
                var _options = $.extend({}, defaults, options);
                var settings = $.extend({}, _options, callbackOption);
                $.ajax(settings);
            },
            postWithMask: function (options) {
                var defaults = {};
                var callbackOption = {
                    beforeSend: function (XMLHttpRequest) {
                        czy.mask.open();   //打开遮罩
                        if (options.beforeSend && typeof options.beforeSend == 'function') {
                            options.beforeSend(XMLHttpRequest);
                        }
                    },
                    success: function (result) {
                        czy.mask.close();   //取消遮罩
                        if (options.success && typeof options.success == 'function') {
                            options.success(result);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        czy.mask.close();   //取消遮罩
                        if (options.error && typeof options.error == 'function') {
                            options.error(XMLHttpRequest, textStatus, errorThrown);
                        }
                        //跳转登陆页面    toDo
                        //展示错误信息    toDo
                    }
                }
                var _options = $.extend({}, defaults, options);
                var settings = $.extend({}, _options, callbackOption);
                $.ajax(settings);
            },

            /**
             *
             * @param options
             */
            postJsonWithMask: function (options) {
                var defaults = {
                    type: "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json"
                };
                var _options = $.extend({}, defaults, options);
                _options.data = JSON.stringify(options.data);
                czy.ajax.postWithMask(_options);
            }
        },
    };
})();