package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mvc.sys.mapper.SysLogMapper;
import com.czy.seed.mvc.sys.model.Log;
import com.czy.seed.mvc.sys.service.SysLogService;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    public Page<SysLog> selectPageRelative(int pageNo, int pageLimit, final QueryParams params) {
        Page<SysLog> page = PageHelper.startPage(pageNo, pageLimit).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
              sysLogMapper.selectListRelative(params);
            }
        });
        return page;
    }

    @Override
    public Page<SysLog> selectPageRelativeByParam(int pageNo, int pageLimit, Log log) {
        return null;
    }


}
