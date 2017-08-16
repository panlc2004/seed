package com.czy.seed.mvc.util;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.auth.UserAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserTool implements ILoginUserTool {

    public SecurityUser getLoginUser() {
        SecurityUser loginUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser;
    }

    public List<UserAuthority> getLoginUserRoles() {
        List<UserAuthority> authorities = (List<UserAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

}
