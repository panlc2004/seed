package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.mapper.SysUserDetailsMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Comup on 2017/5/23.
 * 查询用户服务类
 */
@Service
public class SysUserDetailsService implements UserDetailsService {
    public final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private SysUserDetailsMapper sysUserDetailsMapper;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().equals("")) {
            throw new UsernameNotFoundException("username maybe is null or empty");
        }
        logger.debug("start query user {}...", username);
        SysUser sysUser = sysUserDetailsMapper.selectByUsername(username);
        SecurityUser securityUser = new SecurityUser(sysUser);
        if (securityUser == null) {
            throw new UsernameNotFoundException("User:" + username + "not found");
        }
        logger.debug("query user {} success.", username);
        return securityUser;
    }
}