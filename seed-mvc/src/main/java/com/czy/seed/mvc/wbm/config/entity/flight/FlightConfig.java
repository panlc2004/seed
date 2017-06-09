package com.czy.seed.mvc.wbm.config.entity.flight;



import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 航班架次配置
 * Created by panlc on 2017-04-28.
 */
public class FlightConfig implements Serializable {

    private static final long serialVersionUID = -7760232320264778230L;

    @Id
    private Long id;
    private String num; //出厂序号
    private String msn; //机号
    private BigDecimal baseWeight;  //基本重量
    private BigDecimal baseIndex;   //基本指数
    private BigDecimal takeOffFuel; //起飞油量
    private BigDecimal tripFuel;    //航班用油
    private BigDecimal limitedMtow; //最大起飞重量
    private BigDecimal limitedMldw; //最大落地重量
    private BigDecimal limitedMzfw; //最大无油重量

    private Long flightTypeConfigId;                   //航班机型 ID

    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;

    public BigDecimal getLimitedMtow() {
        return limitedMtow;
    }

    public void setLimitedMtow(BigDecimal limitedMtow) {
        this.limitedMtow = limitedMtow;
    }

    public BigDecimal getLimitedMldw() {
        return limitedMldw;
    }

    public void setLimitedMldw(BigDecimal limitedMldw) {
        this.limitedMldw = limitedMldw;
    }

    public BigDecimal getLimitedMzfw() {
        return limitedMzfw;
    }

    public void setLimitedMzfw(BigDecimal limitedMzfw) {
        this.limitedMzfw = limitedMzfw;
    }

    public FlightTypeConfig getFlightTypeConfig() {
        return flightTypeConfig;
    }

    public void setFlightTypeConfig(FlightTypeConfig flightTypeConfig) {
        this.flightTypeConfig = flightTypeConfig;
    }

    public Long getFlightTypeConfigId() {
        return flightTypeConfigId;
    }

    public void setFlightTypeConfigId(Long flightTypeConfigId) {
        this.flightTypeConfigId = flightTypeConfigId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public BigDecimal getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(BigDecimal baseWeight) {
        this.baseWeight = baseWeight;
    }

    public BigDecimal getBaseIndex() {
        return baseIndex;
    }

    public void setBaseIndex(BigDecimal baseIndex) {
        this.baseIndex = baseIndex;
    }

    public BigDecimal getTakeOffFuel() {
        return takeOffFuel;
    }

    public void setTakeOffFuel(BigDecimal takeOffFuel) {
        this.takeOffFuel = takeOffFuel;
    }

    public BigDecimal getTripFuel() {
        return tripFuel;
    }

    public void setTripFuel(BigDecimal tripFuel) {
        this.tripFuel = tripFuel;
    }
}
