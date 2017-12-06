package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDict;

import java.util.List;

public interface SysDictService extends BaseService<SysDict> {
    List<SysDict> selectOrgTree();

    List<SysDict> selectChildNumListByParentId(long parentId, String code);


    void deleteChildByPrimaryKey(long id);
}
