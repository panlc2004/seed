package com.czy.seed.mvc.wbm.msg.service.impl;

import com.czy.seed.mvc.wbm.config.entity.BaseInfoBean;
import com.czy.seed.mvc.wbm.config.entity.Manifest;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.*;
import com.czy.seed.mvc.wbm.config.entity.type.types.Position;
import com.czy.seed.mvc.wbm.config.mapper.FlightTypeConfigMapper;
import com.czy.seed.mvc.wbm.math.factory.MathResultFactory;
import com.czy.seed.mvc.wbm.math.formula.base.LdMath;
import com.czy.seed.mvc.wbm.msg.ReportData;
import com.czy.seed.mvc.wbm.msg.flight.SheetMessage;
import com.czy.seed.mvc.wbm.msg.service.MessageService;
import com.czy.seed.mvc.wbm.msg.template.TemplateLoader;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.czy.seed.mvc.wbm.math.formula.base.Math;

/**
 * Created by panlc on 2017-04-25.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private FlightTypeConfigMapper flightTypeConfigMapper;

    /**
     * 生成舱单
     *
     * @param flightInfo
     * @return
     */
    public String createMessage(FlightInfo flightInfo, CabinConfig cabinConfig) {
//        //flightInfo.setFlightTypeConfig(findTypeConfigByFlightNo(flightInfo.getFlightTypeConfig().getFlightType()));
//        ReportData reportData = buildReportData(flightInfo, MathResultFactory.getMathResult(flightInfo));
//        BigDecimal cockpitActuralNum = BigDecimal.ZERO;
//        BigDecimal serviceActuralNum = BigDecimal.ZERO;
//        BigDecimal midActuralNum = BigDecimal.ZERO;
//        List<Crew> crewList = flightInfo.getFlightTypeConfig().getCrewList();
//        for (Crew crew : crewList) {
//            Position position = crew.getPosition();
//            switch (position) {
//                case COCKPIT: {
//                    cockpitActuralNum = cockpitActuralNum.add(crew.getActuralNum());
//                    break;
//                }
//                case MID: {
//                    break;
//                }
//                default: {
//                    serviceActuralNum = serviceActuralNum.add(crew.getActuralNum());
//                }
//            }
//        }
//
//
//        List<GalleyGoods> galleyGoodsList = flightInfo.getFlightTypeConfig().getGalleyGoodsList();
//        BigDecimal aftActualWeight = BigDecimal.ZERO;
//        BigDecimal fwdActualWeight = BigDecimal.ZERO;
//        for (GalleyGoods galleyGoods : galleyGoodsList) {
//            if (galleyGoods.getPosition() == Position.FWD) {
//                fwdActualWeight = fwdActualWeight.add(galleyGoods.getActualWeight());
//            } else {
//                aftActualWeight = aftActualWeight.add(galleyGoods.getActualWeight());
//            }
//
//        }
//        //设置机组人员和乘务人员
//        reportData.setCockpitActuralNum(cockpitActuralNum);
//        reportData.setMidActuralNum(midActuralNum);
//        reportData.setServiceActuralNum(serviceActuralNum);
//        //设置机供品参数
//        reportData.setFwdActualWeight(fwdActualWeight);
//        reportData.setAftActualWeight(aftActualWeight);
//
//        reportData.setCabinConfig(cabinConfig);
//
//        //设置乘客信息
//        reportData.setAdultNum(cabinConfig.getAdultOa() + cabinConfig.getAdultOb() + cabinConfig.getAdultOc());
//        reportData.setChildNum(cabinConfig.getChildOa() + cabinConfig.getChildOb() + cabinConfig.getChildOc());
//        reportData.setInfantNum(cabinConfig.getInfantOa() + cabinConfig.getInfantOb() + cabinConfig.getInfantOc());
//        //设置货物信息
//        BigDecimal cWeight = BigDecimal.ZERO;
//        cWeight = cWeight.add(cabinConfig.getCargoOne())
//                .add(cabinConfig.getCargoThree())
//                .add(cabinConfig.getCargoFour())
//                .add(cabinConfig.getCargoFive());
//        BigDecimal bWeight = BigDecimal.ZERO;
//        bWeight = bWeight.add(cabinConfig.getBagOne())
//                .add(cabinConfig.getBagThree())
//                .add(cabinConfig.getBagFour())
//                .add(cabinConfig.getBagFive());
//        BigDecimal mWeight = BigDecimal.ZERO;
//        mWeight = mWeight.add(cabinConfig.getMailOne())
//                .add(cabinConfig.getMailThree())
//                .add(cabinConfig.getMailFour())
//                .add(cabinConfig.getMailFive());
//        reportData.setcWeight(cWeight);
//        reportData.setbWeight(bWeight);
//        reportData.setmWeight(mWeight);
//
//        //设置制作人 审核人 签收人 制作日期 版本号信息
//        reportData.setPre("ZHANGSAN");
//        reportData.setApp("LISI");
//        reportData.setSig("WANGWU");
//        reportData.setData("2017-06-01");
//        reportData.setVerson("V1.0.0");
//        String message = TemplateLoader.parse("9C", reportData);
        return null;
    }

    @Override
    public String createMessage(FlightInfo flightInfo, Manifest manifest, SheetMessage sheetMessage) {
        ReportData reportData = buildReportData(flightInfo, MathResultFactory.getMathResult(flightInfo), manifest);


        BigDecimal cockpitActuralNum = BigDecimal.ZERO;
        BigDecimal serviceActuralNum = BigDecimal.ZERO;
        BigDecimal midActuralNum = BigDecimal.ZERO;
        List<Crew> crewList = flightInfo.getFlightTypeConfig().getCrewList();
        for (Crew crew : crewList) {
            Position position = crew.getPosition();
            switch (position) {
                case COCKPIT: {
                    cockpitActuralNum = cockpitActuralNum.add(crew.getActuralNum());
                    break;
                }
                case MID: {
                    break;
                }
                default: {
                    serviceActuralNum = serviceActuralNum.add(crew.getActuralNum());
                }
            }
        }


        List<GalleyGoods> galleyGoodsList = flightInfo.getFlightTypeConfig().getGalleyGoodsList();
        BigDecimal aftActualWeight = BigDecimal.ZERO;
        BigDecimal fwdActualWeight = BigDecimal.ZERO;
        for (GalleyGoods galleyGoods : galleyGoodsList) {
            if (galleyGoods.getPosition() == Position.FWD) {
                fwdActualWeight = fwdActualWeight.add(galleyGoods.getActualWeight());
            } else {
                aftActualWeight = aftActualWeight.add(galleyGoods.getActualWeight());
            }

        }


        //设置机组人员和乘务人员
        reportData.setCockpitActuralNum(cockpitActuralNum);
        reportData.setMidActuralNum(midActuralNum);
        reportData.setServiceActuralNum(serviceActuralNum);
        //设置机供品参数
        reportData.setFwdActualWeight(fwdActualWeight);
        reportData.setAftActualWeight(aftActualWeight);

        List<PassengerCabin> list = flightInfo.getFlightTypeConfig().getPassengerCabinList();
        Integer adultNum = 0;
        Integer childNum = 0;
        Integer infantNum = 0;
        for (PassengerCabin passengerCabin : list) {
            adultNum += passengerCabin.getAdultNum();
            childNum += passengerCabin.getChildNum();
            infantNum += passengerCabin.getInfantNum();
        }

        List<CargoHold> cargoHoldList = flightInfo.getFlightTypeConfig().getCargoHoldList();
        BigDecimal bWeight = BigDecimal.ZERO;
        BigDecimal cWeight = BigDecimal.ZERO;
        BigDecimal mWeight = BigDecimal.ZERO;
        BigDecimal cargoWeight = BigDecimal.ZERO;
        for (CargoHold cargoHold : cargoHoldList) {
            bWeight = bWeight.add(cargoHold.getbWeight());
            cWeight = cWeight.add(cargoHold.getcWeight());
            mWeight = mWeight.add(cargoHold.getmWeight());
        }
        cargoWeight = cargoWeight.add(bWeight).add(cWeight).add(mWeight);

        List<Passenger> passengers = flightInfo.getFlightTypeConfig().getPassengers();

        BigDecimal totalWeight = BigDecimal.ZERO;
        for (Passenger passenger : passengers) {
            BigDecimal standardWeight = passenger.getStandardWeight();

            String typeCode = passenger.getPassengerTypeCode();

            switch (typeCode) {
                case "ADULT": {
                    totalWeight = totalWeight.add(standardWeight.multiply(new BigDecimal(adultNum)));
                    break;
                }
                case "CHILD": {
                    totalWeight = totalWeight.add(standardWeight.multiply(new BigDecimal(childNum)));
                    break;
                }
                case "INFANT": {
                    totalWeight = totalWeight.add(standardWeight.multiply(new BigDecimal(infantNum)));
                    break;
                }
            }


        }

        reportData.setManifest(manifest);
        reportData.setManySegmentList(manifest.getManySegmentList());
        //(Adult_OA + Adult_OB + Adult_OC) * 72 + (Child_OA + Child_OB + Child_OC) * 36 + (Infant_OA + Infant_OB + Infant_OC) * 10 + Weight_C1 + Weight_C3 + Weight_C4 + Weight_C5 + Val(Text4.Text) * 72
        reportData.setPayload(totalWeight.add(cargoWeight));

        reportData.setSpa(reportData.getMinPlNum().subtract(reportData.getPayload()));
//
//        reportData.setCabinConfig(cabinConfig);
//
//        //设置乘客信息
        reportData.setAdultNum(adultNum);
        reportData.setChildNum(childNum);
        reportData.setInfantNum(infantNum);
//        //设置货物信息
        reportData.setcWeight(cWeight);
        reportData.setbWeight(bWeight);
        reportData.setmWeight(mWeight);

        reportData.setcFs(manifest.getcFs());
        reportData.setbFs(manifest.getbFs());
        reportData.setmFs(manifest.getmFs());
//
        //设置制作人 审核人 签收人 制作日期 版本号信息
        reportData.setPre("");
        reportData.setApp("");
        reportData.setSig("");
        reportData.setData("2017-06-01");
        reportData.setVerson("V1.0.0");
        reportData.setNowDate(new Date());
        String message = TemplateLoader.parse("9C", reportData);
        LdMath ldMath = reportData.getLdMath();
        sheetMessage.setLdw(ldMath.getLdw().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP));
        sheetMessage.setLdwi(ldMath.getLdi());
        sheetMessage.setZfw(ldMath.getZfw().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP));
        sheetMessage.setZfwi(ldMath.getZfi());
        sheetMessage.setTow(ldMath.getTow().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP));
        sheetMessage.setTowi(ldMath.getToi());
        return message;
    }

    private FlightTypeConfig findTypeConfigByFlightNo(String flightType) {
        QueryParams queryParams = new QueryParams(FlightTypeConfig.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("flightType", flightType);
        return flightTypeConfigMapper.selectOneRelativeByParams(queryParams);
    }

    /**
     * 生成舱单模板参数
     *
     * @param flightInfo 航班信息
     * @param math       计算结果
     * @return
     */
    private ReportData buildReportData(FlightInfo flightInfo, Math math, Manifest manifest) {
        ReportData rd = new ReportData();
        rd.setFlightInfo(flightInfo);
        rd.setHeader("Header"); //DOTO
        rd.setLdMath((LdMath) math);
        BigDecimal dow = ((LdMath) math).getDow();
        //'三大限制业载================================================================================
//        '                  MTOW             TOF         DOW       客舱机组
//        PL_MTOW = Val(Text11.Text) - Val(Text14.Text) - DOW - Val(Text4.Text) * 72
//        '                  MLDW                TOF            TF             DOW        客舱机组
//        PL_MLDW = Val(Text12.Text) - (Val(Text14.Text) - Val(Text15.Text)) - DOW - Val(Text4.Text) * 72
//        '                  MZFW      DOW        客舱机组
//        PL_MZFW = Val(Text13.Text) - DOW - Val(Text4.Text) * 72
//        Text100.SelText = "PL_Usb=" + Format(F_min(PL_MTOW, PL_MLDW, PL_MZFW), "#0") + " Act=" + Format(Payload, "#0") + " Spa=" + Format(F_min(PL_MTOW, PL_MLDW, PL_MZFW) - Payload, "#0") + ch$


        BaseInfoBean infoBean = manifest.getBaseInfoBean();
        BigDecimal limitedMtow = infoBean.getLimitedMtow();
        BigDecimal limitedMldw = infoBean.getLimitedMldw();
        BigDecimal limitedMzfw = infoBean.getLimitedMzfw();
        BigDecimal plMtow = limitedMtow.subtract(infoBean.getTakeOffFuel()).subtract(dow);
        BigDecimal plMldw = limitedMldw.subtract(infoBean.getTakeOffFuel().subtract(infoBean.getTripFuel())).subtract(dow);
        BigDecimal plMzfw = limitedMzfw.subtract(dow);

        rd.setPlMtow(plMtow);
        rd.setPlMldw(plMldw);
        rd.setPlMzfw(plMzfw);
        rd.setMinPlNum(minNum(plMtow, plMldw, plMzfw));
        return rd;
    }

    /**
     * 获取业载数据中最小值
     *
     * @param plMtow 业载最大起飞重量
     * @param plMldw 业载最大落地重量
     * @param plMzfw 业载最大无油重量
     * @return
     */
    private BigDecimal minNum(BigDecimal plMtow, BigDecimal plMldw, BigDecimal plMzfw) {
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        bigDecimalList.add(plMldw);
        bigDecimalList.add(plMtow);
        bigDecimalList.add(plMzfw);
        Collections.sort(bigDecimalList);
        return bigDecimalList.get(0);
    }

}
