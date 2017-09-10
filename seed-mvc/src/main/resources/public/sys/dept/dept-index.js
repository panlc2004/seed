define(['text!sys/dept/dept-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/org/org-edit'], resolve);
            }
        },
        data: function () {
            return {
                url: 'sys/dept/selectPageByParams',
                deptData: [],
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                this.reload(this.queryParam);
            },
            selectSubDept: function (row, callback) {
                this.selectListByParentId(row.id, function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, callback) {
                seed.ajax.post({
                    url: 'sys/dept/selectListByParentId',
                    data: {
                        parentId: parentId
                    },
                    success: function (response) {
                        callback(response);
                    }
                })
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            del: function () {

            }
        },
        created: function () {
            //查找一级部门
            var _this = this;
            this.selectListByParentId(0, function (response) {
                _this.deptData = response.data;
                console.log(_this.deptData)

            });
        }
    };

    return {
        component: component         //返回组件
    }

});