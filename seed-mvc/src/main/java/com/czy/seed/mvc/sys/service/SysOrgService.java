package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.sys.entity.SysOrg;

import java.util.List;

/**
 * Created by PLC on 2017/5/30.
 */
public interface SysOrgService {

    /**
     * 查找组织机构树
     * @return
     */
    List<SysOrg> selectOrgTree();

}
