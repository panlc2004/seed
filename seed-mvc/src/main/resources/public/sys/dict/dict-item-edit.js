define(['text!sys/dict/dict-item-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                dictId: '',
                show: false,    //编辑页面是否弹出
                sysDeptList: [], //部门下拉数据
                disabled: false,
                entity: {
                    sysDictId: ''
                },     //保存表单提交数据
                rules: {
                    itemCode: [
                        {required: true, message: '请选择编码'},
                        seed.validate.englishNumberUnderLine(1, 300),
                    ],
                    value: [
                        {required: true, message: '请输入值'},
                        {max: 60, message: '输入长度不能超过150字符'}
                    ],
                    memo: [
                        {max: 60, message: '输入长度不能超过150字符'}
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
            open: function (en) {
                this.show = true;
                this.dictId = en;
            },
            close: function () {
                this.show = false;
            },
            save: function () {
                debugger;
                var _this = this;
                _this.entity.sysDictId = _this.dictId;
                this.$refs.editForm.validate(function (valid) {
                    if (valid) {
                        _this.disabled = true;
                        seed.ajax.postJson({
                            url: 'sys/dictItem/insertItem',
                            data: _this.entity,
                            success: function (data, status) {
                                _this.disabled = false;
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

