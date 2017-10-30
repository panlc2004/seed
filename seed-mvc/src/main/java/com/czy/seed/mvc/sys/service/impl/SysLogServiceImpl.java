package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mvc.sys.mapper.SysLogMapper;
import com.czy.seed.mvc.sys.service.SysLogService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Page<SysLog> selectExtendPageByParam(int pageNo, int pageLimit, final Map<String, Object> params) {
        Page<SysLog> page = PageHelper.startPage(pageNo, pageLimit).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
              sysLogMapper.selectExtendPageByParam(params);
            }
        });
        return page;
    }




}
