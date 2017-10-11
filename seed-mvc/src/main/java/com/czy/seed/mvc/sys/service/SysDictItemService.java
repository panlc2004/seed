package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDictItem;

public interface SysDictItemService extends BaseService<SysDictItem> {
   int  insert(Long sysDictId, SysDictItem sysDictItem);
}
