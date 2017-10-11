package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mvc.sys.service.SysDictItemService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys/dictItem")
public class SysDictItemController extends BaseController<SysDictItem> {
    @Autowired
    private SysDictItemService sysDictItemService;

    @RequestMapping("/insertItem")
    public Res insertItem(@RequestBody SysDictItem sysDictItem){
       /*List<SysDictItem> sysDictItems =*/ sysDictItemService.insert(sysDictItem);
        return Res.ok();
    }

}
