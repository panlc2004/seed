define(['text!sys/param/param-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,    //编辑页面是否弹出
                buttonshow: false,

                sysDeptList: [], //部门下拉数据
                entity: {
                    sysDeptId: ''
                },     //保存表单提交数据
                rules: {

                    code: [
                        {required: true, message: '请输入用户编码'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    name: [
                        {required: true, message: '请输入用户姓名'},
                        {max: 60, message: '输入长度不能超过50字符'}
                    ],
                    value: [
                        {required: true, message: '请输入值'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    memo: [
                        {required: true, message: '请输入用户姓名'},
                        {max: 60, message: '输入长度不能超过50字符'}
                    ]
                }
            };
        },
        methods: {
            open1: function () {
                // this.$nextTick(function() {
                //     alert(document.querySelector('.v-modal').parentNode.tagName);
                // });
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
                        //_this.buttonshow=true;
                        seed.ajax.postJson({
                            url: 'sys/param/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                                //_this.buttonshow=false;
                            }
                           /* error:function (data,status) {
                                _this.buttonshow=false;
                            }*/
                        })
                    } else {
                        return false;
                    }
                });
            },
            getSysDeptList: function () {
                var _this = this;
                seed.ajax.post({
                    url: 'sys/dept/selectOwnOrgDeptList',
                    success: function (response) {
                        _this.sysDeptList = response.data;
                    }
                })
            }
        },
        created: function () {
            this.getSysDeptList();
        }
    };
    return component;
})