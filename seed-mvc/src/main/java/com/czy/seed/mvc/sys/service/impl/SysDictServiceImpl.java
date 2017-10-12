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
    private SysDictMapper sysDictMapper;
    public List<SysDict> selectChildNumListByParentId(Long parentId, String name) {
        return sysDictMapper.selectChildNumListByParentId(parentId,name);
    }
}
