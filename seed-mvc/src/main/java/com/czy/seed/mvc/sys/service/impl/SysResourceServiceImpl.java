package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.mapper.SysResourceMapper;
import com.czy.seed.mvc.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by PLC on 2017/5/29.
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    public List<SysResource> selectResourceTree() {
        List<SysResource> allResources = sysResourceMapper.selectListAll();
        return buildTree(allResources);
    }

    /**
     * 构造资源树
     * @param allResources
     * @return
     */
    public List<SysResource> buildTree(List<SysResource> allResources) {
        List<SysResource> rootResource = findRootResource(allResources);
        findChildrenResource(allResources, rootResource);
        return rootResource;
    }

    /**
     * 查找根节点
     * @param allResources
     * @return
     */
    private List<SysResource> findRootResource(List<SysResource> allResources) {
        List<SysResource> rootResource = new ArrayList<SysResource>();
        for (SysResource resource : allResources) {
            if (0 == resource.getParentId()) {
                rootResource.add(resource);
            }
        }
        allResources.remove(rootResource);
        return rootResource;
    }

    /**
     * 设置父节点的所有子节点——递归调用
     * @param allResourcesWithoutRoot allResourcsWithoutRoot不包含父节点的所有资源列表
     * @param rootResourceList 根节点
     */
    public void findChildrenResource(List<SysResource> allResourcesWithoutRoot, List<SysResource> rootResourceList) {
        List<SysResource> subResources = new ArrayList<SysResource>();  //本轮未查找到归属的节点集合
        Iterator<SysResource> iterator = allResourcesWithoutRoot.iterator();
        List<SysResource> children = new ArrayList<SysResource>();  //查找到归属的节点集合
        while (iterator.hasNext()) {
            SysResource resource = iterator.next();
            boolean flag = false;   //当为真时，表示当前iterator已经被识别为子节点
            for (SysResource parentResource : rootResourceList) {
                if (resource.getParentId() == parentResource.getId()) {
                    parentResource.getChildren().add(resource);
                    children.add(resource);
                    flag = true;
                }
            }
            if(!flag) {
                subResources.add(resource);
            }
        }
        if (subResources.size() > 0 && children.size() > 0) {
            findChildrenResource(subResources, children);
        }
    }


}
