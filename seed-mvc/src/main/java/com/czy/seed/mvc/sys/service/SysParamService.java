package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysParam;


public interface SysParamService extends BaseService<SysParam> {

    int updateActiveByPrimaryKey(SysParam sysParam);
}
