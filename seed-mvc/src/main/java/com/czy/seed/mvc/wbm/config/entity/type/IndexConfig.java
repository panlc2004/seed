package com.czy.seed.mvc.wbm.config.entity.type;


import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 指数设置
 * Created by panlc on 2017-05-03.
 */
public class IndexConfig implements Serializable {

    private static final long serialVersionUID = 3843705832137922386L;

    @Id
    private Long id;
    private Long flightTypeConfigId;      //航班类型
    private Integer types;          //数据类型：1.客舱参数、2.货仓参数、3.燃油参数
    private Long aircraftCabinId;   //飞机舱位关联ID
    @Transient
    private String houseName;       //货仓名/客仓名
    private BigDecimal weight;      //重量（kg）
    private BigDecimal indes;       //指数
    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;

    public Long getAircraftCabinId() {
        return aircraftCabinId;
    }

    public void setAircraftCabinId(Long aircraftCabinId) {
        this.aircraftCabinId = aircraftCabinId;
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

    public Long getFlightTypeConfigId() {
        return flightTypeConfigId;
    }

    public void setFlightTypeConfigId(Long flightTypeConfigId) {
        this.flightTypeConfigId = flightTypeConfigId;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getIndes() {
        return indes;
    }

    public void setIndes(BigDecimal indes) {
        this.indes = indes;
    }
}
