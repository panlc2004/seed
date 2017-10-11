package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.mapper.SysParamsMapper;
import com.czy.seed.mvc.sys.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService {

    @Autowired
    private SysParamsMapper sysParamsMapper;

    /**
     * 该功能用于激活用户
     * @param id     主键
     * @param active 是否激活
     * @return  无返回值
     */
    @Override
    public void updateActiveByPrimaryKey(long id, long active) {
        sysParamsMapper.updateActiveByPrimaryKey(id, active);
    }
}
