package com.czy.seed.mvc.wbm.config.entity.type;

import com.czy.seed.mvc.wbm.math.formula.IllegalParamException;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 货仓信息
 * Created by panlc on 2017-04-07.
 */
public class CargoHold implements Serializable {

    private static final long serialVersionUID = -7690670191919811375L;

    @Id
    private Long id;
    private String code;                //货仓代码
    private String name;                //货仓名称
    @Transient
    private String cargoTypeName;        //货物类型

    private BigDecimal maxLimit;        //载重上限
    private BigDecimal weightIndex;           //重量指数
    private Long flightTypeConfigId;                   //航班机型 ID

    @Transient
    @One2One(columns = "flight_Type_Config_Id=id")
    private FlightTypeConfig flightTypeConfig;


    @Transient
    private List<UnionCargo> unionCargoList;    //联合仓位List
    @Transient
    private BigDecimal cargoWeight;             //货物重量
//    @Transient
//    private CargoType cargoType;        //货物类型

    @Transient
    private BigDecimal cWeight = BigDecimal.ZERO; // 货仓重量
    @Transient
    private BigDecimal bWeight = BigDecimal.ZERO; //包裹重量
    @Transient
    private BigDecimal mWeight = BigDecimal.ZERO; //邮件总量

    public BigDecimal getcWeight() {
        return cWeight;
    }

    public void setcWeight(BigDecimal cWeight) {
        this.cWeight = cWeight;
    }

    public BigDecimal getbWeight() {
        return bWeight;
    }

    public void setbWeight(BigDecimal bWeight) {
        this.bWeight = bWeight;
    }

    public BigDecimal getmWeight() {
        return mWeight;
    }

    public void setmWeight(BigDecimal mWeight) {
        this.mWeight = mWeight;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UnionCargo> getUnionCargoList() {
        return unionCargoList;
    }

    public void setUnionCargoList(List<UnionCargo> unionCargoList) {
        this.unionCargoList = unionCargoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    @Transient
//    public CargoType getCargoType() {
//        return CargoType.valueOf(getCargoTypeName());
//    }
//    @Transient
//    public void setCargoType(CargoType cargoType) {
//        this.cargoType = cargoType;
//    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public BigDecimal getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    public BigDecimal getWeightIndex() {
        return weightIndex;
    }

    public void setWeightIndex(BigDecimal weightIndex) {
        this.weightIndex = weightIndex;
    }

    public String getCargoTypeName() {
        return cargoTypeName;
    }

    public void setCargoTypeName(String cargoTypeName) {
        this.cargoTypeName = cargoTypeName;
    }

    /**
     * 计算货仓货重系数
     *
     * @return BigDecimal
     * @throws IllegalParamException 参数错误异常
     */
    public BigDecimal cargoIndex() {
        if (cargoWeight == null) {
            throw new IllegalArgumentException("参数值不能为null");
        }
        if (cargoWeight.compareTo(BigDecimal.ZERO) < 0 && cargoWeight.compareTo(maxLimit) > 0) {     //TODO 3402 参数配置化
            throw new IllegalArgumentException("货仓" + getName() + "配重值必须在区间[0,3402]中");
        }
        return cargoWeight.multiply(weightIndex);   //TODO 参数配置化
    }
}
