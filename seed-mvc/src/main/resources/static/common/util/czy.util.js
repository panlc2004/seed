(function () {
    window.czy = {
        win: {
            open: function (title, url, area) {
                if (parent.mainPanel != undefined) {
                    parent.mainPanel.openWin(title, url, area);
                } else {
                    this._open(title, url, area);
                }
            },

            _open: function (title, url, area) {
                var defaults = {
                    type: 2,
                    title: title,
                    area: area,
                    // closeBtn:2,
                    fixed: false,
                    maxmin: false,
                    content: url
                };
                if (!area) {
                    defaults.area = ['50%', '50%'];
                }
                var _options = $.extend({}, defaults);
                layer.open(_options);
            },

            close: function () {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
        },

        /**
         * 参数处理
         */
        param: {
            temp: {
                _tempValue: {},
                entityEditKey: 'ENTITY_EDIT_KEY',
                set: function (key, value) {
                    if (parent.mainPanel != undefined) {
                        parent.mainPanel.tempValue[key] = value;
                    } else {
                        this._tempValue[key] = value;
                    }
                },
                get: function (key) {
                    if (parent.mainPanel != undefined) {
                        return parent.mainPanel.tempValue[key];
                    } else {
                        return this._tempValue[key];
                    }
                },
                /**
                 * 传递修改对象
                 * @param value
                 */
                setEntity: function (value) {
                    var view = value.view;
                    if (view) {
                        if(typeof view.window == 'object') {
                            this.set(this.entityEditKey, {});
                            return;
                        }
                    }
                    this.set(this.entityEditKey, value);
                },
                /**
                 * 获取修改对象
                 */
                getEntity: function () {
                    var entity = this.get(this.entityEditKey);
                    if (entity) {
                        return entity;
                    } else {
                        return {};
                    }
                }
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