package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.mapper.SysParamsMapper;
import com.czy.seed.mvc.sys.service.SysParamService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService {

    @Autowired
    private SysParamsMapper sysParamsMapper;

    public int updateActiveByPrimaryKey(SysParam sysParam) {
        return sysParamsMapper.updateActiveByPrimaryKey(sysParam);
    }
}
