package com.czy.seed.mvc.wbm.msg.flight;

import com.czy.seed.mvc.wbm.config.entity.type.CargoHold;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panlc on 2017-04-07.
 */
public class Flight {
    private String flightNo;                            //航班号
    private String segment;                             //航段 //TODO 航班参数，非配置参数，要分为航班类和配置类
    private Integer crewFir;                            //机组配比——机组人员数量
    private Integer crewSec;                            //机组配比
    private Integer crewThi;                            //机组配比——乘客人员数量
    private Integer galleyGoodsFir;                     //机供品数量——1
    private Integer galleyGoodsSec;                     //机供品数量——2
    private double takeOffFuel;                         //起飞油量
    private double tripFuel;                            //航线用油
    private String flightCode;                          //机号
    private double baseWeight;                          //基本重量
    private double baseIndex;                           //基本索引值
    private double maxTakeOffWeight;                    //最大起飞重量
    private double maxLandWeight;                       //最大落地重量
    private double maxZefWeight;                        //最大无油重量
    private int seatNum;                                //最大座位数量
    private List<PassengerCabin> passengerCabinList = new ArrayList<>();    //客仓信息
    private List<CargoHold> cargoHoldList = new ArrayList<>();              //货物信息
    private Integer cNumber;                            //货物总数
    private Integer bNumber;                            //行李总数
    private Integer mNumber;                            //邮件总数
    private double actualWeight;                        //实际重量
    private double takOffWeight;                        //起飞重量
    private double landWeight;                          //落地重量
    private double zefWeight;                           //无油重量
    private double opeQuotient;                         //干操作重量
    private double opeIndex;                            //干操作指数
    private BigDecimal pantryFwd = new BigDecimal(87);  //TODO
    private BigDecimal pantryAft = new BigDecimal(130); //TODO
    private double cabinWeight;                          //乘客每人重量  //TODO 移至参数实体内

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Integer getCrewFir() {
        return crewFir;
    }

    public void setCrewFir(Integer crewFir) {
        this.crewFir = crewFir;
    }

    public Integer getCrewSec() {
        return crewSec;
    }

    public void setCrewSec(Integer crewSec) {
        this.crewSec = crewSec;
    }

    public Integer getCrewThi() {
        return crewThi;
    }

    public void setCrewThi(Integer crewThi) {
        this.crewThi = crewThi;
    }

    public Integer getGalleyGoodsFir() {
        return galleyGoodsFir;
    }

    public void setGalleyGoodsFir(Integer galleyGoodsFir) {
        this.galleyGoodsFir = galleyGoodsFir;
    }

    public Integer getGalleyGoodsSec() {
        return galleyGoodsSec;
    }

    public void setGalleyGoodsSec(Integer galleyGoodsSec) {
        this.galleyGoodsSec = galleyGoodsSec;
    }

    public double getTakeOffFuel() {
        return takeOffFuel;
    }

    public void setTakeOffFuel(double takeOffFuel) {
        this.takeOffFuel = takeOffFuel;
    }

    public double getTripFuel() {
        return tripFuel;
    }

    public void setTripFuel(double tripFuel) {
        this.tripFuel = tripFuel;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public double getBaseIndex() {
        return baseIndex;
    }

    public void setBaseIndex(double baseIndex) {
        this.baseIndex = baseIndex;
    }

    public double getMaxTakeOffWeight() {
        return maxTakeOffWeight;
    }

    public void setMaxTakeOffWeight(double maxTakeOffWeight) {
        this.maxTakeOffWeight = maxTakeOffWeight;
    }

    public double getMaxLandWeight() {
        return maxLandWeight;
    }

    public void setMaxLandWeight(double maxLandWeight) {
        this.maxLandWeight = maxLandWeight;
    }

    public double getMaxZefWeight() {
        return maxZefWeight;
    }

    public void setMaxZefWeight(double maxZefWeight) {
        this.maxZefWeight = maxZefWeight;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public List<PassengerCabin> getPassengerCabinList() {
        return passengerCabinList;
    }

    public void setPassengerCabinList(List<PassengerCabin> passengerCabinList) {
        this.passengerCabinList = passengerCabinList;
    }

    public List<CargoHold> getCargoHoldList() {
        return cargoHoldList;
    }

    public void setCargoHoldList(List<CargoHold> cargoHoldList) {
        this.cargoHoldList = cargoHoldList;
    }

    public Integer getcNumber() {
        return cNumber;
    }

    public void setcNumber(Integer cNumber) {
        this.cNumber = cNumber;
    }

    public Integer getbNumber() {
        return bNumber;
    }

    public void setbNumber(Integer bNumber) {
        this.bNumber = bNumber;
    }

    public Integer getmNumber() {
        return mNumber;
    }

    public void setmNumber(Integer mNumber) {
        this.mNumber = mNumber;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public double getTakOffWeight() {
        return takOffWeight;
    }

    public void setTakOffWeight(double takOffWeight) {
        this.takOffWeight = takOffWeight;
    }

    public double getLandWeight() {
        return landWeight;
    }

    public void setLandWeight(double landWeight) {
        this.landWeight = landWeight;
    }

    public double getZefWeight() {
        return zefWeight;
    }

    public void setZefWeight(double zefWeight) {
        this.zefWeight = zefWeight;
    }

    public double getOpeQuotient() {
        return opeQuotient;
    }

    public void setOpeQuotient(double opeQuotient) {
        this.opeQuotient = opeQuotient;
    }

    public double getOpeIndex() {
        return opeIndex;
    }

    public void setOpeIndex(double opeIndex) {
        this.opeIndex = opeIndex;
    }

    public BigDecimal getPantryFwd() {
        return pantryFwd;
    }

    public void setPantryFwd(BigDecimal pantryFwd) {
        this.pantryFwd = pantryFwd;
    }

    public BigDecimal getPantryAft() {
        return pantryAft;
    }

    public void setPantryAft(BigDecimal pantryAft) {
        this.pantryAft = pantryAft;
    }

    public double getCabinWeight() {
        return cabinWeight;
    }

    public void setCabinWeight(double cabinWeight) {
        this.cabinWeight = cabinWeight;
    }
}
