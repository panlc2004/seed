Vue.component("loading-bar", {
    template: [
        '<transition name="fade">',
        '   <div :class="classes" :style="outerStyles" v-show="show">',
        '       <div :class="innerClasses" :style="styles"></div>',
        '   </div>',
        '</transition>'
    ].join(''),
    data: function () {
        return {
            percent: 0,
            status: 'success',
            show: false
        }
    },
    props: {
        color: {
            type: String,
            default: 'primary'
        },
        failedColor: {
            type: String,
            default: 'error'
        },
        height: {
            type: Number,
            default: 2
        }
    },
    computed: {
        classes: function () {
            return 'ivu-loading-bar';
        },
        innerClasses: function () {
            return [
                'ivu-loading-bar-inner',
                {
                    'ivu-loading-bar-inner-color-primary': this.color === 'primary' && this.status === 'success',
                    'ivu-loading-bar-inner-failed-color-error': this.failedColor === 'error' && this.status === 'error'
                }
            ];
        },
        outerStyles: function () {
            return {
                height: this.height + 'px'
            };
        },
        styles: function () {
            var style = {
                width: this.percent + '%',
                height: this.height + 'px'
            };
            if (this.color !== 'primary' && this.status === 'success') {
                style.backgroundColor = this.color;
            }
            if (this.failedColor !== 'error' && this.status === 'error') {
                style.backgroundColor = this.failedColor;
            }
            return style;
        }
    }
});