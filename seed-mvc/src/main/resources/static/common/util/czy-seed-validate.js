var seed_validate = new Vue({
    methods: {
        chinese: function (min, max) {
            var exp = '^[\u4e00-\u9fa5]{' + min + ',' + max + '}$';
            var msg = '必须输入' + min + '-' + max + '位中文';
            return buildValidate(exp, msg);
        },
        test: function () {
            alert(1);

        }
    }
});

function buildValidate(exp, msg) {
    return {
        pattern: new RegExp(exp),
        message: msg
    }
}