package com.czy.seed.mvc.wbm.config.entity.type;

import com.czy.seed.mvc.wbm.config.entity.type.types.Position;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 乘务信息
 * Created by panlc on 2017-04-28.
 */
public class Crew implements Serializable {

    private static final long serialVersionUID = -7341128733259467934L;

    @Id
    private Long id;
    private String positionCode;//舱位
    @Transient
    private String differentCountries; //国别
    private BigDecimal weight;  //重量
    private BigDecimal standardNum;   //标准人数
    private BigDecimal standardIndex;   //重量指数
    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;


    @Transient
    private BigDecimal acturalNum;      //实际人数
    @Transient
    private Position position;  //舱位
//    @Transient
//    private Nationality nationality;    //国别


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

    public String getDifferentCountries() {
        return differentCountries;
    }

    public void setDifferentCountries(String differentCountries) {
        this.differentCountries = differentCountries;
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

//    public Nationality getNationality() {
//        return Nationality.valueOf(getDifferentCountries());
//    }
//
//    public void setNationality(Nationality nationality) {
//        this.nationality = nationality;
//    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(BigDecimal standardNum) {
        this.standardNum = standardNum;
    }

    public BigDecimal getActuralNum() {
        return acturalNum;
    }

    public void setActuralNum(BigDecimal acturalNum) {
        this.acturalNum = acturalNum;
    }

    public BigDecimal getStandardIndex() {
        return standardIndex;
    }

    public void setStandardIndex(BigDecimal standardIndex) {
        this.standardIndex = standardIndex;
    }

}
