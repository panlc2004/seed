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
                autoLoad: false,
                dictId: '',
                deptData: [],
                url: 'sys/dictItem/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                var querpParam = {equalTo: {'sysDictId': this.dictId}};
                this.reload(querpParam);
            },

            cacheDictId: function (dictId) {
                this.dictId = dictId;
            },

            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
                edit.open(this.dictId);
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open(this.dictId);
            },

            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var url = 'sys/dictItem/deleteByPrimaryKey/' + entity.id
                    seed.ajax.postJson({
                        url: url,
                        success: function (data, status) {
                            if (status) {
                                _this.$message({
                                    type: 'success',
                                    message: '操作成功!'
                                });
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
            //查找一级部门
        }
    };

    return component         //返回组件

});