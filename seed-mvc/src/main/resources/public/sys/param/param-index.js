define(['text!sys/param/param-index.html'], function (Template) {
    var component = {
        template:Template,
        mixins: [czyPageBar],
        data:function () {
            return{
                url: 'sys/param/selectPageRelativeByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods:{
            search:function () {
                this.reload(this.queryParam);
            },
            toAdd:function () {
                
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    seed.ajax.postJson({
                        url: 'sys/param/deleteByPrimaryKey/' + entity.id,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    })
                }).catch(function () {});
            },
        }
    }

    return {
        component: component         //返回组件
    }
});


