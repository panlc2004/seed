define(['text!sys/upload/upload-index.html'], function (Template) {
    var component = {
        template: Template,

        data: function () {
            return {
                fileList: []
            }
        },
        methods: {
            handleRemove: function (file, fileList) {
                console.log(fileList.url);
                console.log(file, fileList);
            },
            handlePreview: function (file) {
                console.log(this.fileList.url);
                var _this = this;
                this.$refs.editForm.validate(function (valid) {
                    if (valid) {
                        seed.ajax.postJson({
                            url: 'sys/role/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                                _this.disabled = false;
                            },
                            error: function (data, status) {
                                _this.disabled = false;
                            }
                        })
                    } else {
                        return false;
                    }
                });
                console.log(file);

            }
        }
        //todo
    };
    return {
        component: component
    }
});