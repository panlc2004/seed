package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.service.SysDictService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict> {
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private ILoginUserTool loginUserTool;

    /**
     * 根据父级部门id查找其下级部门
     * @param parentId 父级部门id
     * @return
     */
    @RequestMapping("/selectListByParentId/{parentId}/{code}")
    public Res selectListByParentId(@PathVariable long parentId,@PathVariable String code) {
        if("null".equals(code)) {
            code = null;
        }
        List<SysDict> sysDicts = sysDictService.selectChildNumListByParentId(parentId,code);
        return Res.ok(sysDicts);
    }

    /**
     * 删除数据及期所有子级数据
     * @param id
     * @return
     */
    @RequestMapping("/deleteChildByPrimaryKey/{id}")
    public Res deleteChildByPrimaryKey(@PathVariable long id) {
        sysDictService.deleteChildByPrimaryKey(id);
        return Res.ok();
    }

    /**
     * 查询本组织下的所有部门
     * @return
     */
    @RequestMapping("/selectOwnOrgDeptList")
    public Res selectOwnOrgDeptList() {
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
}