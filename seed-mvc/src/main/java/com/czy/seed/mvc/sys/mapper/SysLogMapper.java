package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;

import java.util.List;
import java.util.Map;

@AutoMapper
public interface SysLogMapper extends BaseMapper<SysLog>{
    List<SysLog> selectExtendPageByParam(Map<String, Object> params);

}
