package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@AutoMapper
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

/*@Select("SELECT SYS_RESOURCE_ID FROM seed_sys_role_resource WHERE SYS_ROLE_ID = ${sysRoleId}")
    List<Long> getCheck(@Param("sysRoleId") long sysRoleId);*/
}
