package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mvc.sys.service.SysDictItemService;
import org.springframework.stereotype.Service;

@Service
public class SysDictItemServiceImpl extends BaseServiceImpl<SysDictItem> implements SysDictItemService {


    public int insert(Long sysDictId, SysDictItem sysDictItem) {
        sysDictItem.setSysDictId(sysDictId);
       return   insert(sysDictItem);
    }
}
