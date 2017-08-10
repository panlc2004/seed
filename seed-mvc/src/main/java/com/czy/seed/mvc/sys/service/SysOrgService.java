package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysOrg;

import java.util.List;

public interface SysOrgService extends BaseService<SysOrg> {

    List<SysOrg> selectOrgTree();

}
