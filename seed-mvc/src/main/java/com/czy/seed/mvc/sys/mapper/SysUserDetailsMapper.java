package com.czy.seed.mvc.sys.mapper;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@AutoMapper
public interface SysUserDetailsMapper extends BaseMapper<SecurityUser> {

    @Select("SELECT SSU.ID,SSU.NAME,SSA.`PASSWORD`,SSA.SYS_ORG_ID,SSA.SYS_USER_ID" +
            " FROM SEED_SYS_ACCOUNT SSA INNER JOIN SEED_SYS_USER SSU" +
            " ON SSA.SYS_USER_ID = SSU.ID WHERE USERNAME=#{username}")
    SysUser selectByUsername(@Param("username") String username);

}
