define(['text!sys/upload/upload-index.html'], function (Template) {
    var component = {
        template: Template,

        data: function () {
            return {
                fileList: [],
                url: 'sys/attachment/selectPageByParams',
                queryParam: seed.queryParam.create()

            };
        },
        mixins: [czyPageBar],
        methods: {
            downLoad: function (entity) {
                debugger;
                window.open("sys/attachment/downLoadFile?id="+entity.id);
                // var _this = this;
                // seed.ajax.postJson({
                //     url: "sys/attachment/downLoadFile" ,
                //       data: entity,
                //     success: function (data, status) {
                //         if (status) {
                //          //   _this.search();
                //         }
                //     }
                // });
            },
            handleRemove: function (file, fileList) {
                console.log(file, fileList);
            },
            handlePreview: function (file) {
                console.log(file);
            }
        }
        //todo
    };

    return {
        component: component
    }
});