package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@AutoMapper
public interface SysDictMapper extends BaseMapper<SysDict> {
    @Select("select id, parent_id as parentId, code, name, memo from sys_org")
    List<SysDict> selectAllOrgs();

    List<SysDict> selectChildNumListByParentId(@Param("parentId") long parentId);
}
