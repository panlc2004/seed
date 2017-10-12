define(['text!sys/dict/dict-item.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/dict/dict-item-edit'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                dictId:'',
                autoLoad: false,
                url: 'sys/dictItem/selectPageByParams',
                queryParam: seed.queryParam.create(),
            }
        },
        methods: {
            addSysDictID:function (dictId) {
                var _this = this;
                _this.dictId=dictId;
            },
            search: function () {
                this.reload({equalTo:{'sysDictId':this.dictId}});
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                var _this = this;
                edit.entity = {
                    sysDictId:_this.dictId
                };
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    var url = 'sys/dictItem/deleteByPrimaryKey/' + entity.id
                    seed.ajax.postJson({
                        url: url,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                }).catch(function () {
                });
            },
            setRole: function (row) {
                var role = this.$refs.role;
                role.open(row.id);
            }
        },
        created: function () {
            this.search();
        }
    };
    return component         //返回组件

});