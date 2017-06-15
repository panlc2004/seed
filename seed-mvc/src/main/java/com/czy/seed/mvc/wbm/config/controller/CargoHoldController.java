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
import com.czy.seed.mvc.wbm.config.entity.type.CargoHold;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.service.CargoHoldService;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mybatis.base.QueryParams;
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

//    /**
//     * 新增货舱配置
//     *
//     * @param cargoHold
//     * @return
//     */
//    @RequestMapping("/add")
//    public Res add(CargoHold cargoHold) {
//        int result = cargoHoldServiceImpl.insert(cargoHold);
//        return Res.ok(result);
//    }

    /**
     * 批量新增货舱配置包含货舱相关的机供品配置
     *
     * @param aboutIndexConfigBeans 货舱信息和指数配置信息相关实体参数
     * @return
     */
    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<AboutIndexConfigBean> aboutIndexConfigBeans) {
        cargoHoldServiceImpl.insertAsList(aboutIndexConfigBeans);
        return Res.ok();
    }

//
//    @RequestMapping("/view")
//    public CargoHold view(CargoHold cargoHold) {
//        CargoHold hold = cargoHoldServiceImpl.selectRelativeByPrimaryKey(cargoHold.getId());
//        if (hold != null)
//            hold.setFlightTypeConfig(configService.selectByPrimaryKey(hold.getFlightTypeConfigId()));
//        return hold;
//    }

    /**
     * 货舱配置信息修改.
     *
     * @param cargoHold 货舱配置实体参数
     * @return
     */
    @RequestMapping("/update")
    public Res update(CargoHold cargoHold) {
        int result = cargoHoldServiceImpl.updateByPrimaryKey(cargoHold);
        return Res.ok(result);
    }

//    /**
//     * 货舱配置列表
//     * @param cargoHold
//     * @return
//     */
//    @RequestMapping("/list")
//    public List<CargoHold> list(CargoHold cargoHold) {
//        QueryParams queryParams = new QueryParams(CargoHold.class);
//        String typeName = cargoHold.getCargoTypeName();
//        String code = cargoHold.getCode();
//        String name = cargoHold.getName();
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//        if (typeName != null && !"".equals(typeName.trim())) {
//            criteria.andEqualTo("cargoTypeName", typeName);
//        }
//
//        if (code != null && !"".equals(code.trim())) {
//            criteria.andLike("code", "%" + code + "%");
//        }
//        if (name != null && !"".equals(name.trim())) {
//            criteria.andLike("name", "%" + name + "%");
//        }
//
//        List<CargoHold> list = cargoHoldServiceImpl.selectListByParams(queryParams);
//        return list;
//    }
//
//    @RequestMapping("/del")
//    public int del(CargoHold cargoHold) {
//        int result = cargoHoldServiceImpl.deleteByPrimaryKey(cargoHold.getId());
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
}
