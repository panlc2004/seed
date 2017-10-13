define(['text!sys/param/param-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                puut:false,
                entity: {},
                rules: {
                    name: [
                        {required: true, message: '请输入配置项名称'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    code: [
                        {required: true, message: '请输入配置项编码'},
                        //seed.validate.englishNumberUnderLine(1,50)
                        {max: 300, message: '输入长度不能超过300字符'}
                    ],
                    value: [
                        {required: true, message: '请输入配置项值'},
                        {max: 500, message: '输入长度不能超过500字符'}
                    ],
                    memo: [
                        {max: 2000, message: '输入长度不能超过2000字符'}
                    ]
                }
            };
        },
        watch: {
            show: function (val, oldVal) {
                if (!val) {
                    this.$refs['editForm'].resetFields();
                }
            }
        },
        methods: {
            open1:function () {

            },
            open: function () {
                this.show = true;
            },
            close: function () {
                this.show = false;
            },
            save: function () {
                var _this = this;
                this.$refs.editForm.validate(function (valid) {
                    if (valid) {
                        _this.puut = true;
                        seed.ajax.postJson({
                            url: 'sys/param/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success");
                                }
                                _this.puut = false;
                            },
                            error:function(data,status){
                                _this.puut = false;
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        },
        created:function () {
            // seed.validate.test();
        }
    };
    return component;
})