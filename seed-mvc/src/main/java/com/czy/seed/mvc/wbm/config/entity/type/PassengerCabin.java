package com.czy.seed.mvc.wbm.config.entity.type;



import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客仓信息
 * Created by panlc on 2017-04-07.
 */
public class PassengerCabin implements Serializable {

    private static final long serialVersionUID = 1900278404461968030L;

    @Id
    private Long id;
    private String name;        //客仓名
    private Integer startRow;   //开始排数
    private Integer endRow;     //结束排数
    private Integer numLimit;   //载客人数上限
    private BigDecimal indes;   //指数

    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;

    @Transient
    private Integer adultNum;   //成人数量（不包含随行机组）
    @Transient
    private Integer childNum;   //儿童数量
    @Transient
    private Integer infantNum;  //婴儿数量


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public Integer getInfantNum() {
        return infantNum;
    }

    public void setInfantNum(Integer infantNum) {
        this.infantNum = infantNum;
    }

    public Integer getNumLimit() {
        return numLimit;
    }

    public void setNumLimit(Integer numLimit) {
        this.numLimit = numLimit;
    }

    public BigDecimal getIndes() {
        return indes;
    }

    public void setIndes(BigDecimal indes) {
        this.indes = indes;
    }

    public BigDecimal cabinIndex() {
        if (buildTobalNum() < 0 && buildTobalNum() > getNumLimit()) {  //TODO 参数可配置
            throw new IllegalArgumentException("paxNo值必须在区间[0, 76]");
        }
        return new BigDecimal(buildTobalNum()).multiply(new BigDecimal(72)).multiply(getIndes());   //TODO 72 、-0.00618修改为可配置
    }

    @Transient
    public Integer buildTobalNum() {
        return getAdultNum() + getChildNum() + getInfantNum();
    }

}
