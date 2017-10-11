package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@AutoMapper
public interface SysParamsMapper extends BaseMapper<SysParam> {

    @Update("update SEED_SYS_PARAM ssp set ssp.active=${sysParam.active} where ssp.id=${sysParam.id}" )
    int updateActiveByPrimaryKey(@Param("sysParam") SysParam sysParam);
}
