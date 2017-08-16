(function () {
    window.czy = {
        openWin:function (title, url, area) {
            if(parent.mainPanel != undefined) {
                parent.mainPanel.openWin(title, url, area);
            } else {
                this._openWin(title, url, area);
            }
        },

        _openWin: function (title, url, area) {
            var defaults = {
                type: 2,
                title:title,
                area: area,
                // closeBtn:2,
                fixed: false,
                maxmin: false,
                content: url
            };
            if(!area) {
                defaults.area = ['50%', '50%'];
            }
            var _options = $.extend({}, defaults);
            layer.open(_options);
        },
    }
})();