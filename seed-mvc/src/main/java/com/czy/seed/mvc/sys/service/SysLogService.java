package com.czy.seed.mvc.sys.service;


import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.github.pagehelper.Page;
import java.util.Map;

public interface SysLogService extends BaseService<SysLog> {

    Page<SysLog> selectExtendPageByParam(int pageNo, int pageLimit, final Map<String, Object> params);

}
