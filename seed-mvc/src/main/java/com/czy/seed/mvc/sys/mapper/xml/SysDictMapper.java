package com.czy.seed.mvc.sys.mapper.xml;

import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@AutoMapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> selectChildNumListByParentId(@Param("parentId") long parentId, @Param("code") String code);

    List<SysDict>  selectAllOrgs();
}
