package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysResource;

import java.util.List;

public interface SysResourceService extends BaseService<SysResource> {

    List<SysResource> findResourceTreeForLoginUser();

    List<SysResource> selectResourceTree();
}
