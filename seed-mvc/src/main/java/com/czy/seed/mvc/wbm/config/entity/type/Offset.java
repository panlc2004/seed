package com.czy.seed.mvc.wbm.config.entity.type;


import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 偏差设置
 * Created by panlc on 2017-04-28.
 */
public class Offset implements Serializable {

    private static final long serialVersionUID = -59165547077965802L;

    @Id
    private Long id;
    private String type;            //类型
    private BigDecimal minMileage;  //最小值
    private BigDecimal maxMileage;  //最大值
    private BigDecimal num;         //偏差值

    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;


    public Long getFlightTypeConfigId() {
        return flightTypeConfigId;
    }

    public void setFlightTypeConfigId(Long flightTypeConfigId) {
        this.flightTypeConfigId = flightTypeConfigId;
    }

    public FlightTypeConfig getFlightTypeConfig() {
        return flightTypeConfig;
    }

    public void setFlightTypeConfig(FlightTypeConfig flightTypeConfig) {
        this.flightTypeConfig = flightTypeConfig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMinMileage() {
        return minMileage;
    }

    public void setMinMileage(BigDecimal minMileage) {
        this.minMileage = minMileage;
    }

    public BigDecimal getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(BigDecimal maxMileage) {
        this.maxMileage = maxMileage;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }
}
