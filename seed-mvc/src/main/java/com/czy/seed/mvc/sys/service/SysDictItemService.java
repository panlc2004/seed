package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDictItem;

import java.util.List;

public interface SysDictItemService extends BaseService<SysDictItem> {
   int  insert(Long sysDictId, SysDictItem sysDictItem);
}
