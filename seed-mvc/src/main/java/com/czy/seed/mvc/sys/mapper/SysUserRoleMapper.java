package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Comup on 2017/5/23.
 */
@AutoMapper
public interface SysUserRoleMapper extends BaseMapper<SysRole> {
    @Select("select * from sys_role where exists ( select sys_role_id from sys_user_role where sys_user_id=#{userId})")
    List<SysRole> selectRolesByUserId(@Param("userId") Long userId);
}
