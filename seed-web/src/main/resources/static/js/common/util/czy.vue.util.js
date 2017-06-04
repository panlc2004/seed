/**
 * Created by PLC on 2017/6/1.
 */
(function () {
    window.czy = {
        msg: {
            success:function (msg) {
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
                    type: 'warn',
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
        confirm: function (obj, msg, title, url, params, callback) {
            debugger;
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
        ajax:{
            post: function(url, param, callback) {
                $.ajax({
                    type:"POST",
                    url: url,
                    dataType:"json",
                    contentType:"application/json",
                    data:JSON.stringify(param),
                    success:function(data){
                        if(data.code == 200) {
                            czy.msg.success(data.msg);
                        } else {
                            czy.msg.error(data.msg)
                        }
                        if (callback) {
                            callback(data, status);
                        }
                    }
                });
            }
        },
        // post:function(url,params,callcack){
        //     $.post(url,params,)
        // }
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