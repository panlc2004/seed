package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mvc.sys.entity.SysUserRole;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@AutoMapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Select("SELECT * FROM SEED_SYS_ROLE SSR WHERE EXISTS ( SELECT SSUR.SYS_ROLE_ID FROM SEED_SYS_USER_ROLE SSUR" +
            " WHERE SSR.ID = SSUR.SYS_ROLE_ID AND SSUR.SYS_USER_ID=#{userId})")
    List<SysRole> selectRolesByUserId(@Param("userId") Long userId);

}
