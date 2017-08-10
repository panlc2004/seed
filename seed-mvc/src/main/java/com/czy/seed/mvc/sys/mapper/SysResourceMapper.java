package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@AutoMapper
public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("select sr.*, sr.parent_id as parentId, sr.order_by as orderBy from SEED_SYS_RESOURCE sr where EXISTS " +
            "(select srr.id from SEED_SYS_ROLE_RESOURCE srr where sr.id = srr.SYS_RESOURCE_ID and srr.SYS_ROLE_ID in ${userId})")
    List<SysResource> findResourceForLoginUser(@Param("userId") String roleIds);

    @Select("select id,parent_id as parentId, types, code, name, url, order_by as orderBy, icon " +
            "from SEED_SYS_RESOURCE")
    List<SysResource> selectListAll();

}
