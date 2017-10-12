package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mvc.sys.model.Log;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SysLogService extends BaseService<SysLog> {

    Page<SysLog> selectPageRelative(int pageNo, int pageLimit, final QueryParams params);
    Page<SysLog> selectPageRelativeByParam(int pageNo, int pageLimit, Log log);
}
