package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.service.SysResourceService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/sys/resource");
    }

    @RequestMapping("/findResourceTreeForLoginUser")
    public Res findResourceTreeForLoginUser() {
        List<SysResource> resourceTreeForLoginUser = sysResourceService.findResourceTreeForLoginUser();
        return Res.ok(resourceTreeForLoginUser);
    }

    @RequestMapping("/selectResourceTree")
    public Res selectResourceTree() {
        List<SysResource> sysResources = sysResourceService.selectResourceTree();
        return Res.ok(sysResources);
    }

    @RequestMapping("/save")
    public Res save(@RequestBody SysResource sysResource) {
        if (sysResource.getId() == null) {
            sysResourceService.insert(sysResource);
        } else {
            sysResourceService.updateSelectiveByPrimaryKey(sysResource);
        }
        return Res.ok(sysResource.getId().toString());
    }

    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable long id) {
        sysResourceService.deleteByPrimaryKey(id);
        return Res.ok();
    }
}
