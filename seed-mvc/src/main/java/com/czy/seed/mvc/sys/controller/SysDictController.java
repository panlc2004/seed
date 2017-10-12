package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.service.SysDictService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict>  {
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private ILoginUserTool loginUserTool;

    /**
     * 根据父级部门id查找其下级部门
     * @param parentId 父级部门id
     * @return
     */
    @RequestMapping("/selectListByParentId")
    public Res selectListByParentId(long parentId) {
        List<SysDict> subDicts = sysDictService.selectChildNumListByParentId(parentId);
        return Res.ok(subDicts);
    }

    /**
     * 查询本组织下的所有部门
     * @return
     */
    @RequestMapping("/selectOwnOrgDictList")
    public Res selectOwnOrgDictList() {
        QueryParams queryParams = new QueryParams(SysDict.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysOrgId", loginUserTool.getLoginUser().getSysOrgId());
        List<SysDict> sysDicts = sysDictService.selectListByParams(queryParams);
        return Res.ok(sysDicts);
    }


    @RequestMapping("selectOrgTree")
    public List<SysDict> selectOrgTree() {
        return sysDictService.selectOrgTree();
    }

    @RequestMapping("/loadData")
    public SysDict loadData(long id) {
        return sysDictService.selectByPrimaryKey(id);
    }

    @RequestMapping("/deleteOrg")
    public Res deleteOrg(long id) {
        QueryParams params = new QueryParams(SysDict.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("parentId", id);
        int count = sysDictService.selectCountByParams(params);
        if (count > 0) {
            return Res.error("当前机构有下属机构，无法删除");
        }
        sysDictService.deleteByPrimaryKey(id);
        return Res.ok();
    }

//
//    @AutoLog("新增/修改数据字典项")
//
////        @PreAuthorize("hasAnyAuthority('admin')")
//    @PreAuthorize("authenticated and hasPermission('hello', 'view')")


}
