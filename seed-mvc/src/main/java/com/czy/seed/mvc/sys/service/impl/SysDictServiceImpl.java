package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.mapper.SysDictMapper;
import com.czy.seed.mvc.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;


    @Override
    public List<SysDict> selectOrgTree() {
        List<SysDict> sysDicts = sysDictMapper.selectAllOrgs();
        return buildTree(sysDicts);
    }

    public List<SysDict> selectChildNumListByParentId(long parentId,String code) {
        return sysDictMapper.selectChildNumListByParentId(parentId,code);
    }


   public List<SysDict> buildTree(List<SysDict> sysDicts) {
        List<SysDict> res = new ArrayList<SysDict>();

        List<SysDict> rootDict = findRootOrg(sysDicts);
        findChildrenResource(sysDicts, rootDict);

        //构建id为0的虚拟节点
        SysDict zeroOrg = new SysDict();
        zeroOrg.setId(0L);
        zeroOrg.setParentId(-1L);
        zeroOrg.setCode("zero");
        zeroOrg.setName("组织机构");
        zeroOrg.getChildren().addAll(rootDict);

        res.add(zeroOrg);

        return res;
    }

   private List<SysDict> findRootOrg(List<SysDict> dictList) {
        List<SysDict> rootDictList = new ArrayList<SysDict>();
        for (SysDict dict : dictList) {
            if (0 == dict.getParentId()) {
                rootDictList.add(dict);
            }
        }
        dictList.remove(rootDictList);
        return rootDictList;
    }

    public void findChildrenResource(List<SysDict> orgWithoutRoot, List<SysDict> rootList) {
        List<SysDict> subList = new ArrayList<SysDict>();  //本轮未查找到归属的节点集合
        Iterator<SysDict> iterator = orgWithoutRoot.iterator();
        List<SysDict> children = new ArrayList<SysDict>();  //查找到归属的节点集合
        while (iterator.hasNext()) {
            SysDict resource = iterator.next();
            boolean flag = false;   //当为真时，表示当前iterator已经被识别为子节点
            for (SysDict parent : rootList) {
                if (Objects.equals(resource.getParentId(), parent.getId())) {
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
