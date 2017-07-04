/*
 * 文 件 名 : PassengerCabinController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <客舱配置控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.PassengerCabinService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <客舱配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/passengerCabin")
public class PassengerCabinController {

    @Resource
    private PassengerCabinService passengerCabinServiceImpl;

    @Resource
    private FlightTypeConfigService configService;


    /**
     * 批量添加客舱配置和相关的指数配置
     *
     * @param aboutIndexConfigBeans 客舱配置和相关的指数配置列表
     * @return
     */
    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<AboutIndexConfigBean> aboutIndexConfigBeans) {
        passengerCabinServiceImpl.saveAsList(aboutIndexConfigBeans);
        return Res.ok();
    }


    /**
     * 根据航班类型ID 查询客舱配置相关信息
     *
     * @param flightTypeConfigId 航班类型ID
     * @return
     */
    @RequestMapping("/queryList")
    public Res queryList(@RequestParam Long flightTypeConfigId) {
        List<AboutIndexConfigBean> aboutIndexConfigBeans = passengerCabinServiceImpl.queryList(flightTypeConfigId);
        return Res.ok(aboutIndexConfigBeans);
    }

}
