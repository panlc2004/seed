package com.czy.seed.mvc.wbm.math.formula.base;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.*;
import com.czy.seed.mvc.wbm.config.entity.type.types.PassengerType;
import com.czy.seed.mvc.wbm.config.entity.type.types.Position;
import com.czy.seed.mvc.wbm.config.service.IndexConfigService;
import com.czy.seed.mvc.wbm.math.formula.util.MathTool;
import com.czy.seed.mybatis.tool.SpringContextHelper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by panlc on 2017-04-24.
 * 无油参数计算
 */
public class ZfMath extends DoMath {

    private BigDecimal zfw;
    private BigDecimal zfi;
    private BigDecimal zfCg;

    public IndexConfigService indexConfigServiceImpl;

    public ZfMath(FlightInfo flightInfo) {
        super(flightInfo);
        zfw = buildZfw();
        zfi = buildZfi();
        zfCg = buildZfCg();
    }

    public BigDecimal getZfw() {
        return zfw;
    }

    public BigDecimal getZfi() {
        return zfi;
    }

    public void setIndexConfigServiceImpl(IndexConfigService indexConfigServiceImpl) {
        this.indexConfigServiceImpl = indexConfigServiceImpl;
    }

    private IndexConfigService getIndexConfigServiceImpl() {
        if (indexConfigServiceImpl == null) {
            indexConfigServiceImpl = SpringContextHelper.getBeanById("indexConfigServiceImpl");
        }
        return indexConfigServiceImpl;
    }

    /**
     * 计算无油重量
     *
     * @return
     */
    private BigDecimal buildZfw() {
        FlightInfo flightInfo = super.getFlightInfo();
        return super.getDow().add(getTotalCabinWeight(flightInfo))
                .add(getTotalCargoWeight(flightInfo));
    }

    /**
     * 计算无油指数
     *
     * @return
     */
    private BigDecimal buildZfi() {
        FlightInfo flightInfo = super.getFlightInfo();
        BigDecimal zfi = super.getDoi();
        FlightTypeConfig flightTypeConfig = flightInfo.getFlightTypeConfig();

        //货仓指数计算
        List<CargoHold> cargoHoldList = flightTypeConfig.getCargoHoldList();
        Map<String, List<IndexConfig>> cargoConfigGroup = getConfigGroup(flightInfo, 2); //查找货仓配置
        for (CargoHold cargoHold : cargoHoldList) {
            zfi = zfi.add(MathTool.linearInterpolation(
                    cargoConfigGroup.get(cargoHold.getName()), cargoHold.getCargoWeight()));
        }

        //客仓指数计算
        List<PassengerCabin> cabinHoldList = flightTypeConfig.getPassengerCabinList();
        Map<String, List<IndexConfig>> cabinConfigGroup = getConfigGroup(flightInfo, 1); //查找客仓配置
        for (PassengerCabin cabin : cabinHoldList) {
            zfi = zfi.add(MathTool.linearInterpolation(
                    cabinConfigGroup.get(cabin.getName()), new BigDecimal(cabin.getAdultNum())));    //TODO 人数取值可能有问题
        }

        return zfi;
    }

    /**
     * 计算无油重心
     *
     * @return
     */
    private BigDecimal buildZfCg() {
        FlightTypeConfig flightTypeConfig = super.getFlightInfo().getFlightTypeConfig();
        return MathTool.buildCg(zfi, zfw, flightTypeConfig);
    }

    /**
     * 查找指数配置参数
     *
     * @param flightInfo 航班信息
     * @param types      配置类型：1.客舱参数、2.货仓参数、3.燃油参数
     * @return Map<String, List<IndexConfig>>
     */
    public Map<String, List<IndexConfig>> getConfigGroup(FlightInfo flightInfo, int types) {
        List<IndexConfig> indexConfigList = getIndexConfigs(flightInfo, types);
        return indexConfigList.stream().collect(
                Collectors.groupingBy(IndexConfig::getHouseName));
    }


    public Map<PassengerType, List<Passenger>> getPassengerGroup(List<Passenger> passengerList) {
        return passengerList.stream().collect(
                Collectors.groupingBy(Passenger::getPassengerType));
    }


    private List<IndexConfig> getIndexConfigs(FlightInfo flightInfo, int types) {
        return getIndexConfigServiceImpl().findIndexConfigList(
                flightInfo.getFlightTypeConfig().getId(), types);
    }

    /**
     * 计算所有货仓货物总重量
     *
     * @param flightInfo 航班信息
     * @return 所有货仓货物总重量
     */
    private BigDecimal getTotalCargoWeight(FlightInfo flightInfo) {
        List<CargoHold> cargoHoldList = flightInfo.getFlightTypeConfig().getCargoHoldList();
        BigDecimal totalCargoWeight = BigDecimal.ZERO;
        for (CargoHold cargoHold : cargoHoldList) {
            totalCargoWeight.add(cargoHold.getCargoWeight());
        }
        return totalCargoWeight;
    }

    /**
     * 计算乘客舱所有乘座人员总重量
     *
     * @param flightInfo 航班信息
     * @return 包含随行机组人员在内的乘客总重量
     */
    private BigDecimal getTotalCabinWeight(FlightInfo flightInfo) {
        BigDecimal totalCarbinWeight = BigDecimal.ZERO;
        Map<PassengerType, BigDecimal> passengerNums = getPassengerNums(flightInfo);
        FlightTypeConfig flightTypeConfig = flightInfo.getFlightTypeConfig();
        Map<PassengerType, List<Passenger>> passengerGroup = getPassengerGroup(flightTypeConfig.getPassengers());
        totalCarbinWeight
                .add(passengerNums.get(PassengerType.AUDLT)
                        .multiply(passengerGroup.get(PassengerType.AUDLT).get(0).getStandardWeight()))
                .add(passengerNums.get(PassengerType.CHILD)
                        .multiply(passengerGroup.get(PassengerType.CHILD).get(0).getStandardWeight()))
                .add(passengerNums.get(PassengerType.INFANT)
                        .multiply(passengerGroup.get(PassengerType.INFANT).get(0).getStandardWeight()));
        return totalCarbinWeight;
    }


    private Map<PassengerType, BigDecimal> getPassengerNums(FlightInfo flightInfo) {
        FlightTypeConfig flightTypeConfig = flightInfo.getFlightTypeConfig();
        List<PassengerCabin> passengerCabinList = flightTypeConfig.getPassengerCabinList();
        BigDecimal totalAdultNum = BigDecimal.ZERO;
        BigDecimal totalChildNum = BigDecimal.ZERO;
        BigDecimal totalInfantNum = BigDecimal.ZERO;
        for (PassengerCabin cargoHold : passengerCabinList) {
            totalAdultNum = totalAdultNum.add(new BigDecimal(cargoHold.getAdultNum()));
            totalChildNum = totalChildNum.add(new BigDecimal(cargoHold.getChildNum()));
            totalInfantNum = totalInfantNum.add(new BigDecimal(cargoHold.getInfantNum()));
        }
        //实际成人数量=乘客成人数量 + “超出标准之外”的随行机组人员数量
        totalAdultNum = totalAdultNum.add(buildAccompaniedCrewNum(flightInfo));
        Map<PassengerType, BigDecimal> nums = new HashMap<>();
        nums.put(PassengerType.AUDLT, totalAdultNum);
        nums.put(PassengerType.CHILD, totalChildNum);
        nums.put(PassengerType.INFANT, totalInfantNum);
        return nums;
    }

    /**
     * 计算“超出标准之外”的随行机组人员数量
     *
     * @param flightInfo 航班信息
     * @return 返回随行机组人员数量
     */
    private BigDecimal buildAccompaniedCrewNum(FlightInfo flightInfo) {
        Map<Position, List<Crew>> crewGroup = super.getCrewGroup();
        BigDecimal crewDiffNum = getCrewDiffNum(crewGroup, Position.MID);
        return crewDiffNum;
    }

}
