define(['text!sys/log/log-index.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                pageData: [],
                log: {
                    name: '',
                    createDt: '',
                    operation: ''
                },
                pageSize: 10,
                currentPage: 1,
                total: 0,
                currentPageNum: '1',
            }
        },
        methods: {
            sizeChange:function (size) {
                this.pageSize = size;
                this.search();
            },
            search: function () {
                var _this = this;
                var _begin = "2017-11-12 10:10:10";
                var _end = "2017-11-12 10:10:10";
                seed.ajax.post({
                    url: 'sys/log/selectExtendPageByParam/' + _this.currentPage + "/" + _this.pageSize,
                    data: {
                        name: _this.log.name,
                        begin: _begin,
                        end: _end,
                        operation:_this.log.operation
                    },
                    success: function (response) {
                        _this.pageData = response.data.page;
                        _this.total = Number(response.data.total);

                    }
                })
            },

            handleCurrentChange: function (pageNum) {
                this.currentPageNum = pageNum;
                this.currentPage = pageNum == undefined ? 1 : pageNum;
                this.search();
            }

        },
        created: function () {
            this.handleCurrentChange();
        }
    };
    return {
        component: component         //返回组件
    }

});