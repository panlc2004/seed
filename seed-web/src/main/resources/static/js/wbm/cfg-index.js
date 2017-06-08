var cfg_index = new Vue({
    el: "#dt-grid",
    data: function () {
        return {
            tableData: this.loadData(),
            icTableData: this.icDataForm(),
            ftcForm: {
                id: '',
                flightType: '',
                seatNum: '',
                harm: '',
                c: '',
                k: '',
                lemac: '',
                datum: '',
                mac: ''
            },
            passengerCabinForm: {
                passengerCabins: [{}],
            },
            cargoHoldForm: {
                cargoHolds: [{}],
            },
            passengerForm: {
                passengers: [
                    {passengerTypeCode: "AUDLT"},// 成人
                    {passengerTypeCode: "CHILD"},// 小孩
                    {passengerTypeCode: "INFANT"}// 婴儿
                ],
                configuration: {"AUDLT": "成人", "CHILD": "小孩", "INFANT": "婴儿"}
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

            }
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
        rowClick: function (row, event, column) {
            debugger;
            for (var key in row) {
                //alert(row.id);
            }
        },
        //航班类型参数配置提交function
        submitFtcForm: function (formName) {
            this.$refs[formName].validate(function (result) {
                if (result) {
                    var params = JSON.stringify(cfg_index.ftcForm);
                    $.ajax({
                        url: "/cfg/flightTypeConfig/add",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        async: false,//需要添加这个参数使用同步功能
                        success: function (result) {
                            cfg_index.ftcForm.id = result.data.id
                        }
                    });
                }
            });
        },
        //客舱添加输入项
        pcAddClick: function () {
            this.passengerCabinForm.passengerCabins.push({key: Date.now()});
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
            this.cargoHoldForm.cargoHolds.push({key: Date.now()});
        },
        //货舱删除输入项
        chDelClick: function (cargoHold) {
            var indexOf = this.cargoHoldForm.cargoHolds.indexOf(cargoHold);
            if (indexOf !== -1 && indexOf !== 0) {
                this.cargoHoldForm.cargoHolds.splice(indexOf, 1)
            } else {
                czy.msg.warn("不能选择第一条数据!")
            }
        },

        //指数配置增加输入项
        icAddClick: function () {
            this.icTableData.push(this.icDataForm());
        },
        icDelClick: function (index, row) {

            console.log(index, row);
            debugger
        },
        icDataForm: function () {
            return [{}];
        }
        ,
        submitPcForm: function (passengerCabinForm) {
            var flightTypeConfigId = cfg_index.ftcForm.id;
            debugger
            if (!flightTypeConfigId || "" == flightTypeConfigId) {
                //在提交客舱之前必须先提交航班类型参数
                this.submitFtcForm("ftcForm");
            }
            // this.$refs["ftcSubmit"].click();
            //提交客舱信息参数
            this.$refs[passengerCabinForm].validate(function (result) {
                if (result) {
                    var cabins = cfg_index.passengerCabinForm.passengerCabins;
                    for (var key in cabins) {
                        var cabin = cabins[key];
                        cabin.flightTypeConfigId = cfg_index.ftcForm.id;
                    }
                    debugger
                    var params = JSON.stringify(cabins);
                    $.ajax({
                        url: "/cfg/passengerCabin/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result == 200) {
                                czy.msg.success("保存成功!")
                            }
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
            debugger
            // this.$refs["ftcSubmit"].click();
            //提交客舱信息参数
            this.$refs[cargoHold].validate(function (result) {
                if (result) {
                    var cabins = cfg_index.cargoHoldForm.cargoHolds;
                    for (var key in cabins) {
                        var cabin = cabins[key];
                        cabin.flightTypeConfigId = cfg_index.ftcForm.id;
                    }
                    debugger
                    var params = JSON.stringify(cabins);
                    $.ajax({
                        url: "/cfg/cargoHold/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result == 200) {
                                czy.msg.success("保存成功!")
                            }
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
                        url: "/cfg/passenger/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result == 200) {
                                czy.msg.success("保存成功!")
                            }
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
                        url: "/cfg/crew/addList",
                        data: params,
                        type: 'POST',
                        contentType: 'application/json;charset=UTF-8',
                        success: function (result) {
                            if (result == 200) {
                                czy.msg.success("保存成功!")
                            }
                        }
                    });
                } else {
                    czy.msg.error("校验不通过!")
                }
            });
        }
    }
})
