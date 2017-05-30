package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.sys.entity.SysResource;

import java.util.List;

/**
 * Created by PLC on 2017/5/29.
 */
public interface SysResourceService {

    /**
     * 查找资源树
     * @return
     */
    List<SysResource> selectResourceTree();
}
