package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysOrg;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@AutoMapper
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    @Select("select id, parent_id as parentId, code, name, memo from sys_org")
    List<SysOrg> selectAllOrgs();
}
