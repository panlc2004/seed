/*
 * 文 件 名 : EchoConfBean
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017/6/8 17:39
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.entity;

import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.entity.type.Passenger;

import java.util.List;

/**
 * <一句话功能简介><br>
 * @Description: 此对象用于在航班类型配置回显时多个表单查询组装数据对象
 * @ClassName:EchoConfBean
 * @author  [011424]zhangyang@inner.czy.com
 * @version [版本号,2017年6月9日]
 * @see     [相关类/方法]
 * @since   [产品/模块]
 */
public class EchoConfBean {

    /**
     * 客舱信息和指数配置查询结果
     */
    private List<AboutIndexConfigBean> pcList;
    /**
     * 货舱配置信息查询结果
     */
    private List<AboutIndexConfigBean> chList;
    /**
     * 燃油参数指数配置数据回显
     */
    private List<IndexConfig> fuelList;
    /**
     * 乘务信息配置数据回显
     */
    private List<Crew> crewList;
    /**
     * 乘客信息配置数据回显
     */
    private List<Passenger> passengerList;

    public List<AboutIndexConfigBean> getPcList() {
        return pcList;
    }

    public void setPcList(List<AboutIndexConfigBean> pcList) {
        this.pcList = pcList;
    }

    public List<AboutIndexConfigBean> getChList() {
        return chList;
    }

    public void setChList(List<AboutIndexConfigBean> chList) {
        this.chList = chList;
    }

    public List<IndexConfig> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<IndexConfig> fuelList) {
        this.fuelList = fuelList;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}
