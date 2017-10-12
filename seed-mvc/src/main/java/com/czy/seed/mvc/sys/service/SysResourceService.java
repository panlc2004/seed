package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mybatis.base.QueryParams;

import java.util.List;

public interface SysResourceService extends BaseService<SysResource> {

    List<SysResource> findResourceTreeForLoginUser();
    List<SysResource> selectListByParams();
    List<SysResource> selectResourceTree();
    List<SysResource> selectChildNumListByParentId(long parentId);
    List<SysResource> selectOrgTree();
}
