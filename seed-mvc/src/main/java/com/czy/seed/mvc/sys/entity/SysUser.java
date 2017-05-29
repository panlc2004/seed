package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mybatis.config.mybatis.annotations.Cache;

import java.io.Serializable;

/**
 * 用户信息
 * Created by PLC on 2017/5/23.
 */
@Cache
public class SysUser implements Serializable {

    private static final long serialVersionUID = 6022588492678547207L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private boolean enable;                 //账号是否可用
    private boolean nonLocked;       //账号是否锁定
    private boolean credentialsExpiredTime; //密码过期时间
    private boolean accountExpiredTime;     //账号过期时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(boolean nonLocked) {
        this.nonLocked = nonLocked;
    }

    public boolean isCredentialsExpiredTime() {
        return credentialsExpiredTime;
    }

    public void setCredentialsExpiredTime(boolean credentialsExpiredTime) {
        this.credentialsExpiredTime = credentialsExpiredTime;
    }

    public boolean isAccountExpiredTime() {
        return accountExpiredTime;
    }

    public void setAccountExpiredTime(boolean accountExpiredTime) {
        this.accountExpiredTime = accountExpiredTime;
    }
}
