package com.czy.seed.mvc.auth.util;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.auth.UserAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 登陆用户信息工具类
 * Created by 003914[panlc] on 2017-06-13.
 */
public class PrincipalUtil {

    /**
     * 获取登陆用户信息
     * @return SecurityUser
     */
    public static SecurityUser getLoginUser() {
        SecurityUser loginUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser;
    }

    /**
     * 获取登陆用户角色
     * @return SecurityUser
     */
    public static List<UserAuthority> getLoginUserRoles() {
        List<UserAuthority> authorities = (List<UserAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

}
