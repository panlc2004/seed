package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.util.Res;

import java.util.List;

public interface SysDictService extends BaseService<SysDict> {
    List<SysDict> selectChildNumListByParentId(Long parentId, String name) ;
}
