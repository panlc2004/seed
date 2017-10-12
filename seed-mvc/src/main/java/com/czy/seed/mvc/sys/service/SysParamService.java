package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.util.Res;

public interface SysParamService extends BaseService<SysParam>{
    public int updateActive(SysParam sysParam) ;

}
