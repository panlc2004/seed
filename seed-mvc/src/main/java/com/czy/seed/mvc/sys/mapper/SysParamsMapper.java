package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@AutoMapper
public interface SysParamsMapper extends BaseMapper<SysParam>{
 @Update("UPDATE SEED_SYS_PARAM a SET a.active=${sysParam.active} where a.id = ${sysParam.id}")
 int updateActive(@Param("sysParam") SysParam sysParam);

}
