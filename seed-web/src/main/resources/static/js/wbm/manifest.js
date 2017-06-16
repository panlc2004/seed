var defaults = {
    cabinsForm: {
        passengerTypes: [{key: ""}, {key: "Adult"}, {key: "Children"}, {key: "Infant"}],
        passengerCabins: [{name: ""}, {name: "OA"}, {name: "OB"}, {name: "OC"}],
        segments: [{key: "123"}, {key: "123"}, {key: "123"}, {key: "123"}]
    },
    cargoHoldForm: {
        cargoTypes: [{key: ""}, {key: "Cargo"}, {key: "Bag"}, {key: "Mail"}],
        cargos: [{name: ""}, {name: "1"}, {name: "2"}, {name: "3"}, {name: "4"}],
        segments: [{key: "123"}, {key: "123"}, {key: "123"}, {key: "123"}]
    }
}

var cfg_manifest = new Vue({
    el: "#manifest",
    data: function () {
        return {
            tableData: this.loadData(), // 航班类型列表页数据加载
            showTable: true,//是否显示航班类型列表
            currentDate: new Date(),
            infoForm: {segment: '',baseIndex:'',baseWeight:'',limitedMtow:'',limitedMldw:'',limitedMzfw:''},
            radio2: 5,
            cabinsForm: defaults.cabinsForm,
            cargoHoldForm: defaults.cargoHoldForm,
            restaurants: [],
            timeout: null,
            flightNo: ''
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
        //获取客舱配置
        loadCabinData: function () {

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
                return (state.value.indexOf(queryString.toLowerCase()) === 0)
            }
        },
        handleSelect: function (item) {
            this.infoForm.segment = item.segment;
            this.infoForm.baseIndex=item.flightConfig.baseIndex;
            this.infoForm.baseWeight=item.flightConfig.baseWeight;
            this.infoForm.limitedMtow=item.flightConfig.limitedMtow;
            this.infoForm.limitedMldw=item.flightConfig.limitedMldw;
            this.infoForm.limitedMzfw=item.flightConfig.limitedMzfw;
            console.log(item);
        }

    },
    mounted: function () {
        var flights = this.searchByFlightNo();
        var data = [];
        for (var key in  flights) {
            var obj = {};
            var flight = flights[key];
            obj.value = flight.flightNo;
            for (var index in flight) {
                obj[index] = flight[index];
            }
            data.push(obj);
        }
        this.restaurants = data;
    }
})

