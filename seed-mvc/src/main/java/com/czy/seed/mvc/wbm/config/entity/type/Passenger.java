package com.czy.seed.mvc.wbm.config.entity.type;


import com.czy.seed.mvc.wbm.config.entity.type.types.PassengerType;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 乘客信息配置
 * Created by panlc on 2017-04-28.
 */
public class Passenger implements Serializable {

    private static final long serialVersionUID = -3752433582180270364L;

    @Id
    private Long id;

    private BigDecimal standardWeight; //标准重量

    private String passengerTypeCode;   //乘客类型
    @Transient
    private String nationalityCode;     //航班类型

    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;


    @Transient
    private PassengerType passengerType;

//    @Transient
//    private Nationality nationality;

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

    public String getPassengerTypeCode() {
        return passengerTypeCode;
    }

    public void setPassengerTypeCode(String passengerTypeCode) {
        this.passengerTypeCode = passengerTypeCode;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public PassengerType getPassengerType() {
        return PassengerType.valueOf(getPassengerTypeCode());
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

//    public Nationality getNationality() {
//        return Nationality.valueOf(getNationalityCode());
//    }
//
//    public void setNationality(Nationality nationality) {
//        this.nationality = nationality;
//    }

    public BigDecimal getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(BigDecimal standardWeight) {
        this.standardWeight = standardWeight;
    }

}
