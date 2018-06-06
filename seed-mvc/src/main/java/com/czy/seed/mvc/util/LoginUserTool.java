package com.czy.seed.mvc.util;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.auth.UserAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserTool implements ILoginUserTool {

    public SecurityUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        SecurityUser loginUser = (SecurityUser) authentication.getPrincipal();
        return loginUser;
    }

    public List<UserAuthority> getLoginUserRoles() {
        List<UserAuthority> authorities = (List<UserAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

}
