package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by PLC on 2017/5/29.
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController {

    @RequestMapping("/findResourceForLoginUser")
    public List<SysResource> findResourceForLoginUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(principal);
        System.out.println(authorities);

        return null;
    }
}
