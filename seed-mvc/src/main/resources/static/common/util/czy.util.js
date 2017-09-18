(function () {
    window.seed = {
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
                var defaults = {type: "POST"};
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
                        seed.mask.open();   //打开遮罩
                        if (options.beforeSend && typeof options.beforeSend == 'function') {
                            options.beforeSend(XMLHttpRequest);
                        }
                    },
                    success: function (result) {
                        seed.mask.close();   //取消遮罩
                        if (options.success && typeof options.success == 'function') {
                            options.success(result);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        seed.mask.close();   //取消遮罩
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
                seed.ajax.postWithMask(_options);
            }
        },

        /**
         * 查询参数操作
         */
        queryParam: {
            create: function () {
                return {
                    like: {},
                    notLike: {},
                    between: {begin: {}, end: {}},
                    notBetween: {begin: {}, end: {}},
                    in: {},
                    notIn: {},
                    equalTo: {},
                    notEqualTo: {},
                    greatThan: {},
                    greatThanOrEqualTo: {},
                    lessThan: {},
                    lessThanOrEqualTo: {},
                    orderBy: {}
                }
            }
        },
        //校验封装
        validate: {
            /**
             * 输入指定长度的中文
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            chinese: function (min, max) {
                var exp = '^[\u4e00-\u9fa5]{' + min + ',' + max + '}$';
                var msg = '必须输入' + min + '-' + max + '位中文';
                return buildValidate(exp, msg);
            },
            /**
             * 英文
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            english: function (min, max) {
                var exp = '^[A-Za-z]{' + min + ',' + max + '}$';
                var msg = '必须输入' + min + '-' + max + '位英文';
                return buildValidate(exp, msg);
            },
            /**
             * 英文/数字
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            englishNumber: function (min, max) {
                var exp = '^[A-Za-z0-9]{' + min + ',' + max + '}$';
                var msg = '必须输入' + min + '-' + max + '位[英文/数字]';
                return buildValidate(exp, msg);
            },
            /**
             * 英文/数据/下划线
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            englishNumberUnderLine: function (min, max) {
                var exp = '^[A-Za-z0-9_]{' + min + ',' + max + '}$';
                var msg = '必须输入' + min + '-' + max + '位[英文/数字/下划线]';
                return buildValidate(exp, msg);
            },
            /**
             * 中文/英文/数据
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            chineseEnglishNumber: function (min, max) {
                var exp = '^[A-Za-z0-9\u4e00-\u9fa5]{' + min + ',' + max + '}$';
                var msg = '必须输入' + min + '-' + max + '位[汉字/英文/数字]';
                return buildValidate(exp, msg);
            },
            /**
             * 数字
             * @param min 最小长度
             * @param max 最大长度
             * @param decimal 每位数字的范围
             * @returns {{pattern, message}}
             */
            number: function (min, max, range) {
                var _range = '0-9';
                if (range) {
                    _range = range;
                }
                var exp = '^[' + _range + ']{' + min + ',' + (isNaN(max) ? min : max) + '}$';
                var msg = '只能输入' + (isNaN(max) ? min : (min + '-' + max)) + '位[' + _range + '范围内的数字]';
                return buildValidate(exp, msg);
            },
            /**
             * 固定电话
             * @returns {{pattern, message}}
             */
            telphone: function () {
                var exp = '^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$';
                var msg = '固定电话格式为：010-12345678';
                return buildValidate(exp, msg);
            },
            /**
             * 移动电话
             * @returns {{pattern, message}}
             */
            mobile: function () {
                var exp = '^((((13[0-9]{1})|189|(15[0-9]{1})|(0[1-9]{2}))+\d{8})|((0[1-9]{3}))+\d{7})$';
                var msg = '移动电话格式不正确';
                return buildValidate(exp, msg);
            },
            /**
             * 身份证
             * @returns {{pattern, message}}
             */
            idCard: function () {
                var exp = '^[0-9]{17}([0-9]{1}|x)';
                var msg = '正确的身份证号为[15/18位数字或17位数字+"x"]组成';
                return buildValidate(exp, msg);
            },
            /**
             * 半角符号
             * @param min 最小长度
             * @param max 最大长度
             * @returns {{pattern, message}}
             */
            halfAngle: function (min, max) {
                var exp = '^[\u0000-\u00FF]{' + min + ',' + max + '}$';
                var msg = '只能输入' + min + '-' + max + '位[半角字符]';
                return buildValidate(exp, msg);
            },
            /**
             * 邮箱
             * @returns {{pattern, message}}
             */
            email: function () {
                var exp = '^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$';
                var msg = '正确的邮箱格式应类似"xxx@seed.com"';
                return buildValidate(exp, msg);
            },
            /**
             *邮政编码
             * @returns {{pattern, message}}
             */
            postCode: function () {
                var exp = '[1-9]\\d{5}(?!\\d)';
                var msg = '邮政编码格式不正确';
                return buildValidate(exp, msg);
            },
            /**
             * 浮点数
             * @param min 最小长度
             * @param max 最大长度
             * @param decimal 小数位最大长度
             * @returns {{pattern, message}}
             */
            float: function (min, max, decimal) {
                var _decimal = 2;
                if(decimal) {
                    _decimal = decimal;
                }
                var exp = '^((([1-9]\\d{' + min + ',' + (max - 1) + '})|[0]?)(\\.\\d{1,' + _decimal + '})?)$';
                var msg = '必须输入[' + min + '-' + max + ']位长度的浮点数,且最多允许[' + _decimal + ']位小数）';
                return buildValidate(exp, msg);
            },
        }
    };
})();

function buildValidate(exp, msg) {
    return {
        pattern: new RegExp(exp),
        message: msg
    }
}