package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;

@AutoMapper
public interface SysParamMapper extends BaseMapper<SysParam> {
     int updateActive(Long id);
     int deleteActive(Long id);
}
