package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.mapper.SysDeptMapper;
import com.czy.seed.mvc.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysOrgMapper;

    @Override
    public List<SysDept> selectOrgTree() {
        List<SysDept> sysOrgs = sysOrgMapper.selectAllOrgs();
        return buildTree(sysOrgs);
    }

    public List<SysDept> buildTree(List<SysDept> sysOrgs) {
        List<SysDept> res = new ArrayList<SysDept>();

        List<SysDept> rootOrg = findRootOrg(sysOrgs);
        findChildrenResource(sysOrgs, rootOrg);

        //构建id为0的虚拟节点
        SysDept zeroOrg = new SysDept();
        zeroOrg.setId(0L);
        zeroOrg.setParentId(-1L);
        zeroOrg.setCode("zero");
        zeroOrg.setName("组织机构");
        zeroOrg.getChildren().addAll(rootOrg);

        res.add(zeroOrg);

        return res;
    }

    private List<SysDept> findRootOrg(List<SysDept> orgList) {
        List<SysDept> rootOrgList = new ArrayList<SysDept>();
        for (SysDept org : orgList) {
            if (0 == org.getParentId()) {
                rootOrgList.add(org);
            }
        }
        orgList.remove(rootOrgList);
        return rootOrgList;
    }

    public void findChildrenResource(List<SysDept> orgWithoutRoot, List<SysDept> rootList) {
        List<SysDept> subList = new ArrayList<SysDept>();  //本轮未查找到归属的节点集合
        Iterator<SysDept> iterator = orgWithoutRoot.iterator();
        List<SysDept> children = new ArrayList<SysDept>();  //查找到归属的节点集合
        while (iterator.hasNext()) {
            SysDept resource = iterator.next();
            boolean flag = false;   //当为真时，表示当前iterator已经被识别为子节点
            for (SysDept parent : rootList) {
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
