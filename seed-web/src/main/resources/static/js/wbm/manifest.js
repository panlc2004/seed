var defaults = {
    cabinsForm: {
        //客舱类型,第一个数据是为了在构建table的时候 0-0 的占位
        passengerTypes: [{key: ""}, {key: "Adult"}, {key: "Children"}, {key: "Infant"}],
        passengerCabins: [{key: Date.now(), tableData: [{aircraftCabin: [{key: Date.now()}], key: Date.now()}]}],//[{key: Date.now(), name: "", inputData: [{key: Date.now(), name: ''}]}],
        segments: [{key: new Date()}]
    },
    cargoHoldForm: {
        //货舱类型,第一个数据是为了在构建table的时候 0-0 的占位
        cargoTypes: [{key: ""}, {key: "Cargo"}, {key: "Bag"}, {key: "Mail"}],
        cargos: [{key: Date.now(), tableData: [{aircraftCabin: [{key: Date.now()}], key: Date.now()}]}],//[{key: Date.now(), name: "", inputData: [{key: Date.now(), name: ''}]}],
        segments: [{key: new Date()}]
    },
    crews: [
        {positionCode: "COCKPIT"},// 驾驶舱
        {positionCode: "FWD"},// 前舱
        {positionCode: "MID"},// 中舱
        {positionCode: "AFT"}// 后舱
    ],
    galleyGoods: [
        {positionCode: "FWD"},// 前舱
        {positionCode: "AFT"}// 后舱
    ],
    segmentInput: [
        {sgment: "A"},
        {sgment: "B"},
        {sgment: "C"},
        {sgment: "D"}
    ],
    //简单数据回显时使用的字段名字,便于循环赋值
    configuration: ["baseIndex", "baseWeight", "limitedMtow", "limitedMldw", "limitedMzfw", "takeOffFuel", "tripFuel"],
    //缓存动态构建的电子舱单货舱单航段对象,用于添加单航段或者多航段
    cacheCargoObj: {},
    //缓存动态构建的电子舱单客舱单行对象,用于提那家单航段或者多航段
    cacheCabinObj: {}
}

var cfg_manifest = new Vue({
    el: "#manifest",
    data: function () {
        return {
            disabled: true,//航班号不确定不能选择航段信息
            showCargoAndCabin: false,//显示货舱和客舱card
            showSegmentTag: false,//是否在客舱货舱上显示航段信息标签
            showSegmentA_B: false,//显示A B 航段输入框
            showSegmentA_B_C: false, //显示A B C 航段输入框
            showSegmentA_B_C_D: false, //显示A B C D 航段输入框
            showFtl: "",//显示电子舱单模板
            checkResultData: "",
            currentDate: new Date(),
            infoForm: {
                crews: defaults.crews,
                galleyGoods: defaults.galleyGoods,
                flightInfo: {},
                segmentInput: [],
                segmentA: '', //A 站输入框
                segmentB: '', //B 站输入框
                segmentC: '', //C 站输入框
                segmentD: '', //D 站输入框
            },
            radio2: 1, //航段单选按钮
            cabinsForm: defaults.cabinsForm, //客舱信息组装
            cargoHoldForm: defaults.cargoHoldForm, //货舱信息组装
            restaurants: [], //航班号搜索框
            timeout: null,
            flightNo: '' //航班号
        }
    },
    methods: {
        radioChange: function (value) {
            //清空客舱和货舱航段
            this.cabinsForm.passengerCabins = [];
            this.cargoHoldForm.cargos = [];
            //动态构建航段
            var lenght = 1;

            var inptLength = 0;
            if (value == 1) {
                lenght = 1;
            }
            if (value == 2 || value == 3) {
                lenght = 2;
                inptLength = 3;
            }
            if (value == 4 || value == 6) {
                lenght = 3;
                inptLength = 4;
            }
            if (value == 5) {
                lenght = 4;
                inptLength = 4;
            }
            //控制航段输入框的显示和隐藏
            if (value < 2) {
                this.showSegmentA_B = true;
                this.showSegmentA_B_C = false;
                this.showSegmentA_B_C_D = false;
            } else if (value >= 2 && value < 4) {
                this.showSegmentA_B = true;
                this.showSegmentA_B_C = true;
                this.showSegmentA_B_C_D = false;
            } else {
                this.showSegmentA_B = true;
                this.showSegmentA_B_C = true;
                this.showSegmentA_B_C_D = true;
            }

            //根据选择得航段动态生成货舱和客舱输入项
            for (var i = 0; i < lenght; i++) {
                var cabinObj = $.extend(true, {}, defaults.cacheCabinObj);
                this.cabinsForm.passengerCabins.push(cabinObj);
                //深度拷贝
                var cargoObj = $.extend(true, {}, defaults.cacheCargoObj);
                this.cargoHoldForm.cargos.push(cargoObj);
            }

            this.infoForm.segmentInput = [];
            //在动态生成的客舱和货舱card 显示卡上显示航段信息
            //if (inptLength != 0) {
            this.showSegmentTag = true;
            // }

            for (var i = 0; i < inptLength; i++) {
                this.infoForm.segmentInput.push(defaults.segmentInput[i]);
            }
        },
        searchByFlightNo: function () {
            var data = [];
            $.ajax({
                url: "cfg/flightInfo/searchFlightInfo",
                data: {},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    data = result.data
                },
                error: function (error) {
                    var text = error.responseJSON.msg;
                    if (text && text.length > 100) {
                        text = text.substring(0, 100);
                    }
                    czy.msg.error(text);
                }
            });
            return data;
        },
        querySearchAsync: function (queryString, callBack) {
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;
            callBack(results);
        },

        //航班号过滤数据
        createStateFilter: function (queryString) {
            return function (state) {
                return (state.value.indexOf(queryString) === 0)
            }
        },
        handleSelect: function (item) {
            this.showSegmentA_B = true;
            this.radio2 = 1;
            //回显数据
            for (var val in defaults.configuration) {
                var configuration = defaults.configuration[val];
                var value = item.flightConfig[configuration];
                this.infoForm[configuration] = value;
            }
            this.infoForm.seatNum = item.flightTypeConfig.seatNum;
            //
            this.infoForm.segment = item.segment;

            var crews = item.flightTypeConfig.crewList;
            var galleyGoods = item.flightTypeConfig.galleyGoodsList;
            var holdList = item.flightTypeConfig.cargoHoldList;
            var cabinsList = item.flightTypeConfig.passengerCabinList;
            //保存数据用于电子舱单生成时数据回显使用数据
            this.infoForm.flightInfo = item;

            this.infoForm.crews = defaults.crews;
            this.infoForm.galleyGoods = defaults.galleyGoods;
            //乘务信息组装数据回显
            for (var key in crews) {
                var crew = crews[key];
                var defCrews = this.infoForm.crews;
                for (var index in defCrews) {
                    if (crew.positionCode == defCrews[index].positionCode) {
                        this.infoForm.crews[key].standardNum = crew.standardNum;
                    }
                }
            }
            //机供品组装数据回显
            for (var key in galleyGoods) {
                var galleyGood = galleyGoods[key];
                var defGalleyGoods = this.infoForm.galleyGoods;
                for (var index in defGalleyGoods) {
                    if (galleyGood.positionCode == defGalleyGoods[index].positionCode) {
                        this.infoForm.galleyGoods[key].standardWeight = galleyGood.standardWeight;
                    }
                }
            }


            //组装货舱信息
            var types = defaults.cargoHoldForm.cargoTypes;
            var segments = this.cargoHoldForm.segments;
            this.cargoHoldForm.cargos = [];
            //组装航段
            for (var skey in segments) {
                var obj = {};//航段
                obj.tableData = [];
                //组装多少行数
                for (var index in types) {
                    var holtType = {};//
                    holtType.aircraftCabin = [];
                    holtType.holtType = types[index].key;
                    var defaultHoldObj = {};
                    //添加表格列名占位
                    defaultHoldObj.index = skey + "-" + index + "-" + "colOne";
                    defaultHoldObj.name = "colOne";
                    holtType.aircraftCabin.push(defaultHoldObj);
                    //组装多少列
                    for (var key in  holdList) {
                        var holdObj = {};
                        holdObj.index = skey + "-" + index + "-" + key;
                        var hold = holdList[key];
                        holdObj.name = hold.name;
                        //给货舱输入项加入默认值
                        holdObj.value = 0;
                        holtType.aircraftCabin.push(holdObj);
                    }
                    holtType.fullScale = 0;
                    obj.tableData.push(holtType);
                }
                //缓存每个货舱数据信息便于多航段使用
                //$.extend  方法第一个参数为boolean
                defaults.cacheCargoObj = $.extend(true, {}, obj);
                this.cargoHoldForm.cargos.push(obj);
            }


//------------------------------组装客舱信息----------------------------------------//

            //组装货舱信息
            var types = defaults.cabinsForm.passengerTypes;
            var segments = this.cabinsForm.segments;
            this.cabinsForm.passengerCabins = [];
            //组装航段
            for (var skey in segments) {
                var obj = {};//航段
                obj.tableData = [];
                //组装多少行数
                for (var index in types) {
                    var cabinType = {};//
                    cabinType.aircraftCabin = [];
                    cabinType.cabinType = types[index].key;
                    var defaultCabinObj = {};
                    //添加表格列名占位
                    defaultCabinObj.index = skey + "-" + index + "-" + "colOne";
                    defaultCabinObj.name = "colOne";
                    cabinType.aircraftCabin.push(defaultCabinObj);
                    //组装多少列
                    for (var key in  cabinsList) {
                        var cabinObj = {};
                        //index 和 name 字段在实际使用中并没有使用,做为调试参数
                        cabinObj.index = skey + "-" + index + "-" + key;
                        var cabin = cabinsList[key];
                        cabinObj.name = cabin.name;
                        //给客舱输入项加入默认值
                        cabinObj.value = 0;
                        cabinType.aircraftCabin.push(cabinObj);
                    }
                    cabinType.fullScale = 0;
                    obj.tableData.push(cabinType);
                }
                //缓存每个客舱数据信息便于多航段使用
                //$.extend  方法第一个参数为boolean
                defaults.cacheCabinObj = $.extend(true, {}, obj);
                this.cabinsForm.passengerCabins.push(obj);
            }

            this.disabled = false;
            this.showCargoAndCabin = true;
        },
        //提交电子舱单,货舱代码
        submitManifest: function () {
            var cargoData = cfg_manifest.cargoHoldForm;
            var cabinData = cfg_manifest.cabinsForm;

            //客舱数据提交表格结果集数据对象
            var cCabins = cabinData.passengerCabins;
            //货舱数据提交表格结果集数据对象
            var cargoDatum = cargoData.cargos;

            var manySegmentList = [];

            for (var cIndex in cCabins) {
                var tabData = cCabins[cIndex].tableData;
                var manySegment = {};

                var cabinParams = [];
                //把航段名称对应的航段组装传回后台,用于电子舱单显示
                manySegment.segmentName = this.getSegmentName(cIndex);

                for (var cKey in tabData) {
                    //第一个元素是占位使用,并不是实际数据
                    if (cKey == 0) {
                        continue;
                    }
                    var rowObj = {};
                    var colData = tabData[cKey].aircraftCabin;
                    //type,passengerList和后台的manifest 对应使用Spring 自动装载绑定值
                    rowObj.type = tabData[cKey].cabinType;
                    rowObj.passengerList = [];
                    for (var colKey in colData) {
                        //在页面中第一个元素为占位,在传数据时候不需回传到后台中
                        if (colKey == 0) {
                            continue;
                        }
                        var passenger = {};
                        //把客舱名字和值绑定成为一个对象如(OA:123)  这种数据格式
                        var colDatum = colData[colKey];

                        passenger.name = colDatum.name;
                        passenger.num = colDatum.value
                        // rowObj[colDatum.name] = colDatum.value;
                        rowObj.passengerList.push(passenger);
                    }
                    // //为每条数据添加航段编号,便于区别数据
                    // rowObj.segment = cIndex;
                    cabinParams.push(rowObj);
                }

                var cargoParams = [];
                var tabData = cargoDatum[cIndex].tableData;
                for (var cKey in tabData) {
                    //第一个元素是占位使用,并不是实际数据
                    if (cKey == 0) {
                        continue;
                    }
                    var rowObj = {};
                    var colData = tabData[cKey].aircraftCabin;
                    //type,cargoList,fullScale和后台的manifest 对应使用Spring 自动装载绑定值
                    rowObj.type = tabData[cKey].holtType;
                    rowObj.fullScale = tabData[cKey].fullScale;
                    rowObj.cargoHoldList = [];
                    for (var colKey in colData) {
                        //在页面中第一个元素为占位,在传数据时候不需回传到后台中
                        if (colKey == 0) {
                            continue;
                        }
                        var cargo = {};
                        //把客舱名字和值绑定成为一个对象如(OA:123)  这种数据格式
                        var colDatum = colData[colKey];

                        cargo.name = colDatum.name;
                        cargo.weight = colDatum.value
                        rowObj.cargoHoldList.push(cargo);
                    }
                    cargoParams.push(rowObj);
                }
                manySegment.cabinList = cabinParams;
                manySegment.cargoList = cargoParams;
                //组装多个航段的信息
                manySegmentList.push(manySegment);
            }
            //组装传输到后台的数据
            var data = {
                flightId: this.infoForm.flightInfo.id,//航班号主键ID
                baseInfoBean: this.infoForm,
                manySegmentList: manySegmentList,
            };
            $.ajax({
                url: "msg/message/createManifest",
                data: JSON.stringify(data),
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    cfg_manifest.showFtl = result.data.message;
                    cfg_manifest.showtable(result.data);
                    czy.msg.success(result.msg)
                },
                error: function (error) {
                    var text = error.responseJSON.msg;
                    if (text && text.length > 100) {
                        text = text.substring(0, 100);
                    }
                    czy.msg.error(text);
                }
            });
        },
        getSegmentName: function (index) {
            var checkNumber = this.radio2;
            var segmentXX = "";
            if (checkNumber == 1) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentB;
                }
            }
            if (checkNumber == 2) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentB;
                }
                if (index == 1) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentC;
                }
            }
            if (checkNumber == 3) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentC;
                }
                if (index == 1) {
                    segmentXX = this.infoForm.segmentB + "-" + this.infoForm.segmentC;
                }
            }
            if (checkNumber == 4) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentB;
                }
                if (index == 1) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentC;
                }
                if (index == 2) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentD;
                }

            }

            if (checkNumber == 5) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentC;
                }
                if (index == 1) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentD;
                }
                if (index == 2) {
                    segmentXX = this.infoForm.segmentB + "-" + this.infoForm.segmentC;
                }
                if (index == 3) {
                    segmentXX = this.infoForm.segmentB + "-" + this.infoForm.segmentD;
                }

            }
            if (checkNumber == 6) {
                if (index == 0) {
                    segmentXX = this.infoForm.segmentA + "-" + this.infoForm.segmentD;
                }
                if (index == 1) {
                    segmentXX = this.infoForm.segmentB + "-" + this.infoForm.segmentD;
                }
                if (index == 2) {
                    segmentXX = this.infoForm.segmentC + "-" + this.infoForm.segmentD;
                }
            }
            return segmentXX;

        },
        showtable: function (result) {

            var symbolSize = 5;
            var data = [[40, 35], [34, 53], [36, 63], [35, 72], [40, 74], [65, 77], [82, 77.5], [90, 73], [82, 58], [66, 47], [63, 35]];
            var data1 = [[40, 35], [34, 53], [36, 63], [36, 64.5], [85.5, 64.5], [82, 58], [66, 47], [63, 35]];
            var data2 = [[42, 35], [38.8, 43], [39, 53.8], [38, 56], [39, 61], [88, 61], [71, 35]];

            var data4 = [[result.towi, result.tow]];//[[50, 57]];
            var data5 = [[result.ldwi, result.ldw]];
            var data6 = [[result.zfwi, result.zfw]];

            var option = {
                backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
                    offset: 0,
                    color: '#f7f8fa'
                }, {
                    offset: 1,
                    color: '#cdd0d5'
                }]),
                title: {
                    text: 'LOAD and TRIM SHEET'
                },
                tooltip: {
                    formatter: function (params) {
                        var data = params.data || [0, 0];
                        return params.seriesName + " , " + data[0].toFixed(2) + ', ' + data[1].toFixed(2);
                    }
                },
                legend: {
                    right: true,
                    align: "left",
                    data: ["MTOW", "MLW", "MZFW"]
                },
                xAxis: [{
                    min: 20,
                    max: 100,
                    interval: 10,
                    name: "Index",
                    nameLocation: 'middle',
                    nameGap: 20,
                    type: 'value',
                    splitLine: {show: false},
                    axisLine: {onZero: false}
                }, {
                    min: 15,
                    max: 42,
                    interval: 1,
                    name: "Aircraft CG (%MAC)",
                    nameLocation: 'middle',
                    nameGap: 25,
                    type: 'value',
                    splitLine: {show: false},
                    axisLine: {onZero: false}
                }],
                yAxis: {
                    min: 35,
                    max: 80,
                    name: "Aircraft Weight(kg x 1000)",
                    nameLocation: 'middle',
                    nameGap: 25,
                    splitNumber: 5,
                    interval: 5,
                    type: 'value',
                    axisLine: {onZero: false}
                },
                series: [
                    {
                        id: 'a',
                        name: "MTOW",
                        type: 'line',
                        smooth: false,
                        symbolSize: symbolSize,
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 68)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 131)'
                                }])
                            }
                        },
                        data: data
                    },
                    {
                        id: 'b',
                        name: "MLW",
                        type: 'line',
                        smooth: false,
                        symbolSize: symbolSize,
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 255)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 200)'
                                }])
                            }
                        },
                        data: data1
                    }, {
                        id: 'c',
                        name: "MZFW",
                        type: 'line',
                        smooth: false,
                        symbolSize: symbolSize,
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(34,139,34)'
                                }])
                            }
                        },
                        data: data2
                    }, {
                        id: 'd',
                        name: "MTOW 重心点",
                        type: 'scatter',
                        smooth: false,
                        symbolSize: 10,
                        areaStyle: {
                            normal: {
                                color: {
                                    colorStops: [{
                                        offset: 0, color: 'red' // 0% 处的颜色
                                    }, {
                                        offset: 1, color: 'blue' // 100% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                }
                            }
                        },
                        data: data4//[[result.towi, result.tow]]
                    }, {
                        id: 'e',
                        type: 'scatter',
                        name: "MLW 重心点",
                        smooth: false,
                        symbolSize: 10,
                        areaStyle: {
                            normal: {
                                color: {
                                    colorStops: [{
                                        offset: 0, color: 'red' // 0% 处的颜色
                                    }, {
                                        offset: 1, color: 'blue' // 100% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                }
                            }
                        },
                        data: data5//[[result.ldw, result.ldwi]]
                    }, {
                        id: 'f',
                        name: 'MZFW 重心点',
                        type: 'scatter',
                        smooth: false,
                        symbolSize: 10,
                        areaStyle: {
                            normal: {
                                color: {
                                    colorStops: [{
                                        offset: 0, color: 'red' // 0% 处的颜色
                                    }, {
                                        offset: 1, color: 'blue' // 100% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                }
                            }
                        },
                        data: data6//[[result.zfwi, result.zfw]]
                    }
                ]
            };
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('loadAndTrimSheet'));
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //重设图表尺寸
            myChart.resize({
                width: 500,
                height: 450,
                silent: true
            });


            var mtowMap = validate(data4, data);
            var mlwMap = validate(data5, data1);
            var mzfwMap = validate(data6, data2);

            var html = " MTOW " + (mtowMap == true ? "在" : "不在") + "包线范围内;"
            html += " MLW " + (mlwMap == true ? "在" : "不在") + "包线范围内;"
            html += " MZFW " + (mzfwMap == true ? "在" : "不在") + "包线范围内;"
            this.checkResultData = html;
            function validate(point, data) {
                var array = new Array();
                for (var index in data) {
                    var datum = data[index];
                    var datumObj = {x: datum[0], y: datum[1]};
                    array.push(datumObj);
                }

                var gPoint = {x: point[0][0], y: point[0][1]};
                var casting = cfg_manifest.rayCasting(gPoint, array);
                var windingNumber = cfg_manifest.windingNumber(gPoint, array);
                if (casting === windingNumber && casting === "in") {
                    return true;
                }
                return false;
            }


        },
        /**
         * @description 回转数法判断点是否在多边形内部
         * @param {Object} p 待判断的点，格式：{ x: X坐标, y: Y坐标 }
         * @param {Array} poly 多边形顶点，数组成员的格式同 p
         * @return {String} 点 p 和多边形 poly 的几何关系
         */
        windingNumber: function (p, poly) {
            var px = p.x,
                py = p.y,
                sum = 0
            for (var i = 0, l = poly.length, j = l - 1; i < l; j = i, i++) {
                var sx = poly[i].x,
                    sy = poly[i].y,
                    tx = poly[j].x,
                    ty = poly[j].y
                // 点与多边形顶点重合或在多边形的边上
                if ((sx - px) * (px - tx) >= 0 && (sy - py) * (py - ty) >= 0 && (px - sx) * (ty - sy) === (py - sy) * (tx - sx)) {
                    return 'on'
                }
                // 点与相邻顶点连线的夹角
                var angle = Math.atan2(sy - py, sx - px) - Math.atan2(ty - py, tx - px)
                // 确保夹角不超出取值范围（-π 到 π）
                if (angle >= Math.PI) {
                    angle = angle - Math.PI * 2
                } else if (angle <= -Math.PI) {
                    angle = angle + Math.PI * 2
                }
                sum += angle
            }
            // 计算回转数并判断点和多边形的几何关系
            return Math.round(sum / Math.PI) === 0 ? 'out' : 'in'
        },
        /**
         * @description 射线法判断点是否在多边形内部
         * @param {Object} p 待判断的点，格式：{ x: X坐标, y: Y坐标 }
         * @param {Array} poly 多边形顶点，数组成员的格式同 p
         * @return {String} 点 p 和多边形 poly 的几何关系
         */
        rayCasting: function (p, poly) {
            var px = p.x,
                py = p.y,
                flag = false
            for (var i = 0, l = poly.length, j = l - 1; i < l; j = i, i++) {
                var sx = poly[i].x,
                    sy = poly[i].y,
                    tx = poly[j].x,
                    ty = poly[j].y
                // 点与多边形顶点重合
                if ((sx === px && sy === py) || (tx === px && ty === py)) {
                    return 'on'
                }
                // 判断线段两端点是否在射线两侧
                if ((sy < py && ty >= py) || (sy >= py && ty < py)) {
                    // 线段上与射线 Y 坐标相同的点的 X 坐标
                    var x = sx + (py - sy) * (tx - sx) / (ty - sy)

                    // 点在多边形的边上
                    if (x === px) {
                        return 'on'
                    }
                    // 射线穿过多边形的边界
                    if (x > px) {
                        flag = !flag
                    }
                }
            }
            // 射线穿过多边形边界的次数为奇数时点在多边形内
            return flag ? 'in' : 'out'
        }
    },
    mounted: function () {
        //
        var flights = this.searchByFlightNo();
        var data = [];
        for (var key in  flights) {
            var obj = {};
            var flight = flights[key];
            //value 此属性是必须的,在构建远程input 请求显示列表
            obj.value = flight.flightNo;
            for (var index in flight) {
                obj[index] = flight[index];
            }
            data.push(obj);
        }
        this.restaurants = data;
    }
})

