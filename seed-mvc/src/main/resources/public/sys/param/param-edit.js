define(['text!sys/param/param-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                entity:{},
                show: false,    //编辑页面是否弹出
                sysDeptList: [], //部门下拉数据
                rules: {
                    code: [
                        {required: true, message: '请输入参数编码'},
                        seed.validate.englishNumberUnderLine(0,50)
                    ],
                    name: [
                        {required: true, message: '请输入参数名称'},
                        {max: 50, message: '输入长度不能超过25字符'}
                    ],
                    value: [
                        {required: true, message: '请输入can'},
                        {max: 100, message: '长度不能超过100字符'}
                    ],
                    memo: [
                        seed.validate.chinese(6,100)
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
            open1: function () {

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
                        seed.ajax.postJson({
                            url: 'sys/param/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        }
    };
    return component;
})


