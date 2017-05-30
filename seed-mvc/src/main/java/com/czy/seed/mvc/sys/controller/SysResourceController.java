package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PLC on 2017/5/29.
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 查找登陆用户的资源树
     * @return
     */
    @RequestMapping("/findResourceTreeForLoginUser")
    public List<SysResource> findResourceTreeForLoginUser() {
        return sysResourceService.selectResourceTree();
    }
}
