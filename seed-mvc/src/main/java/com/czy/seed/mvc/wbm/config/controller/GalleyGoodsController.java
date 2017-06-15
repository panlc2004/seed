/*
 * 文 件 名 : GalleyGoodsController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <机供品配置控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.GalleyGoodsService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <机供品配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/galleyGoods")
public class GalleyGoodsController {

    @Resource
    private GalleyGoodsService galleyGoodsServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    /**
     * @param galleyGoods
     * @return
     */
    @RequestMapping("/add")
    public Res add(@RequestBody GalleyGoods galleyGoods) {
        galleyGoodsServiceImpl.insert(galleyGoods);
        return Res.ok();
    }

    @RequestMapping("/update")
    public Res update(GalleyGoods galleyGoods) {
        int result = galleyGoodsServiceImpl.updateByPrimaryKey(galleyGoods);
        return Res.ok(result);
    }

//    @RequestMapping("/view")
//    public GalleyGoods view(GalleyGoods galleyGoods) {
//        GalleyGoods goods = galleyGoodsServiceImpl.selectRelativeByPrimaryKey(galleyGoods.getId());
//        if (goods != null)
//            goods.setFlightTypeConfig(configService.selectByPrimaryKey(goods.getFlightTypeConfigId()));
//        return goods;
//    }

    /**
     * 批量新增机供品配置
     *
     * @param galleyGoodsList 机供品参数配置参数列表
     * @return
     */
    @RequestMapping("/addList")
    public Res addList(@RequestBody List<GalleyGoods> galleyGoodsList) {
        galleyGoodsServiceImpl.insertList(galleyGoodsList);
        return Res.ok();
    }


//    @RequestMapping("/list")
//    public List<GalleyGoods> list(GalleyGoods galleyGoods) {
//        QueryParams queryParams = new QueryParams(GalleyGoods.class);
//        String positionCode = galleyGoods.getPositionCode();
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//
//        if (positionCode != null && !"".equals(positionCode.trim())) {
//            criteria.andEqualTo("positionCode", positionCode);
//        }
//
//        List<GalleyGoods> list = galleyGoodsServiceImpl.selectListByParams(queryParams);
//        return list;
//    }
//
//    @RequestMapping("/del")
//    public int del(GalleyGoods galleyGoods) {
//        int result = galleyGoodsServiceImpl.deleteByPrimaryKey(galleyGoods.getId());
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
}
