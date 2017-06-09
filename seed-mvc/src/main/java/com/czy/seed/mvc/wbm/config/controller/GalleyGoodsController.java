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
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/galleyGoods")
public class GalleyGoodsController {

    @Resource
    private GalleyGoodsService galleyGoodsServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Res add(@RequestBody GalleyGoods galleyGoods) {
        galleyGoodsServiceImpl.insert(galleyGoods);
        return Res.ok();
    }

    @RequestMapping("/update")
    public Integer update(GalleyGoods galleyGoods) {
        int result = galleyGoodsServiceImpl.updateByPrimaryKey(galleyGoods);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public GalleyGoods view(GalleyGoods galleyGoods) {
        GalleyGoods goods = galleyGoodsServiceImpl.selectRelativeByPrimaryKey(galleyGoods.getId());
        if (goods != null)
            goods.setFlightTypeConfig(configService.selectByPrimaryKey(goods.getFlightTypeConfigId()));
        return goods;
    }


    @RequestMapping("/addList")
    public Res addList(@RequestBody List<GalleyGoods> galleyGoodsList) {
        galleyGoodsServiceImpl.insertList(galleyGoodsList);
        return Res.ok();
    }


    @RequestMapping("/list")
    public List<GalleyGoods> list(GalleyGoods galleyGoods) {
        QueryParams queryParams = new QueryParams(GalleyGoods.class);
        String positionCode = galleyGoods.getPositionCode();
        QueryParams.Criteria criteria = queryParams.createCriteria();

        if (positionCode != null && !"".equals(positionCode.trim())) {
            criteria.andEqualTo("positionCode", positionCode);
        }

        List<GalleyGoods> list = galleyGoodsServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(GalleyGoods galleyGoods) {
        int result = galleyGoodsServiceImpl.deleteByPrimaryKey(galleyGoods.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
