package com.czy.seed.mvc.util;

import com.czy.seed.mvc.auth.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 登陆用户信息工具类
 * Created by 003914[panlc] on 2017-06-13.
 */
public class PrincipalUtil {
    public static SecurityUser getLoginUser() {
        SecurityUser loginUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser;
    }
}
