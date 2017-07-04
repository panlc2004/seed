package com.czy.seed.mvc.wbm.msg.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.Manifest;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.*;
import com.czy.seed.mvc.wbm.config.service.FlightInfoService;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.msg.flight.SheetMessage;
import com.czy.seed.mvc.wbm.msg.service.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 生成舱单controller
 * Created by panlc on 2017-05-04.
 */
@RestController
@RequestMapping("/msg/message")
public class MessageController {

    @Resource
    private MessageService messageServiceImpl;

    @Resource
    private FlightInfoService flightInfoServiceImpl;
    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/createMsg")
    public ResultMsg createMsg(CabinConfig cabinConfig) {
        FlightInfo flightInfo = flightInfoServiceImpl.selectRelativeByPrimaryKey(cabinConfig.getFlightId());
        if (flightInfo != null) {
            FlightTypeConfig flightTypeConfig = configService.selectRelativeByPrimaryKey(flightInfo.getFlightConfig().getFlightTypeConfigId());
            mapCargoHold(cabinConfig, flightTypeConfig);
            mapPassengerBin(cabinConfig, flightTypeConfig);
            List<Crew> list = flightTypeConfig.getCrewList();
            for (Crew crew : list) {
                crew.setActuralNum(crew.getStandardNum());
            }
            List<GalleyGoods> goodsList = flightTypeConfig.getGalleyGoodsList();
            for (GalleyGoods goods : goodsList) {
                goods.setActualWeight(goods.getStandardWeight());
            }
            flightInfo.setFlightTypeConfig(flightTypeConfig);
        }
        String message = messageServiceImpl.createMessage(flightInfo, cabinConfig);
        ResultMsg msg = new ResultMsg();
        msg.setMsg(message);
        msg.setSuccess("true");
        return msg;
    }

    @RequestMapping("/createManifest")
    public Res createManifest(@RequestBody Manifest manifest) {
        if (manifest == null) {
            return Res.error("生成电子舱单错误,录入数据没有传输到后台!");
        }
        FlightInfo flightInfo = flightInfoServiceImpl.selectRelativeByPrimaryKey(manifest.getFlightId());
        if (flightInfo != null) {
            FlightTypeConfig flightTypeConfig = configService.selectRelativeByPrimaryKey(flightInfo.getFlightConfig().getFlightTypeConfigId());
            mapPassengerCaBin(manifest, flightTypeConfig);
            mapCargoHold(manifest, flightTypeConfig);
            //乘务信息
            List<Crew> list = flightTypeConfig.getCrewList();
            for (Crew crew : list) {
                crew.setActuralNum(crew.getStandardNum());
            }
            //机供品信息
            List<GalleyGoods> goodsList = flightTypeConfig.getGalleyGoodsList();
            for (GalleyGoods goods : goodsList) {
                goods.setActualWeight(goods.getStandardWeight());
            }
            flightInfo.setFlightTypeConfig(flightTypeConfig);
        }
        SheetMessage sheetMessage = new SheetMessage();
        String message = messageServiceImpl.createMessage(flightInfo, manifest,sheetMessage);
        sheetMessage.setMessage(message);
        return Res.ok(sheetMessage);
    }

    //客舱信息组装
    private void mapPassengerCaBin(Manifest manifest, FlightTypeConfig typeConfig) {
        List<PassengerCabin> cabinList = typeConfig.getPassengerCabinList();

        List<Manifest.ManySegment> manySegmentList = manifest.getManySegmentList();

        for (PassengerCabin passengerCabin : cabinList) {
            String name = passengerCabin.getName();
            Integer adultNum = 0;
            Integer childrenNum = 0;
            Integer infantNum = 0;
            for (Manifest.ManySegment manySegment : manySegmentList) {
                List<Manifest.Cabin> manySegmentCabinList = manySegment.getCabinList();
                for (Manifest.Cabin cabin : manySegmentCabinList) {
                    List<Manifest.Passenger> cabinPassengerList = cabin.getPassengerList();
                    String type = cabin.getType();
                    // "Adult"}, {key: "Children"}, {key: "Infant"}
                    for (Manifest.Passenger passenger : cabinPassengerList) {
                        String cabinName = passenger.getName();
                        //判断客舱名字是否和前台传回名字相同数据
                        if (name.equals(cabinName)) {
                            //获取前台传回的每个航段每个舱位对应的人数
                            Integer num = passenger.getNum();
                            if ("Adult".equals(type)) {
                                adultNum += num;
                                break;
                            }
                            if ("Children".equals(type)) {
                                childrenNum += num;
                                break;
                            }
                            if ("Infant".equals(type)) {
                                infantNum += num;
                                break;
                            }
                        }
                    }
                }
            }
            passengerCabin.setInfantNum(infantNum);
            passengerCabin.setChildNum(childrenNum);
            passengerCabin.setAdultNum(adultNum);
        }

    }

    //货仓信息组装

    private void mapCargoHold(Manifest manifest, FlightTypeConfig typeConfig) {
        List<CargoHold> holdList = typeConfig.getCargoHoldList();
        List<Manifest.ManySegment> manySegmentList = manifest.getManySegmentList();


        for (CargoHold hold : holdList) {
            String name = hold.getName();
            BigDecimal weight = BigDecimal.ZERO;

            BigDecimal cargoWeight = BigDecimal.ZERO;
            BigDecimal bagWeight = BigDecimal.ZERO;
            BigDecimal mailWeight = BigDecimal.ZERO;
            Integer cFs = 0; //货物数量
            Integer bFs = 0; //包裹数量
            Integer mFs = 0; //邮件数量
            for (Manifest.ManySegment manySegment : manySegmentList) {
                List<Manifest.Cargo> manySegmentCargoList = manySegment.getCargoList();

                for (Manifest.Cargo cargo : manySegmentCargoList) {
                    List<Manifest.CargoHold> cargoHoldList = cargo.getCargoHoldList();
                    String type = cargo.getType();
                    Integer fullScale = cargo.getFullScale();
                    if ("Cargo".equals(type)) {
                        cFs += fullScale;
                    }
                    if ("Bag".equals(type)) {
                        bFs += fullScale;
                    }
                    if ("Mail".equals(type)) {
                        mFs += fullScale;
                    }

                    //"Cargo","Bag","Mail"
                    for (Manifest.CargoHold cargoHold : cargoHoldList) {
                        String cargoHoldName = cargoHold.getName();
                        //判断货舱名字是否和前台传回名字相同数据
                        if (name.equals(cargoHoldName)) {
                            BigDecimal cargoHoldWeight = cargoHold.getWeight();
                            weight = weight.add(cargoHoldWeight);
                            if ("Cargo".equals(type)) {
                                cargoWeight = cargoWeight.add(cargoHoldWeight);
                                break;
                            }
                            if ("Bag".equals(type)) {
                                bagWeight = bagWeight.add(cargoHoldWeight);
                                break;
                            }
                            if ("Mail".equals(type)) {
                                mailWeight = mailWeight.add(cargoHoldWeight);
                                break;
                            }
                        }
                    }
                }
            }
            hold.setbWeight(bagWeight);
            hold.setcWeight(cargoWeight);
            hold.setmWeight(mailWeight);
            hold.setCargoWeight(weight);
            manifest.setcFs(cFs);
            manifest.setbFs(bFs);
            manifest.setmFs(mFs);
        }

    }


    //客舱信息组装

    private void mapPassengerBin(CabinConfig cabinConfig, FlightTypeConfig typeConfig) {
        List<PassengerCabin> cabinList = typeConfig.getPassengerCabinList();
        for (PassengerCabin cabin : cabinList) {
            String name = cabin.getName().toLowerCase();
            switch (name) {
                case "oa": {
                    cabin.setAdultNum(cabinConfig.getAdultOa() == null ? 0 : cabinConfig.getAdultOa());
                    cabin.setChildNum(cabinConfig.getChildOa() == null ? 0 : cabinConfig.getChildOa());
                    cabin.setInfantNum(cabinConfig.getInfantOa() == null ? 0 : cabinConfig.getInfantOa());
                    break;
                }
                case "ob": {
                    cabin.setAdultNum(cabinConfig.getAdultOb() == null ? 0 : cabinConfig.getAdultOb());
                    cabin.setChildNum(cabinConfig.getChildOb() == null ? 0 : cabinConfig.getChildOb());
                    cabin.setInfantNum(cabinConfig.getInfantOb() == null ? 0 : cabinConfig.getInfantOb());
                    break;
                }
                case "oc": {
                    cabin.setAdultNum(cabinConfig.getAdultOc() == null ? 0 : cabinConfig.getAdultOc());
                    cabin.setChildNum(cabinConfig.getChildOc() == null ? 0 : cabinConfig.getChildOc());
                    cabin.setInfantNum(cabinConfig.getInfantOc() == null ? 0 : cabinConfig.getInfantOc());
                    break;
                }
            }

        }


    }

    //货仓信息组装
    private void mapCargoHold(CabinConfig cabinConfig, FlightTypeConfig typeConfig) {
        List<CargoHold> holdList = typeConfig.getCargoHoldList();
        for (CargoHold hold : holdList) {
            String name = hold.getName().trim().toLowerCase();
            BigDecimal weight = BigDecimal.ZERO;
            switch (name) {
                case "1": {
                    BigDecimal cargoOne = cabinConfig.getCargoOne() == null ? BigDecimal.ZERO : cabinConfig.getCargoOne();
                    BigDecimal bagOne = cabinConfig.getBagOne() == null ? BigDecimal.ZERO : cabinConfig.getBagOne();
                    BigDecimal mailOne = cabinConfig.getMailOne() == null ? BigDecimal.ZERO : cabinConfig.getMailOne();
                    weight = weight.add(cargoOne).add(bagOne).add(mailOne);
                    setCargoHold(hold, cargoOne, bagOne, mailOne);
                    hold.setcWeight(cargoOne);
                    hold.setbWeight(bagOne);
                    hold.setmWeight(mailOne);
                    break;
                }
                case "3": {
                    BigDecimal cargoThree = cabinConfig.getCargoThree() == null ? BigDecimal.ZERO : cabinConfig.getCargoThree();
                    BigDecimal bagThree = cabinConfig.getBagThree() == null ? BigDecimal.ZERO : cabinConfig.getBagThree();
                    BigDecimal mailThree = cabinConfig.getMailThree() == null ? BigDecimal.ZERO : cabinConfig.getMailThree();
                    weight = weight.add(cargoThree).add(bagThree).add(mailThree);
                    setCargoHold(hold, cargoThree, bagThree, mailThree);
                    hold.setcWeight(cargoThree);
                    hold.setbWeight(bagThree);
                    hold.setmWeight(mailThree);
                    break;
                }

                case "4": {
                    BigDecimal cargoFour = cabinConfig.getCargoFour() == null ? BigDecimal.ZERO : cabinConfig.getCargoFour();
                    BigDecimal bagFour = cabinConfig.getBagFour() == null ? BigDecimal.ZERO : cabinConfig.getBagFour();
                    BigDecimal mailFour = cabinConfig.getMailFour() == null ? BigDecimal.ZERO : cabinConfig.getMailFour();
                    weight = weight.add(cargoFour).add(bagFour).add(mailFour);
                    setCargoHold(hold, cargoFour, bagFour, mailFour);
                    hold.setcWeight(cargoFour);
                    hold.setbWeight(bagFour);
                    hold.setmWeight(mailFour);
                    break;
                }

                case "5": {
                    BigDecimal cargoFive = cabinConfig.getCargoFive() == null ? BigDecimal.ZERO : cabinConfig.getCargoFive();
                    BigDecimal bagFive = cabinConfig.getBagFive() == null ? BigDecimal.ZERO : cabinConfig.getBagFive();
                    BigDecimal mailFive = cabinConfig.getMailFive() == null ? BigDecimal.ZERO : cabinConfig.getMailFive();
                    weight = weight.add(cargoFive).add(bagFive).add(mailFive);
                    setCargoHold(hold, cargoFive, bagFive, mailFive);
                    hold.setcWeight(cargoFive);
                    hold.setbWeight(bagFive);
                    hold.setmWeight(mailFive);
                    break;
                }

                case "fs": {
                    BigDecimal cargoFs = cabinConfig.getCargoFs() == null ? BigDecimal.ZERO : cabinConfig.getCargoFs();
                    BigDecimal bagFs = cabinConfig.getBagFs() == null ? BigDecimal.ZERO : cabinConfig.getBagFs();
                    BigDecimal mailFs = cabinConfig.getMailFs() == null ? BigDecimal.ZERO : cabinConfig.getMailFs();
                    weight = weight.add(cargoFs).add(bagFs).add(mailFs);
                    setCargoHold(hold, cargoFs, bagFs, mailFs);
                    hold.setcWeight(cargoFs);
                    hold.setbWeight(bagFs);
                    hold.setmWeight(mailFs);
                    break;
                }

            }
            hold.setCargoWeight(weight);
        }

    }


    // 组装货仓信息数据
    private void setCargoHold(CargoHold hold, BigDecimal cargoWeight, BigDecimal bagWeight, BigDecimal mailWeight) {
//        switch (hold.getCargoType()) {
//            case C: {
//                hold.setCargoWeight(cargoWeight);
//                break;
//            }
//            case B: {
//                hold.setCargoWeight(bagWeight);
//                break;
//            }
//            case M: {
//                hold.setCargoWeight(mailWeight);
//                break;
//            }
//        }

    }


}
