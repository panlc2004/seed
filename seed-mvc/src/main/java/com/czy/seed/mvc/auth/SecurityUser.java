package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.mapper.SysUserRoleMapper;
import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = -53674055969551571L;

    @Transient
    private List<SysRole> roles;

    public SecurityUser(SysUser sysUser) {
        if (sysUser != null) {
            setId(sysUser.getId());
            setName(sysUser.getName());
            setEmail(sysUser.getEmail());
            setPassword(sysUser.getPassword());
            setSysOrgId(sysUser.getSysOrgId());
            setSysDeptId(sysUser.getSysDeptId());
            roles = SpringContextHelper.getBeanByType(SysUserRoleMapper.class).selectRolesByUserId(sysUser.getId());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (SysRole role : roles) {
                UserAuthority authority = new UserAuthority(role.getCode(), role.getId());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
