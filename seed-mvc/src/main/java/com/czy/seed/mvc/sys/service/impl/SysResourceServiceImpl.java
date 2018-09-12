package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.auth.UserAuthority;
import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.mapper.SysResourceMapper;
import com.czy.seed.mvc.sys.service.SysResourceService;
import com.czy.seed.mvc.util.LoginUserTool;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private LoginUserTool loginUserTool;

    public List<SysResource> selectChildNumListByParentId(long parentId) {
        return sysResourceMapper.selectChildNumListByParentId(parentId);
    }

    public List<SysResource> selectListByName(String name) {
        return sysResourceMapper.selectListByName(name);
    }

    public List<SysResource> findResourceTreeForLoginUser() {
        String roleIds = getRoleIds(loginUserTool.getLoginUserRoles());
        List<SysResource> resources = sysResourceMapper.findResourceForLoginUser(roleIds);   //查询用户所有菜单

        //关联查询所有菜单的父级菜单
        List<SysResource> fullResourceList = findFullResources(resources, resources);
        return buildTree(fullResourceList);
    }

    private String getRoleIds(List<UserAuthority> loginUserRoles) {
        //封装角色id
        StringBuffer sb = new StringBuffer("(");
        for (UserAuthority userAuthority : loginUserRoles) {
            sb.append(userAuthority.getRoleId()).append(",");
        }
        String substring = sb.substring(0, sb.length() - 1);
        substring = substring + ")";
        return substring;
    }

    private List<SysResource> findFullResources(List<SysResource> resources, List<SysResource> fullResources) {
        if (resources == null || resources.size() == 0) {
            return fullResources;
        }

        //生成父级资源id列表
        Set<Long> ids = new HashSet<Long>(resources.size());
        List<SysResource> parentResources = new ArrayList<SysResource>();
        for (SysResource resource : resources) {
            Long parentId = resource.getParentId();
            if (parentId != 0) {
                ids.add(parentId);
            }
        }

        //查找父级资源
        if (ids.size() > 0) {
            QueryParams queryParams = new QueryParams(SysResource.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            criteria.andIn("id", ids);
            parentResources = sysResourceMapper.selectListByParams(queryParams);   //查找父级资源
            for (SysResource par : parentResources) {
                boolean have = false;
                for (SysResource full : fullResources) {
                    if (Objects.equals(par.getId(), full.getId())) {
                        have = true;
                        break;
                    }
                }
                if (!have) {
                    fullResources.add(par);
                }
            }
        }
        return findFullResources(parentResources, fullResources);  //递归查找;
    }

    @Override
    public List<SysResource> selectListByParams() {
        return null;
    }

    public List<SysResource> selectResourceTree() {
        List<SysResource> allResources = sysResourceMapper.selectListAll();
        return buildTree(allResources);
    }

//    public int insert1(SysResource sysResource) {
////        return sysResourceMapper.insert1(sysResource);
//    }


    public List<SysResource> buildTree(List<SysResource> allResources) {
        List<SysResource> rootResource = findRootResource(allResources);
        findChildrenResource(allResources, rootResource);
        //构造虚拟根节点：一个id为0的节点，永远展示在页面上
        SysResource zeroNode = new SysResource();
        zeroNode.setName("系统菜单");
        zeroNode.setId(0L);
        zeroNode.setParentId(-1L);
        zeroNode.getChildren().addAll(rootResource);
        List<SysResource> resources = new ArrayList<SysResource>();
        resources.add(zeroNode);
        orderBy(resources);
        return resources;
    }

    private void orderBy(List<SysResource> resources) {
        Collections.sort(resources, new Comparator<SysResource>() {
            @Override
            public int compare(SysResource o1, SysResource o2) {
                int orderByDiff = o1.getOrderBy() - o2.getOrderBy();
                if (orderByDiff != 0) {
                    return orderByDiff;
                } else {
                    String idDiff = o1.getId() - o2.getId() + "";
                    return Integer.parseInt(idDiff);
                }
            }
        });
        for (SysResource resource : resources) {
            List<SysResource> children = resource.getChildren();
            if (children != null && children.size() > 0) {
                orderBy(children);
            }
        }
    }

    private List<SysResource> findRootResource(List<SysResource> allResources) {
        List<SysResource> rootResource = new ArrayList<SysResource>();
        for (SysResource resource : allResources) {
            if (0 == resource.getParentId()) {
                rootResource.add(resource);
            }
        }
        allResources.removeAll(rootResource);
        return rootResource;
    }

    @Override
    public List<SysResource> selectOrgTree() {
        List<SysResource> sysOrgs = sysResourceMapper.selectAllOrgs();
        return buildTree(sysOrgs);
    }

    public void findChildrenResource(List<SysResource> allResourcesWithoutRoot, List<SysResource> rootResourceList) {
        List<SysResource> subResources = new ArrayList<SysResource>();  //本轮未查找到归属的节点集合
        Iterator<SysResource> iterator = allResourcesWithoutRoot.iterator();
        List<SysResource> children = new ArrayList<SysResource>();  //查找到归属的节点集合
        while (iterator.hasNext()) {
            SysResource resource = iterator.next();
            boolean flag = false;   //当为真时，表示当前iterator已经被识别为子节点
            for (SysResource parentResource : rootResourceList) {
                if (Objects.equals(resource.getParentId(), parentResource.getId())) {
                    parentResource.getChildren().add(resource);
                    children.add(resource);
                    flag = true;
                }
            }
            if (!flag) {
                subResources.add(resource);
            }
        }
        if (subResources.size() > 0 && children.size() > 0) {
            findChildrenResource(subResources, children);
        }
    }

}
