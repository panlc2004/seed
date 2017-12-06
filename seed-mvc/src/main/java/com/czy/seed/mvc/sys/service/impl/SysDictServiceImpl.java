package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mvc.sys.mapper.SysDictMapper;
import com.czy.seed.mvc.sys.service.SysDictService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteChildByPrimaryKey(long id) {
        deleteByPrimaryKey(id);
        QueryParams queryParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("parentId", id);
        List<SysDict> childItems = selectListByParams(queryParams);
        deleteChild(childItems);
    }

    /**
     * 递归删除子级数据
     * @param sysDicts
     */
    private void deleteChild(List<SysDict> sysDicts) {
        if (sysDicts == null || sysDicts.size() == 0) {
            return;
        }
        List<Long> ids = new ArrayList<>();
        for (SysDict sysDict : sysDicts) {
            ids.add(sysDict.getId());
        }

        //查找下级数据
        QueryParams queryParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andIn("parentId", ids);
        List<SysDict> sysDictChildItems = selectListByParams(queryParams);

        //删除本级数据
        QueryParams delParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria delCriteria = delParams.createCriteria();
        delCriteria.andIn("id", ids);
        deleteByParams(delParams);

        //删除子级数据
        deleteChild(sysDictChildItems);
    }

}
