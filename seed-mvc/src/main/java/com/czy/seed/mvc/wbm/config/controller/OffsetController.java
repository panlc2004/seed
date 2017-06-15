/*
 * 文 件 名 : IndexConfigController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <指数配置控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.Offset;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.OffsetService;
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
 * @Description: <偏差配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/offset")
public class OffsetController {

    @Resource
    private OffsetService offsetServiceImpl;


    @Resource
    private FlightTypeConfigService configService;

    /**
     * 新增偏差配置
     *
     * @param offset 偏差设置参数实体对象
     * @return
     */
    @RequestMapping("/add")
    public Res add(@RequestBody Offset offset) {
        int result = offsetServiceImpl.insert(offset);
        return Res.ok(result);
    }

    /**
     * 修改偏差设置
     *
     * @param offset 偏差设置参数实体对象
     * @return
     */
    @RequestMapping("/update")
    public Res update(Offset offset) {
        int result = offsetServiceImpl.updateByPrimaryKey(offset);
        return Res.ok(result);
    }

//    @RequestMapping("/view")
//    public Offset view(Offset offset) {
//        Offset offset1 = offsetServiceImpl.selectRelativeByPrimaryKey(offset.getId());
//        if (offset1 != null)
//            offset1.setFlightTypeConfig(configService.selectByPrimaryKey(offset1.getFlightTypeConfigId()));
//        return offset1;
//    }

//    @RequestMapping("/list")
//    public List<Offset> list(Offset offset) {
//        QueryParams queryParams = new QueryParams(Offset.class);
//        String type = offset.getType();//类型
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//
//        if (type != null && !"".equals(type.trim())) {
//
//            criteria.andLike("type", "%" + type + "%");
//        }
//        List<Offset> list = offsetServiceImpl.selectListByParams(queryParams);
//        return list;
//    }

//    @RequestMapping("/del")
//    public int del(Offset offset) {
//        int result = offsetServiceImpl.deleteByPrimaryKey(offset.getId());
//        if (result > 0) {
//            return 200;
//        } else {
//            return 500;
//        }
//    }
}
