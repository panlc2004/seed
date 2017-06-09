package com.czy.seed.mvc.wbm.config.entity.type;

import com.czy.seed.mvc.wbm.config.entity.type.types.Position;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 机供品
 * Created by panlc on 2017-04-28.
 */
public class GalleyGoods implements Serializable {

    private static final long serialVersionUID = 5625199552072037584L;

    @Id
    private Long id;
    private String positionCode;//机供品类别：前/后仓
    private BigDecimal standardWeight;           //机供品标准重量
    private BigDecimal alarmWeight;              //预警重量
    private BigDecimal standardIndex;            //指数
    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;

    @Transient
    private Position position;//机供品类别：前/后仓
    @Transient
    private BigDecimal actualWeight;

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

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return Position.valueOf(getPositionCode());
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(BigDecimal standardWeight) {
        this.standardWeight = standardWeight;
    }

    public BigDecimal getAlarmWeight() {
        return alarmWeight;
    }

    public void setAlarmWeight(BigDecimal alarmWeight) {
        this.alarmWeight = alarmWeight;
    }

    public BigDecimal getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(BigDecimal actualWeight) {
        this.actualWeight = actualWeight;
    }

    public BigDecimal getStandardIndex() {
        return standardIndex;
    }

    public void setStandardIndex(BigDecimal standardIndex) {
        this.standardIndex = standardIndex;
    }
}
