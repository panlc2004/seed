package com.czy.seed.mvc.wbm.config.entity;/*
 * 文 件 名 : EchoInfoBean
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/6/9 15:16
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */

import com.czy.seed.mvc.wbm.config.entity.flight.FlightConfig;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.entity.type.Offset;

import java.util.List;

/**
 * 此对象用于航班架次参数配置数据回显组装
 */
public class EchoInfoBean {
    /**
     * 机供品配置组装页
     */
    private List<GalleyGoods> galleyGoodsList;
    /**
     * 偏差设置数据对象
     */
    private Offset offset;
    /**
     * 航班架次配置数据对象
     */
    private FlightConfig flightConfig;
    /**
     * 航班总体参数数据对象
     */
    private FlightInfo flightInfo;

    public List<GalleyGoods> getGalleyGoodsList() {
        return galleyGoodsList;
    }

    public void setGalleyGoodsList(List<GalleyGoods> galleyGoodsList) {
        this.galleyGoodsList = galleyGoodsList;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public FlightConfig getFlightConfig() {
        return flightConfig;
    }

    public void setFlightConfig(FlightConfig flightConfig) {
        this.flightConfig = flightConfig;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }
}
