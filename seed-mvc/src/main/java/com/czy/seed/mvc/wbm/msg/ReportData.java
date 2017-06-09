package com.czy.seed.mvc.wbm.msg;



import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.CabinConfig;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 舱单生成参数封装类
 * Created by panlc on 2017-04-07.
 */
public class ReportData implements Serializable {

    private static final long serialVersionUID = 5469742731757342130L;

    private String header;  //报文头
    private String pre;     //制作人
    private String app;     //审核人
    private String sig;     //签收人
    private String data;    //制作日期
    private String verson;  //版本号
    private FlightInfo flightInfo;  //航班信息
    private Math math;  //公式计算结果

    private BigDecimal cockpitActuralNum; //飞行员人数

    private BigDecimal serviceActuralNum;//机务人员人数

    private BigDecimal midActuralNum;//中舱人数

    private BigDecimal aftActualWeight; //后舱重量

    private BigDecimal fwdActualWeight; // 前舱总量

    private CabinConfig cabinConfig;// 舱位相关信息

    private Integer adultNum; //成人数量
    private Integer childNum; //小孩数量
    private Integer infantNum;//婴儿数量

    private BigDecimal cWeight; //货物信息重量

    private BigDecimal bWeight; //行李重量

    private BigDecimal mWeight; //邮件重量



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

    public CabinConfig getCabinConfig() {
        return cabinConfig;
    }

    public void setCabinConfig(CabinConfig cabinConfig) {
        this.cabinConfig = cabinConfig;
    }

    public BigDecimal getAftActualWeight() {
        return aftActualWeight;
    }

    public void setAftActualWeight(BigDecimal aftActualWeight) {
        this.aftActualWeight = aftActualWeight;
    }

    public BigDecimal getFwdActualWeight() {
        return fwdActualWeight;
    }

    public void setFwdActualWeight(BigDecimal fwdActualWeight) {
        this.fwdActualWeight = fwdActualWeight;
    }

    public BigDecimal getCockpitActuralNum() {
        return cockpitActuralNum;
    }

    public void setCockpitActuralNum(BigDecimal cockpitActuralNum) {
        this.cockpitActuralNum = cockpitActuralNum;
    }

    public BigDecimal getServiceActuralNum() {
        return serviceActuralNum;
    }

    public void setServiceActuralNum(BigDecimal serviceActuralNum) {
        this.serviceActuralNum = serviceActuralNum;
    }

    public BigDecimal getMidActuralNum() {
        return midActuralNum;
    }

    public void setMidActuralNum(BigDecimal midActuralNum) {
        this.midActuralNum = midActuralNum;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVerson() {
        return verson;
    }

    public void setVerson(String verson) {
        this.verson = verson;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }

    public Math getMath() {
        return math;
    }

    public void setMath(Math math) {
        this.math = math;
    }
}
