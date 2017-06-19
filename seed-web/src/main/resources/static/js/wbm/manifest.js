var defaults = {
    cabinsForm: {
        passengerTypes: [{key: ""}, {key: "Adult"}, {key: "Children"}, {key: "Infant"}],
        passengerCabins: [{}],
        segments: [{key: new Date()}]
    },
    cargoHoldForm: {
        cargoTypes: [{key: ""}, {key: "Cargo"}, {key: "Bag"}, {key: "Mail"}],
        cargos: [{key: Date.now(), tableData: [{aircraftCabinId: -1, key: Date.now()}]}],//[{key: Date.now(), name: "", inputData: [{key: Date.now(), name: ''}]}],
        segments: [{key: new Date()}],
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
    configuration: ["baseIndex", "baseWeight", "limitedMtow", "limitedMldw", "limitedMzfw", "takeOffFuel", "tripFuel"]
}

var cfg_manifest = new Vue({
    el: "#manifest",
    data: function () {
        return {
            tableData: this.loadData(), // 航班类型列表页数据加载
            disabled: true,//航班号不确定不能选择航段信息
            showcargoAndCabin: false,//显示货舱和客舱card
            showSegmentTag: false,//是否在客舱货舱上显示航段信息标签
            showSegmentA_B: false,//显示A B 航段输入框
            showSegmentA_B_C: false, //显示A B C 航段输入框
            showSegmentA_B_C_D: false, //显示A B C D 航段输入框
            currentDate: new Date(),
            infoForm: {
                crews: defaults.crews,
                galleyGoods: defaults.galleyGoods,
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
        loadData: function () {
            var data = {};//组装的data数据
            $.ajax({
                url: "/cfg/flightTypeConfig/list",
                data: {},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    data = result;
                }
            });
            return data;
        },
        submitPcForm: function (passengerCabinForm) {

        },
        //获取货舱配置
        loadCargoData: function () {
            var data = {};
            $.ajax({
                url: "/cfg/flightTypeConfig/list",
                data: {},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    data = result;
                }
            });
            return data;
        },

        radioChange: function (value) {
            //清空客舱和货舱航段
            this.cabinsForm.segments = [];
            this.cargoHoldForm.segments = [];
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
                this.cabinsForm.segments.push({key: new Date()});
                this.cargoHoldForm.segments.push({key: new Date()});
            }

            this.infoForm.segmentInput = [];
            //在动态生成的客舱和货舱card 显示卡上显示航段信息
            //if (inptLength != 0) {
            this.showSegmentTag = true;
            // }

            for (var i = 0; i < inptLength; i++) {
                this.infoForm.segmentInput.push(defaults.segmentInput[i]);
            }


            console.log(value);
        },
        searchByFlightNo: function () {
            var data = [];
            $.ajax({
                url: "/cfg/flightInfo/searchFlightInfo",
                data: {},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    data = result.data
                }
            });
            return data;
        },
        querySearchAsync: function (queryString, callBack) {
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;
            callBack(results);
        },
        createStateFilter: function (queryString) {
            return function (state) {
                return (state.value.indexOf(queryString) === 0)
            }
        },
        handleSelect: function (item) {
            this.radio2 = 1;
            //回显数据
            for (var val in defaults.configuration) {
                var configuration = defaults.configuration[val];
                var value = item.flightConfig[configuration];
                this.infoForm[configuration] = value;
            }
            //
            this.infoForm.segment = item.segment;

            var crews = item.flightTypeConfig.crewList;
            var galleyGoods = item.flightTypeConfig.galleyGoodsList;
            var holdList = item.flightTypeConfig.cargoHoldList;
            var cabinsList = item.flightTypeConfig.passengerCabinList;

            this.infoForm.crews = defaults.crews;
            this.infoForm.galleyGoods = defaults.galleyGoods;
            //乘务信息组装
            for (var key in crews) {
                var crew = crews[key];
                var defCrews = this.infoForm.crews;
                for (var index in defCrews) {
                    if (crew.positionCode == defCrews[index].positionCode) {
                        this.infoForm.crews[key].standardNum = crew.standardNum;
                    }
                }
            }
            //机供品组装
            for (var key in galleyGoods) {
                var galleyGood = galleyGoods[key];
                var defGalleyGoods = this.infoForm.galleyGoods;
                for (var index in defGalleyGoods) {
                    if (galleyGood.positionCode == defGalleyGoods[index].positionCode) {
                        this.infoForm.galleyGoods[key].standardWeight = galleyGood.standardWeight;
                    }
                }
            }

            this.cargoHoldForm.cargos = [{key: Date.now(), name: "", inputData: [{key: Date.now(), name: ''}]}];


            //组装货舱信息
            for (var key in  holdList) {
                var holdObj = {};
                var hold = holdList[key];
                holdObj.name = hold.name;
                holdObj.inputData = [{key: Date.now(), name: ''}];
                this.cargoHoldForm.cargos.push(holdObj);
            }

            this.cabinsForm.passengerCabins = [{name: ""}];
            for (var key in cabinsList) {
                var cabinObj = {};
                var cabin = cabinsList[key];
                cabinObj.name = cabin.name;
                this.cabinsForm.passengerCabins.push(cabinObj);
            }
            this.cabinsForm.segments = [{}];
            this.disabled = false;
            this.showcargoAndCabin = true;
        },

        submitManifest: function () {
            var data = cfg_manifest.cargoHoldForm;
            console.log(data);
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

