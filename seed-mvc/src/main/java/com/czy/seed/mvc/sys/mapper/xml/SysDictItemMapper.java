package com.czy.seed.mvc.sys.mapper.xml;

import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@AutoMapper
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    List<SysDictItem> insert(@Param("sysDictId") Long sysDictId);

}
