package com.czy.seed.mvc.wbm.msg.service.impl;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.*;
import com.czy.seed.mvc.wbm.config.entity.type.types.Position;
import com.czy.seed.mvc.wbm.config.mapper.FlightTypeConfigMapper;
import com.czy.seed.mvc.wbm.math.factory.MathResultFactory;
import com.czy.seed.mvc.wbm.msg.ReportData;
import com.czy.seed.mvc.wbm.msg.service.MessageService;
import com.czy.seed.mvc.wbm.msg.template.TemplateLoader;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        //flightInfo.setFlightTypeConfig(findTypeConfigByFlightNo(flightInfo.getFlightTypeConfig().getFlightType()));
        ReportData reportData = buildReportData(flightInfo, MathResultFactory.getMathResult(flightInfo));
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

        reportData.setCabinConfig(cabinConfig);

        //设置乘客信息
        reportData.setAdultNum(cabinConfig.getAdultOa() + cabinConfig.getAdultOb() + cabinConfig.getAdultOc());
        reportData.setChildNum(cabinConfig.getChildOa() + cabinConfig.getChildOb() + cabinConfig.getChildOc());
        reportData.setInfantNum(cabinConfig.getInfantOa() + cabinConfig.getInfantOb() + cabinConfig.getInfantOc());
        //设置货物信息
        BigDecimal cWeight = BigDecimal.ZERO;
        cWeight = cWeight.add(cabinConfig.getCargoOne())
                .add(cabinConfig.getCargoThree())
                .add(cabinConfig.getCargoFour())
                .add(cabinConfig.getCargoFive());
        BigDecimal bWeight = BigDecimal.ZERO;
        bWeight = bWeight.add(cabinConfig.getBagOne())
                .add(cabinConfig.getBagThree())
                .add(cabinConfig.getBagFour())
                .add(cabinConfig.getBagFive());
        BigDecimal mWeight = BigDecimal.ZERO;
        mWeight = mWeight.add(cabinConfig.getMailOne())
                .add(cabinConfig.getMailThree())
                .add(cabinConfig.getMailFour())
                .add(cabinConfig.getMailFive());
        reportData.setcWeight(cWeight);
        reportData.setbWeight(bWeight);
        reportData.setmWeight(mWeight);

        //设置制作人 审核人 签收人 制作日期 版本号信息
        reportData.setPre("ZHANGSAN");
        reportData.setApp("LISI");
        reportData.setSig("WANGWU");
        reportData.setData("2017-06-01");
        reportData.setVerson("V1.0.0");
        String message = TemplateLoader.parse("9C", reportData);
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
     * @param flightInfo
     * @param math
     * @return
     */
    private ReportData buildReportData(FlightInfo flightInfo, Math math) {
        ReportData rd = new ReportData();
        rd.setFlightInfo(flightInfo);
        rd.setHeader("Header"); //DOTO
        return rd;
    }


}
