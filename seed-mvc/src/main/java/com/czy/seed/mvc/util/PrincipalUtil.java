package com.czy.seed.mvc.util;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.auth.UserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class PrincipalUtil {

    public static SecurityUser getLoginUser() {
        SecurityUser loginUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser;
    }

    public static List<UserAuthority> getLoginUserRoles() {
        List<UserAuthority> authorities = (List<UserAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

}
