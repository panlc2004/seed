package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.mapper.SysUserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDetailsMapper sysUserDetailsMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().equals("")) {
            throw new UsernameNotFoundException("username maybe is null or empty");
        }
        SysUser sysUser = sysUserDetailsMapper.selectByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("User:" + username + "not found");
        }
        SecurityUser securityUser = new SecurityUser(sysUser);
        return securityUser;
    }
}