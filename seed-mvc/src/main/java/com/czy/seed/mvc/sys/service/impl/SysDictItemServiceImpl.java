package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mvc.sys.mapper.SysDictItemMapper;
import com.czy.seed.mvc.sys.service.SysDictItemService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDictItemServiceImpl extends BaseServiceImpl<SysDictItem> implements SysDictItemService {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Override
    public int insert(Long sysDictId, SysDictItem sysDictItem) {
        sysDictItem.setSysDictId(sysDictId);
       return   insert(sysDictItem);
    }

    @Override
    public List<SysDict> selectChildNumListByParentId(long parentId, long sysDictId) {
        return sysDictItemMapper.selectChildNumListByParentId(parentId, sysDictId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteChildByPrimaryKey(long id) {
        deleteByPrimaryKey(id);
        QueryParams queryParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("parentId", id);
        List<SysDictItem> childItems = selectListByParams(queryParams);
        deleteChild(childItems);
    }

    /**
     * 递归删除子级数据
     * @param sysDictItems
     */
    private void deleteChild(List<SysDictItem> sysDictItems) {
        if (sysDictItems == null || sysDictItems.size() == 0) {
            return;
        }
        List<Long> ids = new ArrayList<>();
        for (SysDictItem sysDictItem : sysDictItems) {
            ids.add(sysDictItem.getId());
        }

        //查找下级数据
        QueryParams queryParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andIn("parentId", ids);
        List<SysDictItem> sysDictChildItems = selectListByParams(queryParams);

        //删除本级数据
        QueryParams delParams = new QueryParams(SysDictItem.class);
        QueryParams.Criteria delCriteria = delParams.createCriteria();
        delCriteria.andIn("id", ids);
        deleteByParams(delParams);

        //删除子级数据
        deleteChild(sysDictChildItems);
    }
}
