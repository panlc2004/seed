package com.czy.seed.mvc.util;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.auth.UserAuthority;

import java.util.List;

/**
 * Created by 003914[panlc] on 2017-08-16.
 */
public interface ILoginUserTool {

    SecurityUser getLoginUser();

     List<UserAuthority> getLoginUserRoles();

}
