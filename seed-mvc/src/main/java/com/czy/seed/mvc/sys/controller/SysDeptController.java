package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.service.SysDeptService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sys/dept")
public class SysDeptController extends BaseController<SysDept> {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private ILoginUserTool loginUserTool;

    /**
     * 根据父级部门id查找其下级部门
     * @param parentId 父级部门id
     * @return
     */
    @RequestMapping("/selectListByParentId")
    public Res selectListByParentId(long parentId) {
        List<SysDept> subDepts = sysDeptService.selectChildNumListByParentId(parentId);
        return Res.ok(subDepts);
    }

    /**
     * 查询本组织下的所有部门
     * @return
     */
    @RequestMapping("/selectOwnOrgDeptList")
    public Res selectOwnOrgDeptList() {
        QueryParams queryParams = new QueryParams(SysDept.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysOrgId", loginUserTool.getLoginUser().getSysOrgId());
        List<SysDept> sysDepts = sysDeptService.selectListByParams(queryParams);
        return Res.ok(sysDepts);
    }


    @RequestMapping("selectOrgTree")
    public List<SysDept> selectOrgTree() {
        return sysDeptService.selectOrgTree();
    }

    @RequestMapping("/loadData")
    public SysDept loadData(long id) {
        return sysDeptService.selectByPrimaryKey(id);
    }

    @RequestMapping("/deleteOrg")
    public Res deleteOrg(long id) {
        QueryParams params = new QueryParams(SysDept.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("parentId", id);
        int count = sysDeptService.selectCountByParams(params);
        if (count > 0) {
            return Res.error("当前机构有下属机构，无法删除");
        }
        sysDeptService.deleteByPrimaryKey(id);
        return Res.ok();
    }

}

