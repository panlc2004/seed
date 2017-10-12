package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.entity.SysUserRole;
import com.czy.seed.mvc.sys.service.SysRoleService;
import com.czy.seed.mvc.sys.service.SysUserRoleService;
import com.czy.seed.mvc.sys.service.SysUserService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUser> {

    private static final String SALT = "seed";

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public Res insert(SysUser sysUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode("000000");
        sysUser.setPassword(encodePassword);
        return super.insert(sysUser);
    }

    /**
     * 查询用户角色信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping("selectRoleInfo/{userId}")
    public Res selectRoleInfo(@PathVariable("userId") long userId) {
        //查询所有角色信息
        QueryParams allroleQueryParams = new QueryParams(SysRole.class);
        allroleQueryParams.selectProperties("id", "name");
        List<SysRole> allRoles = sysRoleService.selectListByParams(allroleQueryParams);
        //查询已有角色
        QueryParams userRoleQueryParams = new QueryParams(SysUserRole.class);
        userRoleQueryParams.selectProperties("id", "name");
        QueryParams.Criteria userRoleCriteria = userRoleQueryParams.createCriteria();
        userRoleCriteria.andEqualTo("sysUserId", userId);
        List<SysUserRole> userRoles = sysUserRoleService.selectListByParams(userRoleQueryParams);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("allRoles", allRoles);
        resMap.put("userRoles", userRoles);
        return Res.ok(resMap);
    }

    /**
     * 保存角色
     * @param userId 用户id
     * @param roleIds 要保存的角色id数组
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public Res saveUserRoles(Long userId, Long[] roleIds) {
        sysUserRoleService.saveUserRoles(userId,roleIds);
        return Res.ok();
    }

    /**
     * 删除角色
     * @param id 主键
     * @return
     */
    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable("id") long id) {
        sysUserService.deleteByPrimaryKey(id);
        return Res.ok();
    }

}
