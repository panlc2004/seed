package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.mapper.SysParamMapper;
import com.czy.seed.mvc.sys.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService {
    @Autowired
    SysParamMapper sysParamsMapper;

    @Override
    public int updateActive(Long id) {
        return sysParamsMapper.updateActive(id);
    }

    @Override
    public int deleteActive(Long id) {
        return sysParamsMapper.deleteActive(id);
    }
}
