/*
 * 文 件 名 : UnionCargoController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <联合舱位配置控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.wbm.config.entity.type.UnionCargo;
import com.czy.seed.mvc.wbm.config.service.UnionCargoService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <联合舱位配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/unionCargo")
public class UnionCargoController {

    @Resource
    private UnionCargoService unionCargoServiceImpl;

//
//    @RequestMapping("/add")
//    public Integer add(UnionCargo unionCargo) {
//        int result = unionCargoServiceImpl.insert(unionCargo);
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//
//    }
//
//    @RequestMapping("/update")
//    public Integer update(UnionCargo unionCargo) {
//        int result = unionCargoServiceImpl.updateByPrimaryKey(unionCargo);
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
//
//    @RequestMapping("/list")
//    public List<UnionCargo> list(UnionCargo unionCargo) {
//        QueryParams queryParams = new QueryParams(UnionCargo.class);
//        String unionCargoCode = unionCargo.getUnionCargoCode();
//
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//        if (unionCargoCode != null && !"".equals(unionCargoCode.trim())) {
//            criteria.andLike("unionCargoCode", "%" + unionCargoCode + "%");
//        }
//
//        List<UnionCargo> list = unionCargoServiceImpl.selectListByParams(queryParams);
//        return list;
//    }
//
//    @RequestMapping("/del")
//    public int del(UnionCargo unionCargo) {
//        int result = unionCargoServiceImpl.deleteByPrimaryKey(unionCargo.getId());
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
}
