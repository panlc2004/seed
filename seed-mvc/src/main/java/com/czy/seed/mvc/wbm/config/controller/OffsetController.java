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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 保存数据:当传入数据有id时，进行修改操作，无id时，进行新增操作
     *
     * @param offset 数据实体
     * @return 新增/修改数据的id值
     */
    @RequestMapping("/save")
    public Res save(@RequestBody Offset offset) {
        if (offset.getId() == null) {
            offsetServiceImpl.insert(offset);
        } else {
            offsetServiceImpl.updateSelectiveByPrimaryKey(offset);
        }
        return Res.ok(offset);
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

}
