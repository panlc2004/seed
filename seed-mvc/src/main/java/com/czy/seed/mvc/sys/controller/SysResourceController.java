package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.service.SysResourceService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys/resource")
public class SysResourceController extends BaseController<SysResource> {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private ILoginUserTool loginUserTool;

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

    @RequestMapping("selectOrgTree")
    public List<SysResource> selectOrgTree() {
        return sysResourceService.selectOrgTree();
    }

    /**
     * 根据父级菜单id查找其下级菜单
     *
     * @param parentId 父级菜单id
     * @return Res
     */
    @RequestMapping("/selectListByParentId")
    public Res selectListByParentId(long parentId) {
        List<SysResource> subResources = sysResourceService.selectChildNumListByParentId(parentId);
        return Res.ok(subResources);
    }

    /**
     * 查询本目录下的所有菜单
     *
     * @return Res
     */
    @RequestMapping("/selectOwnResourceList")
    public Res selectOwnResourceList() {
        QueryParams queryParams = new QueryParams(SysResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysResourceId", loginUserTool.getLoginUser().getSysOrgId());
        List<SysResource> sysDepts = sysResourceService.selectListByParams(queryParams);
        return Res.ok(sysDepts);
    }
}
