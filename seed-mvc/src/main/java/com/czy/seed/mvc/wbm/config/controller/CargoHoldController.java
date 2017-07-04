/*
 * 文 件 名 : CargoHoldController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <货舱信息控制器>
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
import com.czy.seed.mvc.wbm.config.service.CargoHoldService;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <货舱配置相关的增删改查功能>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/cargoHold")
public class CargoHoldController {

    @Resource
    private CargoHoldService cargoHoldServiceImpl;

    @Resource
    private FlightTypeConfigService configService;


    /**
     * 批量新增货舱配置包含货舱相关的机供品配置
     *
     * @param aboutIndexConfigBeans 货舱信息和指数配置信息相关实体参数
     * @return
     */
    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<AboutIndexConfigBean> aboutIndexConfigBeans) {
        cargoHoldServiceImpl.saveAsList(aboutIndexConfigBeans);
        return Res.ok();
    }

}
