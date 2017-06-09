package com.czy.seed.mvc.wbm.config.entity.flight;



import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 航班总体参数实体类
 * Created by panlc on 2017-04-14.
 */
public class FlightInfo implements Serializable {

    private static final long serialVersionUID = -3095755264671920471L;

    @Id
    private Long id;
    private String flightNo;                            //航班号
    private String segment;                             //航段
    //private String flightType;
    private Long flightConfigId;                      //航班架次参数id
    //private Long flightTypeConfigId;                   //航班机型 ID

    @One2One(columns = "flight_Config_Id=id")
    private FlightConfig flightConfig;

    @Transient
    private FlightTypeConfig flightTypeConfig;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

//    public Long getFlightTypeConfigId() {
//        return flightTypeConfigId;
//    }
//
//    public void setFlightTypeConfigId(Long flightTypeConfigId) {
//        this.flightTypeConfigId = flightTypeConfigId;
//    }


//    public String getFlightType() {
//        return flightType;
//    }
//
//    public void setFlightType(String flightType) {
//        this.flightType = flightType;
//    }

    public Long getFlightConfigId() {
        return flightConfigId;
    }

    public void setFlightConfigId(Long flightConfigId) {
        this.flightConfigId = flightConfigId;
    }

    public FlightConfig getFlightConfig() {
        return flightConfig;
    }

    public void setFlightConfig(FlightConfig flightConfig) {
        this.flightConfig = flightConfig;
    }

    public FlightTypeConfig getFlightTypeConfig() {
        return flightTypeConfig;
    }

    public void setFlightTypeConfig(FlightTypeConfig flightTypeConfig) {
        this.flightTypeConfig = flightTypeConfig;
    }
}
