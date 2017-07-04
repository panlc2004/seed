/**
 * 注: indexConfig ==> ic
 *     passengerCabin ==> pc
 *     galleyGoods ==> gg
 *     flightTypeConfig ==> ftc
 */
var defaults = {
    ftcForm: {//航班类型配置
        id: null,
        flightType: '',
        seatNum: '',
        harm: '',
        c: '',
        k: '',
        lemac: '',
        datum: '',
        mac: ''
    },
    fcForm: {//航班配置flightConfig
        id: '',
        num: '',
        msn: '',
        baseWeight: '',
        baseIndex: '',
        takeOffFuel: '',
        tripFuel: '',
        limitedMtow: '',
        limitedMldw: '',
        limitedMzfw: ''
    },
    ggForm: {//机供品配置
        galleyGoods: [
            {positionCode: "FWD"},// 前舱
            {positionCode: "AFT"}// 后舱
        ],
        configuration: {"FWD": "前舱", "AFT": "后舱"}
    },
    passengerForm: {
        passengers: [
            {passengerTypeCode: "ADULT"},// 成人
            {passengerTypeCode: "CHILD"},// 小孩
            {passengerTypeCode: "INFANT"}// 婴儿
        ],
        configuration: {"ADULT": "成人", "CHILD": "小孩", "INFANT": "婴儿"}
    },
    crewForm: {
        crews: [
            {positionCode: "COCKPIT"},// 驾驶舱
            {positionCode: "FWD"},// 前舱
            {positionCode: "MID"},// 中舱
            {positionCode: "AFT"}// 后舱
        ],
        configuration: {"COCKPIT": "驾驶舱", "FWD": "前舱", "MID": "中舱", "AFT": "后舱"}
    },
    flightInfoForm: {//航班总体参数配置
        flightNo: '',
        segment: ''
    },
    offsetForm: {//偏差设置
        id: '',
        type: '',
        minMileage: '',
        maxMileage: '',
        num: ''
    },
    passengerCabinForm: {
        passengerCabins: [{key: Date.now(), icTableData: [{key: Date.now()}]}]
    },
    cargoHoldForm: {
        cargoHolds: [{key: Date.now(), icTableData: [{key: Date.now()}]}]
    },
    fuelIndexConfigForm: {
        //做数据值初始化aircraftCabinId 舱位关联ID 在燃油参数配置中没有舱位关联设置默认值为-1
        //types:3 表示燃油参数指数设置
        fuels: [{key: Date.now(), icTableData: [{aircraftCabinId: -1, types: 3, key: Date.now()}]}]
    },
}


var cfg_index = new Vue({
    el: "#dt-grid",
    data: function () {
        return {
            tableData: this.loadData(), // 航班类型列表页数据加载
            showTable: true,//是否显示航班类型列表
            showForm: false,//是否显示航班类型配置页
            showInfoForm: false,//是否显示架次配置页
            fcForm: defaults.fcForm,
            flightInfoForm: defaults.flightInfoForm,
            offsetForm: defaults.offsetForm,
            ggForm: defaults.ggForm,
            ftcForm: defaults.ftcForm,
            passengerCabinForm: defaults.passengerCabinForm,
            cargoHoldForm: defaults.cargoHoldForm,
            fuelIndexConfigForm: defaults.fuelIndexConfigForm,
            passengerForm: defaults.passengerForm,
            crewForm: defaults.crewForm,
            //航班类型参数配置验证规则
            ftcRules: {
                flightType: [
                    {required: true, message: '请输入机型', trigger: 'blur'},
                    {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
                ],
                seatNum: [
                    {required: true, message: '请输入最大座位数'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '最大座位数必须为数字值', trigger: 'blur'}
                ],
                harm: [
                    {required: true, message: '请输入基准力臂'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '基准力臂必须为数字值', trigger: 'blur'}
                ],
                c: [
                    {required: true, message: '请输入c'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: 'c必须为数字值', trigger: 'blur'}
                ],
                k: [
                    {required: true, message: '请输入k'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: 'k必须为数字值', trigger: 'blur'}
                ],
                lemac: [
                    {required: true, message: '请输入lemac'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '最大座位数必须为数字值', trigger: 'blur'}
                ],
                datum: [
                    {required: true, message: '请输入datum'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: 'datum必须为数字值', trigger: 'blur'}
                ],
                mac: [
                    {required: true, message: '请输入mac'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: 'mac必须为数字值', trigger: 'blur'}
                ]
            },
            //航班类型参数配置验证规则
            fcRules: {
                num: [
                    {required: true, message: '请输出厂序号', trigger: 'blur'},
                    {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
                ],
                msn: [
                    {required: true, message: '请输入机号'}
                ],
                baseWeight: [
                    {required: true, message: '请输入基本重量'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                baseIndex: [
                    {required: true, message: '请输入基本指数'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                takeOffFuel: [
                    {required: true, message: '请输入起飞油量'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                tripFuel: [
                    {required: true, message: '请输入航班用油'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                limitedMtow: [
                    {required: true, message: '请输入起飞重量'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                limitedMldw: [
                    {required: true, message: '请输入落地重量'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ],
                limitedMzfw: [
                    {required: true, message: '请输入无油重量'},
                    // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'},
                    {type: 'number', message: '必须为数字值', trigger: 'blur'}
                ]

            }
        }
    },
    methods: {
        loadData: function () {
            var data = {};//组装的data数据
            $.ajax({
                url: "cfg/flightTypeConfig/list",
                data: {},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    data = result;
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
        getSelectRows: function (selection, row) {
            var length = selection.length;
            if (length && length == 1) {
                this.ftcForm = row;
                return;
            } else {
                czy.msg.warn("只能选取一条数据!")
                return;
            }
        },
        //类型配置编辑按钮
        typeConfEdit: function () {
            if (this.ftcForm.id != null) {
                this.aboutTypeConfEcho();
                this.showTable = false;
                this.showForm = true;
                return;
            }
            czy.msg.warn("请选择一条数航班类型配置参数!")
        },

        typeConfAdd: function () {

            this.ftcForm = {};
            this.showTable = false;
            this.showForm = true;
        },
        //架次参数配置编辑按钮
        infoConfEdit: function () {
            if (this.ftcForm.id != null) {
                this.aboutInfoConfEcho();
                this.showTable = false;
                this.showInfoForm = true;
                return;
            }
            czy.msg.warn("请选择一条数航班类型配置参数!")
        },
        //架次参数配置新增按钮
        infoConfAdd: function () {
            if (this.ftcForm.id != null) {
                this.showTable = false;
                this.showInfoForm = true;
                return;
            }
            czy.msg.warn("请选择一条数航班类型配置参数!")
        },
        //航班类型参数配置提交function
        submitFtcForm: function (formName) {
            this.$refs[formName].validate(function (result) {
                if (result) {
                    var params = JSON.stringify(cfg_index.ftcForm);
                    $.ajax({
                        url: "cfg/flightTypeConfig/save",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        async: false,//需要添加这个参数使用同步功能
                        success: function (result) {
                            cfg_index.ftcForm.id = result.data.id
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
                }
            });
        },
        //客舱添加输入项
        pcAddClick: function () {
            this.passengerCabinForm.passengerCabins.push({key: Date.now(), icTableData: [{key: Date.now()}]});
        },
        //客舱删除输入项
        pcDelClick: function (passengerCabin) {
            var indexOf = this.passengerCabinForm.passengerCabins.indexOf(passengerCabin);
            if (indexOf !== -1 && indexOf !== 0) {
                this.passengerCabinForm.passengerCabins.splice(indexOf, 1)
            } else {
                czy.msg.warn("不能选择第一条数据!")
            }
        },
        //货舱添加输入项
        chAddClick: function () {
            this.cargoHoldForm.cargoHolds.push({key: Date.now(), icTableData: [{key: Date.now()}]});
        },
        //货舱删除输入项
        chDelClick: function (cargoHold) {
            var indexOf = this.cargoHoldForm.cargoHolds.indexOf(cargoHold);
            if (indexOf !== -1 && indexOf !== 0) {
                /**splice 方法说明
                 * index    必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
                 * howmany    必需。要删除的项目数量。如果设置为 0，则不会删除项目。
                 * item1, ..., itemX    可选。向数组添加的新项目。
                 */
                this.cargoHoldForm.cargoHolds.splice(indexOf, 1)
            } else {
                czy.msg.warn("不能选择第一条数据!")
            }
        },
        //指数配置增加输入项
        icpcAddClick: function (index) {
            this.passengerCabinForm.passengerCabins[index].icTableData.push({types: 1, key: Date.now()});
        },
        icpcDelClick: function (index, row) {
            if (index == 0) {
                czy.msg.warn("不能删除第一条数据!");
                return;
            }
            row.splice(index, 1);
        },
        //燃油指数配置增加输入项
        fuelAddClick: function (index) {
            this.fuelIndexConfigForm.fuels[index].icTableData.push({aircraftCabinId: -1, types: 3, key: Date.now()});
        },
        fuelIcDelClick: function (index, row) {
            if (index == 0) {
                czy.msg.warn("不能删除第一条数据!");
                return;
            }
            row.splice(index, 1);
        },
        //货舱指数配置增加输入项
        icchAddClick: function (index) {
            this.cargoHoldForm.cargoHolds[index].icTableData.push({types: 2, key: Date.now()});
        },

        icchDelClick: function (index, row) {
            if (index == 0) {
                czy.msg.warn("不能删除第一条数据!");
                return;
            }
            row.splice(index, 1);
        },

        submitPcForm: function (passengerCabinForm) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }
            // this.$refs["ftcSubmit"].click();
            //提交客舱信息参数
            this.$refs[passengerCabinForm].validate(function (result) {
                if (result) {
                    var cabins = cfg_index.passengerCabinForm.passengerCabins;
                    var data = [];
                    for (var index in cabins) {
                        var singleData = {};//此对象包含客舱信息和指数配置信息列表对象
                        var indexConfigList = {};//指数配置列表对象
                        var cabin = {};//客舱信息配置对象
                        var indexOfObj = cabins[index];
                        for (var key in indexOfObj) {
                            if (key == "icTableData") {
                                indexConfigList = indexOfObj[key];
                                for (var indes in indexConfigList) {
                                    indexConfigList[indes].flightTypeConfigId = cfg_index.ftcForm.id;
                                }
                            } else {
                                cabin[key] = indexOfObj[key];
                            }
                        }
                        cabin.flightTypeConfigId = cfg_index.ftcForm.id;
                        singleData.indexConfigList = indexConfigList;
                        singleData.cabin = cabin;
                        data.push(singleData);
                    }
                    var params = JSON.stringify(data);
                    $.ajax({
                        url: "cfg/passengerCabin/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
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
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        submitChForm: function (cargoHold) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }
            // this.$refs["ftcSubmit"].click();
            //提交客舱信息参数
            this.$refs[cargoHold].validate(function (result) {
                if (result) {
                    var cargoHolds = cfg_index.cargoHoldForm.cargoHolds;
                    var data = [];
                    for (var index in cargoHolds) {
                        var singleData = {};//此对象包含客舱信息和指数配置信息列表对象
                        var indexConfigList = {};//指数配置列表对象
                        var cargoHold = {};//客舱信息配置对象
                        var indexOfObj = cargoHolds[index];
                        for (var key in indexOfObj) {
                            if (key == "icTableData") {//为指数配置项添加 flightTypeConfigId
                                indexConfigList = indexOfObj[key];
                                for (var indes in indexConfigList) {
                                    indexConfigList[indes].flightTypeConfigId = cfg_index.ftcForm.id;
                                }
                            } else {
                                cargoHold[key] = indexOfObj[key];
                            }
                        }
                        cargoHold.flightTypeConfigId = cfg_index.ftcForm.id;
                        singleData.indexConfigList = indexConfigList;
                        singleData.cargoHold = cargoHold;
                        data.push(singleData);
                    }
                    var params = JSON.stringify(data);
                    $.ajax({
                        url: "cfg/cargoHold/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
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
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        submitFuelForm: function (fuelIndexConfigForm) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }
            //提交客舱信息参数
            this.$refs[fuelIndexConfigForm].validate(function (result) {
                if (result) {
                    var fuels = cfg_index.fuelIndexConfigForm.fuels;
                    var fuelsIndexConfig;
                    //为每项数据添加关联的航班类型ID
                    for (var index in fuels) {
                        var icTableData = fuels[index].icTableData;
                        for (var key in icTableData) {
                            icTableData[key].flightTypeConfigId = cfg_index.ftcForm.id;
                        }
                        fuelsIndexConfig = icTableData;
                    }
                    var params = JSON.stringify(fuelsIndexConfig);
                    $.ajax({
                        url: "cfg/indexConfig/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        //乘客信息配置提交
        submitPassengerForm: function (passenger) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }

            if (!cfg_index.ftcForm.id || "" == cfg_index.ftcForm.id) {
                //在提交客舱之前必须先提交航班类型参数
                czy.msg.error("flightTypeConfigId 为空不能提交");
                return;
            }
            //提交客舱信息参数
            this.$refs[passenger].validate(function (result) {
                if (result) {
                    var passengers = cfg_index.passengerForm.passengers;
                    for (var key in passengers) {
                        var passenger = passengers[key];
                        passenger.flightTypeConfigId = cfg_index.ftcForm.id;
                    }
                    var params = JSON.stringify(passengers);
                    $.ajax({
                        url: "cfg/passenger/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        //乘务信息配置提交
        submitCrewForm: function (crew) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }

            if (!cfg_index.ftcForm.id || "" == cfg_index.ftcForm.id) {
                //在提交客舱之前必须先提交航班类型参数
                czy.msg.error("flightTypeConfigId 为空不能提交");
                return;
            }
            //提交客舱信息参数
            this.$refs[crew].validate(function (result) {
                if (result) {
                    var crews = cfg_index.crewForm.crews;
                    for (var key in crews) {
                        var crew = crews[key];
                        crew.flightTypeConfigId = cfg_index.ftcForm.id;
                    }
                    var params = JSON.stringify(crews);
                    $.ajax({
                        url: "cfg/crew/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        //机供品信息配置提交
        submitGgForm: function (galleyGoods) {
            if (!cfg_index.ftcForm.id || "" == cfg_index.ftcForm.id) {
                //在提交客舱之前必须先提交航班类型参数
                czy.msg.error("flightTypeConfigId 为空不能提交");
                return;
            }
            //提交客舱信息参数
            this.$refs[galleyGoods].validate(function (result) {
                if (result) {
                    var galleyGoods = cfg_index.ggForm.galleyGoods;
                    for (var key in galleyGoods) {
                        var galleyGood = galleyGoods[key];
                        galleyGood.flightTypeConfigId = cfg_index.ftcForm.id;
                    }
                    var params = JSON.stringify(galleyGoods);
                    $.ajax({
                        url: "cfg/galleyGoods/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        //偏差配置提交
        submitOffsetForm: function (offsetForm) {
            if (!cfg_index.ftcForm.id || "" == cfg_index.ftcForm.id) {
                //在提交客舱之前必须先提交航班类型参数
                czy.msg.error("flightTypeConfigId 为空不能提交");
                return;
            }
            //提交客舱信息参数
            this.$refs[offsetForm].validate(function (result) {
                if (result) {
                    var offset = cfg_index.offsetForm;
                    offset.flightTypeConfigId = cfg_index.ftcForm.id;
                    var params = JSON.stringify(offset);
                    $.ajax({
                        url: "cfg/offset/save",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        // 航班总体参数配置提交
        submitFlightInfoForm: function (flightInfoForm) {
            var flightConfigId = cfg_index.fcForm.id;
            if (!flightConfigId || "" == flightConfigId) {
                //在提交航班总体参数之前必须先提交航班类型参数
                this.submitFcForm("fcForm");
            }

            if (!cfg_index.fcForm.id || "" == cfg_index.fcForm.id) {
                //在提交航班总体参数之前必须先提交航班类型参数
                czy.msg.error("fcForm 为空不能提交");
                return;
            }
            //提交客舱信息参数
            this.$refs[flightInfoForm].validate(function (result) {
                if (result) {
                    var flightInfo = cfg_index.flightInfoForm;
                    flightInfo.flightConfigId = cfg_index.fcForm.id;
                    var params = JSON.stringify(flightInfo);
                    $.ajax({
                        url: "cfg/flightInfo/save",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!");
                }
            });
        },
        //航班架次配置提交
        submitFcForm: function (fcForm) {

            //提交客舱信息参数
            this.$refs[fcForm].validate(function (result) {
                var flightConfigId = cfg_index.ftcForm.id;
                if (!flightConfigId || "" == flightConfigId) {
                    czy.msg.error("航班类型配置ID");
                    return;
                }

                if (result) {
                    var flightConfig = cfg_index.fcForm;
                    flightConfig.flightTypeConfigId = cfg_index.ftcForm.id;
                    var params = JSON.stringify(flightConfig);
                    $.ajax({
                        url: "cfg/flightCfg/save",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            cfg_index.fcForm.id = result.data.id;
                            czy.msg.success(result.msg);
                        },
                        error: function (error) {
                            var text = error.responseJSON.msg;
                            if (text && text.length > 100) {
                                text = text.substring(0, 100);
                            }
                            czy.msg.error(text);
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        },
        //回显数据---------------------------------------------------------------//
        //客舱数据和指数参数配置数据回显
        aboutTypeConfEcho: function () {
            $.ajax({
                url: "cfg/flightTypeConfig/queryFlightTypeList",
                data: {flightTypeConfigId: cfg_index.ftcForm.id},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    //客舱和指数回显组装数据data对象
                    var cabins = [];
                    //货舱和指数回显组装数据data对象
                    var cargoHolds = [];
                    //燃油和指数回显组装数据data对象
                    var fuels = [];
                    //乘务信息数据回显data对象
                    var crews = [];
                    //乘客信息数据回显data对象
                    var passengers = [];
                    var pcList = result.data.pcList;
                    var chList = result.data.chList;
                    var fuelList = result.data.fuelList;
                    var crewList = result.data.crewList;
                    var passengerList = result.data.passengerList;

                    //客舱信息数据回显
                    for (var key in pcList) {
                        var obj = pcList[key];
                        var cabin = obj.cabin;
                        var indexConfigList = obj.indexConfigList;
                        var data = $.extend({}, cabin);
                        data.icTableData = indexConfigList;
                        cabins.push(data);
                    }

                    //货舱信息数据回显
                    for (var key in chList) {
                        var obj = chList[key];
                        var cargoHold = obj.cargoHold;
                        var indexConfigList = obj.indexConfigList;
                        var data = $.extend({}, cargoHold);
                        data.icTableData = indexConfigList;
                        cargoHolds.push(data);
                    }
                    //燃油指数配置
                    var fuel = {};
                    fuel.icTableData = fuelList == null || fuelList.length == 0 ? [{key: new Date()}] : fuelList;
                    fuels.push(fuel);

                    //乘务信息配置
                    for (var key in crewList) {
                        var crew = crewList[key];
                        crews.push(crew);
                    }

                    //乘客信息配置
                    for (var key in passengerList) {
                        var passenger = passengerList[key];
                        passengers.push(passenger);
                    }
                    /**
                     * 在数据回显赋值时,当数据库没有返回对应的值是,使用默认的配置项,防止页面输入项不正确
                     */
                    cfg_index.passengerCabinForm.passengerCabins = cabins.length != 0 ? cabins : defaults.passengerCabinForm.passengerCabins;
                    cfg_index.cargoHoldForm.cargoHolds = cargoHolds.length != 0 ? cargoHolds : defaults.cargoHoldForm.cargoHolds;
                    cfg_index.fuelIndexConfigForm.fuels = fuels.length != 0 ? fuels : defaults.fuelIndexConfigForm.fuels;
                    cfg_index.crewForm.crews = crews.length != 0 ? crews : defaults.crewForm.crews;
                    cfg_index.passengerForm.passengers = passengers.length != 0 ? passengers : defaults.passengerForm.passengers;
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
        //架次参数配置回显数据
        aboutInfoConfEcho: function () {
            $.ajax({
                url: "cfg/flightCfg/queryFlightConfList",
                data: {flightTypeConfigId: cfg_index.ftcForm.id},
                type: 'POST',
                async: false,//需要添加这个参数使用同步功能
                success: function (result) {
                    //机供品
                    var galleyGoodsList = result.data.galleyGoodsList;
                    //偏差设置
                    var offset = result.data.offsets;
                    // 航班架次配置数据对象
                    var flightConfig = result.data.flightConfig;
                    //航班总体参数数据对象
                    var flightInfo = result.data.flightInfo;
                    /**
                     * 在数据库中没有存在值时,需使用默认的配置项,防止页面输入项不正确
                     */
                    cfg_index.fcForm = flightConfig == null ? defaults.fcForm : flightConfig;
                    cfg_index.offsetForm = offset == null ? defaults.offsetForm : offset;
                    cfg_index.flightInfoForm = flightInfo == null ? defaults.flightInfoForm : flightInfo;
                    cfg_index.ggForm.galleyGoods = galleyGoodsList == null || galleyGoodsList.length == 0 ? defaults.ggForm.galleyGoods : galleyGoodsList;

                },
                error: function (error) {
                    var text = error.responseJSON.msg;
                    if (text && text.length > 100) {
                        text = text.substring(0, 100);
                    }
                    czy.msg.error(text);
                }
            });
        }
    }
})

