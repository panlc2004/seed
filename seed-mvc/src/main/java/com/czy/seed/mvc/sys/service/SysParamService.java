package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysParam;
import org.springframework.stereotype.Service;

@Service
public interface SysParamService extends BaseService<SysParam> {



     int updateActive(Long id);

     int deleteActive(Long id);
}
