package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@AutoMapper
public interface SysParamsMapper extends BaseMapper<SysParam> {
    @Update("UPDATE\n" +
            "\tseed_sys_param\n" +
            "SET active = ${active}\n" +
            "WHERE\n" +
            "\tid = ${id}")
     void updateActiveByPrimaryKey(@Param("id") long id, @Param("active") long active);

}
