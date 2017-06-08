/**
 * Created by PLC on 2017/6/1.
 */
(function () {
    window.czy = {
        /**
         *弹出消息工具方法
         */
        msg: {
            success: function (msg) {
                main_contain.$message({
                    type: 'success',
                    message: msg
                });
            },
            error: function (msg) {
                main_contain.$message({
                    type: 'error',
                    message: msg
                });
            },
            warn: function (msg) {
                main_contain.$message({
                    type: 'warning',
                    message: msg
                });
            },
            info: function (msg) {
                main_contain.$message({
                    type: 'info',
                    message: msg
                });
            }
        },
        /**
         * 确认弹窗并执行任务——临时，不要使用
         * @param obj
         * @param msg
         * @param title
         * @param url
         * @param params
         * @param callback
         */
        confirm: function (obj, msg, title, url, params, callback) {
            if (params == undefined || params == null) {
                params = {};
            }
            main_contain.$confirm(msg, {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(
                function () {
                    $.post(url, {params: params}, callback(data));
                }).catch(
                function () {
                });
        },
        query: {
            params: function (pageNum, pageSize, params) {
                var _params = {"pageNum": 0, "pageSize": 20, "params": {}}
                _params.params = params == undefined ? params : {};
                _params.pageNum = pageNum == undefined ? 1 : pageNum;
                _params.pageSize = pageSize == undefined ? 1 : pageSize;
                return _params;
            }
        },
        /**
         * 遮罩
         */
        loading: window.ELEMENT.Loading,
        mask: {
            open: function () {
                czy.loading.service({fullscreen: true, text: '拼命加载中...'});
            },
            close: function () {
                czy.loading.service({fullscreen: false, text: '拼命加载中...'});
            }
        },
        /**
         * ajax请求，参数为json格式数据
         */
        ajax: function (options, success, error) {
            var defaults = {
                contentType: "application/json;charset=UTF-8",
            };
            var callbackOption = {
                success: function (result) {
                    czy.mask.close();   //取消遮罩
                    success(result);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    czy.mask.close();   //取消遮罩
                    error(XMLHttpRequest, textStatus, errorThrown);
                    //跳转登陆页面    toDo
                    //展示错误信息    toDo
                }
            }
            var options = $.extend(defaults, options, callbackOption);
            czy.mask.open();   //打开遮罩
            $.ajax(options);
        },
        operateWin: function (id, url) {
            var operateWin = new Vue({
                el: id,
                data: {formData: {}},
                methods: {
                    save: function () {
                        $.post(url, formData, function (data) {

                        })
                    }
                }
            });
        }
    };
})();