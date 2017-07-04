package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;

import java.util.List;

/**
 * Created by panlc on 2017-05-03.
 */
public interface IndexConfigService extends BaseService<IndexConfig> {

    List<IndexConfig> findIndexConfigList(String flightType, int types);

    List<IndexConfig> findIndexConfigList(Long flightTypeConfigId, int types);

    List<IndexConfig> findIndexConfigList(Long flightTypeConfigId, int types, Long aircraftCabinId);

    /**
     * 保存和修改指数配置信息,在列表集合中修改操作先直接删除列表数据,再新增列表数据
     *
     * @param indexConfigList 指数配置信息列表
     * @return
     */
    void saveAsList(List<IndexConfig> indexConfigList);
}
