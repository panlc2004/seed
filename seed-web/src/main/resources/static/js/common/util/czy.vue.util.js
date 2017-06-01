/**
 * Created by PLC on 2017/6/1.
 */
(function() {
    window.czy = {
        operateWin: function(id,url){
            var operateWin = new Vue({
                el: id,
                data: {formData: {}},
                methods: {
                    save: function () {
                        $.post(url,formData, function(data) {

                        })
                    }
                }
            });
        }
    }
})();