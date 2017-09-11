package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDept;

import java.util.List;

public interface SysDeptService extends BaseService<SysDept> {

    List<SysDept> selectOrgTree();

    List<SysDept> selectChildNumListByParentId(long parentId);

}
