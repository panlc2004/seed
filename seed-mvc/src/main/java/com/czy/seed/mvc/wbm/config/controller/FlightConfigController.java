/*
 * 文 件 名 : FlightConfigController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <航班参数配置控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;


import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.EchoInfoBean;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightConfig;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.entity.type.Offset;
import com.czy.seed.mvc.wbm.config.service.*;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <航班配置页面跳转控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/flightCfg")
public class FlightConfigController {

    @Resource
    private FlightConfigService flightConfigServiceImpl;

    @Resource
    private FlightTypeConfigService configService;
    @Resource
    private GalleyGoodsService galleyGoodsServiceImpl;
    @Resource
    private OffsetService offsetServiceImpl;
    @Resource
    private FlightInfoService flightInfoServiceImpl;

    /**
     * 新增航班配置参数
     *
     * @param flightConfig
     * @return
     */
    @RequestMapping("/add")
    public Res add(@RequestBody FlightConfig flightConfig) {
        flightConfigServiceImpl.insert(flightConfig);
        return Res.ok(flightConfig);
    }

//    @RequestMapping("/view")
//    public FlightConfig view(FlightConfig flightConfig) {
//        FlightConfig config = flightConfigServiceImpl.selectRelativeByPrimaryKey(flightConfig.getId());
//        if (config != null)
//            config.setFlightTypeConfig(configService.selectByPrimaryKey(config.getFlightTypeConfigId()));
//        return config;
//    }

    /**
     * 修改航班配置参数
     *
     * @param flightConfig
     * @return
     */
    @RequestMapping("/update")
    public Res update(FlightConfig flightConfig) {
        int result = flightConfigServiceImpl.updateByPrimaryKey(flightConfig);
        return Res.ok(result);
    }

//    @RequestMapping("/list")
//    public List<FlightConfig> list(FlightConfig flightConfig) {
//        QueryParams queryParams = new QueryParams(FlightConfig.class);
//        String num = flightConfig.getNum();//出厂序号
//        String msn = flightConfig.getMsn();//机号
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//        if (num != null && !"".equals(num.trim())) {
//            criteria.andLike("num", "%" + num + "%");
//        }
//        if (msn != null && !"".equals(msn.trim())) {
//
//            criteria.andLike("msn", "%" + msn + "%");
//        }
//        List<FlightConfig> list = flightConfigServiceImpl.selectListByParams(queryParams);
//        return list;
//    }


    /**
     * 根据航班类型主键ID 查询(航班架次参数配置,航班配置,偏差设置配置,机供品配置设置)
     * 组装成一个统一的对象用于回显航班类型配置
     *
     * @param flightTypeConfigId 航班类型主键ID
     * @return
     */
    @RequestMapping("/queryFlightConfList")
    public Res queryFlightConfList(@RequestParam Long flightTypeConfigId) {
        //航班架次参数配置
        QueryParams fcParams = new QueryParams(FlightConfig.class);
        QueryParams.Criteria fcParamsCriteria = fcParams.createCriteria();
        fcParamsCriteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        FlightConfig flightConfig = flightConfigServiceImpl.selectOneByParams(fcParams);
        FlightInfo flightInfo = null;
        if (flightConfig != null) {
            //航班总体参数配置
            QueryParams fiParams = new QueryParams(FlightInfo.class);
            QueryParams.Criteria fiParamsCriteria = fiParams.createCriteria();
            fiParamsCriteria.andEqualTo("flightConfigId", flightConfig.getId());
            flightInfo = flightInfoServiceImpl.selectOneByParams(fiParams);
        }
        //偏差设置配置
        QueryParams osParams = new QueryParams(Offset.class);
        QueryParams.Criteria osParamsCriteria = osParams.createCriteria();
        osParamsCriteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        Offset offset = offsetServiceImpl.selectOneByParams(osParams);
        //机供品
        QueryParams ggParams = new QueryParams(GalleyGoods.class);
        QueryParams.Criteria ggParamsCriteria = ggParams.createCriteria();
        ggParamsCriteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        List<GalleyGoods> galleyGoods = galleyGoodsServiceImpl.selectListByParams(ggParams);
        //组装回显数据
        EchoInfoBean bean = new EchoInfoBean();
        bean.setFlightConfig(flightConfig);
        bean.setFlightInfo(flightInfo);
        bean.setGalleyGoodsList(galleyGoods);
        bean.setOffsets(offset);
        return Res.ok(bean);
    }


//    @RequestMapping("/del")
//    public int del(FlightConfig flightConfig) {
//        int result = flightConfigServiceImpl.deleteByPrimaryKey(flightConfig.getId());
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
}
