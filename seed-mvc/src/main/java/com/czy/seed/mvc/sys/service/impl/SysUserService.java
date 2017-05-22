package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.sys.entity.SysUser;

/**
 * Created by PLC on 2017/5/23.
 */
public interface SysUserService {
    SysUser findUserByUsername(String username);
}
