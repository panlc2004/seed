define(['text!sys/log/log-index.html'], function (Template) {
    var component = {
        template: Template,
        mixins: [czyPageBar],
        data: function () {
            return {
                pageDate: {},
                log: {
                    name: ' ',
                    createDt: ' ',
                    operation: ' '
                },
                url: 'sys/log/selectPageRelative',
                queryParam: seed.queryParam.create(),
            }
        },
        methods: {
            search: function () {
                var _this = this;
                debugger;
                if (_this.log.name === '' || _this.log.name === undefined) {
                    _this.log.name = ' ';
                }
                if (_this.log.createDt === '' || _this.log.createDt === undefined) {
                    _this.log.createDt = ' ';
                }
                if (_this.log.operation === '' || _this.log.operation === undefined) {
                    _this.log.operation = ' ';
                }
                var createDt=[];
                if(_this.log.createDt!==" "){
                    for(var i= 0;i<_this.log.createDt.length;i++)
                    createDt.push(_this.log.createDt[i].getTime())
                }
                _this.log.createDt = createDt.join(',');
                _this.url = 'sys/log/selectPageRelativeByParam/' + _this.log.name + '/' + _this.log.createDt + '/' + _this.log.operation
                this.turnPage();
                // if (_this.log.name == '' && _this.log.createDt == '' && _this.log.operation == '') {
                //     _this.url = 'sys/log/selectPageRelative';
                // } else {
                //     console.log(_this.find);
                //     _this.url = 'sys/log/slecetPageRelativeByParams/'+/' + _this.log;
                //     this.turnPage();
                // }
            },

        }
    };
    return {
        component: component         //返回组件
    }

});