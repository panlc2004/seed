/*
 * 文 件 名 : EchoInfoBean.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <用户实体>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月9日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.entity;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightConfig;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.entity.type.Offset;

import java.util.List;
/**
 * <一句话功能简介><br>
 * @Description: 此对象用于航班架次参数配置数据回显组装
 * @ClassName:EchoInfoBean
 * @author  [011424]zhangyang@inner.czy.com
 * @version [版本号,2017年6月9日]
 * @see     [相关类/方法]
 * @since   [产品/模块]
 */
public class EchoInfoBean {
    /**
     * 机供品配置组装页
     */
    private List<GalleyGoods> galleyGoodsList;
    /**
     * 偏差设置数据对象
     */
    private Offset offsets;
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

    public Offset getOffsets() {
        return offsets;
    }

    public void setOffsets(Offset offsets) {
        this.offsets = offsets;
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
