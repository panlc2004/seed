package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.mapper.SysUserMapper;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().equals("")) {
            throw new UsernameNotFoundException("username maybe is null or empty");
        }
        QueryParams params = new QueryParams(SysUser.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("username", username);
        SysUser sysUser = sysUserMapper.selectOneByParams(params);
        if (sysUser == null) {
            throw new UsernameNotFoundException("User:" + username + "not found");
        }
        SecurityUser securityUser = new SecurityUser(sysUser);
        return securityUser;
    }

}