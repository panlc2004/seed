package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.mapper.SysDictMapper;
import com.czy.seed.mvc.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysOrgMapper;


    public List<SysDict> selectChildNumListByParentId(long parentId) {
        return sysOrgMapper.selectChildNumListByParentId(parentId);
    }


    @Override
    public List<SysDict> selectOrgTree() {
        List<SysDict> sysOrgs = sysOrgMapper.selectAllOrgs();
        return buildTree(sysOrgs);
    }

    public List<SysDict> buildTree(List<SysDict> sysOrgs) {
        List<SysDict> res = new ArrayList<SysDict>();

        List<SysDict> rootOrg = findRootOrg(sysOrgs);
        findChildrenResource(sysOrgs, rootOrg);

        //构建id为0的虚拟节点
        SysDict zeroOrg = new SysDict();
        zeroOrg.setId(0L);
        zeroOrg.setParentId(-1L);
        zeroOrg.setCode("zero");
        zeroOrg.setName("组织机构");
        zeroOrg.getChildren().addAll(sysOrgs);

        res.add(zeroOrg);

        return res;
    }

    private List<SysDict> findRootOrg(List<SysDict> orgList) {
        List<SysDict> rootOrgList = new ArrayList<SysDict>();
        for (SysDict org : orgList) {
            if (0 == org.getParentId()) {
                rootOrgList.add(org);
            }
        }
        orgList.remove(rootOrgList);
        return rootOrgList;
    }

    public void findChildrenResource(List<SysDict> orgWithoutRoot, List<SysDict> rootList) {
        List<SysDict> subList = new ArrayList<SysDict>();  //本轮未查找到归属的节点集合
        Iterator<SysDict> iterator = orgWithoutRoot.iterator();
        List<SysDict> children = new ArrayList<SysDict>();  //查找到归属的节点集合
        while (iterator.hasNext()) {
            SysDict resource = iterator.next();
            boolean flag = false;   //当为真时，表示当前iterator已经被识别为子节点
            for (SysDict parent : rootList) {
                if (resource.getParentId() == parent.getId()) {
                    parent.getChildren().add(resource);
                    children.add(resource);
                    flag = true;
                }
            }
            if(!flag) {
                subList.add(resource);
            }
        }
        if (subList.size() > 0 && children.size() > 0) {
            findChildrenResource(subList, children);
        }
    }

}
