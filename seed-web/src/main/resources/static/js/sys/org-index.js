/**
 * Created by PLC on 2017/5/30.
 */
$(document).ready(function () {

    var data = [{
        id: 1,
        name: '一级 1111',
        children: [{
            id: 4,
            name: '二级 1-1',
            children: [{
                id: 9,
                name: '三级 1-1-1'
            }, {
                id: 10,
                name: '三级 1-1-2'
            }]
        }]
    }, {
        id: 2,
        name: '一级 2',
        children: [{
            id: 5,
            name: '二级 2-1'
        }, {
            id: 6,
            name: '二级 2-2'
        }]
    }, {
        id: 3,
        name: '一级 3',
        children: [{
            id: 7,
            name: '二级 3-1'
        }, {
            id: 8,
            name: '二级 3-2'
        }]
    }];

    var orgTree = new Vue({
        el: '#orgTree',
        data: {
            data: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            }
        },
        created: function () {
            this.data = data;
        }
    });
});