package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;

import java.util.List;

/**
 * Created by panlc on 2017-05-03.
 */
public interface IndexConfigService  extends BaseService<IndexConfig> {

    List<IndexConfig> findIndexConfigList(String flightType, int types);

    List<IndexConfig> findIndexConfigList(Long flightTypeConfigId, int types);

}
