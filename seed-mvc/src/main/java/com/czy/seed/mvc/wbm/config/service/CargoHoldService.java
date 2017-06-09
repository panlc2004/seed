package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.CargoHold;

import java.util.List;

/**
 * Created by panlc on 2017-05-04.
 */
public interface CargoHoldService extends BaseService<CargoHold> {
    /**
     * 通过在组装的货舱数据组装指数配置项,直接插入到数据库中
     *
     * @param indexConfigBeanList 在客舱数据中组装上指数配置列表项
     * @return
     */
    void insertAsList(List<AboutIndexConfigBean> indexConfigBeanList);

    /**
     * 根据flightTypeConfigId 查询货舱信息和指数配置信息组装成对应的对象
     *
     * @param flightTypeConfigId
     * @return
     */
    List<AboutIndexConfigBean> queryList(Long flightTypeConfigId);
}
