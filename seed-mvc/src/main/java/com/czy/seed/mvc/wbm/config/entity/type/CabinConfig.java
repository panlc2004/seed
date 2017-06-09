package com.czy.seed.mvc.wbm.config.entity.type;/*
 * 文 件 名 : CabinConfig
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/5/27 14:49
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */


import java.io.Serializable;
import java.math.BigDecimal;

public class CabinConfig implements Serializable {

    private Long flightId;//航班号主键Id

    private Integer adultOa; //成人OA 舱

    private Integer adultOb; //成人OB 舱
    private Integer adultOc; //成人OC 舱

    private Integer childOa; //儿童OA 舱
    private Integer childOb; //儿童OB 舱
    private Integer childOc; //儿童OC 舱

    private Integer infantOa; //婴儿OA 舱
    private Integer infantOb; //婴儿OB 舱
    private Integer infantOc; //婴儿OC 舱

    private BigDecimal cargoOne; //货舱
    private BigDecimal cargoThree;
    private BigDecimal cargoFour;
    private BigDecimal cargoFive;
    private BigDecimal cargoFs;

    private BigDecimal bagOne; // 包裹
    private BigDecimal bagThree;
    private BigDecimal bagFour;
    private BigDecimal bagFive;
    private BigDecimal bagFs;

    private BigDecimal mailOne; //邮件
    private BigDecimal mailThree;
    private BigDecimal mailFour;
    private BigDecimal mailFive;
    private BigDecimal mailFs;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Integer getAdultOa() {
        return adultOa;
    }

    public void setAdultOa(Integer adultOa) {
        this.adultOa = adultOa;
    }

    public Integer getAdultOb() {
        return adultOb;
    }

    public void setAdultOb(Integer adultOb) {
        this.adultOb = adultOb;
    }

    public Integer getAdultOc() {
        return adultOc;
    }

    public void setAdultOc(Integer adultOc) {
        this.adultOc = adultOc;
    }

    public Integer getChildOa() {
        return childOa;
    }

    public void setChildOa(Integer childOa) {
        this.childOa = childOa;
    }

    public Integer getChildOb() {
        return childOb;
    }

    public void setChildOb(Integer childOb) {
        this.childOb = childOb;
    }

    public Integer getChildOc() {
        return childOc;
    }

    public void setChildOc(Integer childOc) {
        this.childOc = childOc;
    }

    public Integer getInfantOa() {
        return infantOa;
    }

    public void setInfantOa(Integer infantOa) {
        this.infantOa = infantOa;
    }

    public Integer getInfantOb() {
        return infantOb;
    }

    public void setInfantOb(Integer infantOb) {
        this.infantOb = infantOb;
    }

    public Integer getInfantOc() {
        return infantOc;
    }

    public void setInfantOc(Integer infantOc) {
        this.infantOc = infantOc;
    }

    public BigDecimal getCargoOne() {
        return cargoOne;
    }

    public void setCargoOne(BigDecimal cargoOne) {
        this.cargoOne = cargoOne;
    }

    public BigDecimal getCargoThree() {
        return cargoThree;
    }

    public void setCargoThree(BigDecimal cargoThree) {
        this.cargoThree = cargoThree;
    }

    public BigDecimal getCargoFour() {
        return cargoFour;
    }

    public void setCargoFour(BigDecimal cargoFour) {
        this.cargoFour = cargoFour;
    }

    public BigDecimal getCargoFive() {
        return cargoFive;
    }

    public void setCargoFive(BigDecimal cargoFive) {
        this.cargoFive = cargoFive;
    }

    public BigDecimal getCargoFs() {
        return cargoFs;
    }

    public void setCargoFs(BigDecimal cargoFs) {
        this.cargoFs = cargoFs;
    }

    public BigDecimal getBagOne() {
        return bagOne;
    }

    public void setBagOne(BigDecimal bagOne) {
        this.bagOne = bagOne;
    }

    public BigDecimal getBagThree() {
        return bagThree;
    }

    public void setBagThree(BigDecimal bagThree) {
        this.bagThree = bagThree;
    }

    public BigDecimal getBagFour() {
        return bagFour;
    }

    public void setBagFour(BigDecimal bagFour) {
        this.bagFour = bagFour;
    }

    public BigDecimal getBagFive() {
        return bagFive;
    }

    public void setBagFive(BigDecimal bagFive) {
        this.bagFive = bagFive;
    }

    public BigDecimal getBagFs() {
        return bagFs;
    }

    public void setBagFs(BigDecimal bagFs) {
        this.bagFs = bagFs;
    }

    public BigDecimal getMailOne() {
        return mailOne;
    }

    public void setMailOne(BigDecimal mailOne) {
        this.mailOne = mailOne;
    }

    public BigDecimal getMailThree() {
        return mailThree;
    }

    public void setMailThree(BigDecimal mailThree) {
        this.mailThree = mailThree;
    }

    public BigDecimal getMailFour() {
        return mailFour;
    }

    public void setMailFour(BigDecimal mailFour) {
        this.mailFour = mailFour;
    }

    public BigDecimal getMailFive() {
        return mailFive;
    }

    public void setMailFive(BigDecimal mailFive) {
        this.mailFive = mailFive;
    }

    public BigDecimal getMailFs() {
        return mailFs;
    }

    public void setMailFs(BigDecimal mailFs) {
        this.mailFs = mailFs;
    }
}
