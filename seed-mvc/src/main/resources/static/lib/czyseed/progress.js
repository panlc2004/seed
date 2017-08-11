Vue.component("progress-bar", {
    template: [
        '<transition name="fade">',
        '<div class="progress-bar" v-if="isShow">',
        '</div>',
        '</transition>'
    ].join(''),
    data: function () {
        return {
            isShow: true, // 是否显示进度条
            val: 0, // 进度
        }
    },
    props: {
        /**
         * 每10毫秒自增幅度
         */
        step: {
            type: Number,
            default: 5,
        },
        /**
         * 初始值
         */
        initVal: {
            type: Number,
            default: 0,
        },
        /**
         * 到一定进度停止
         */
        stopVal: {
            type: Number,
            default: 80,
        },
        /**
         * 进度条继续到成功
         */
        isOk: {
            type: Boolean,
            default: false,
        },
    },
    mounted: function () {
        // 初始化后加载进度，加载到百分之多少由stopVal决定
        this.val = this.initVal;
        var step = this.step
        var timer = setInterval(function () {
            this.val = this.val + step
            this.$el.style.width = this.val + '%'

            // 父组件数据加载完前进度条最多到stopVal的这个百分值
            if (this.val >= this.stopVal) {
                clearInterval(timer)
                return
            }
        }, 10);
    },

})