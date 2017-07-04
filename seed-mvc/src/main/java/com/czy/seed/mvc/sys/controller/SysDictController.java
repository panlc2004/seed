package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.annotation.AutoLog;
import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.util.Res;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 003914[panlc] on 2017-06-12.
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict> {


    @AutoLog("新增/修改数据字典项")
    public Res save(@RequestBody SysDict record) {
        if (record.getId() != null) {
            return super.insert(record);
        } else {
            return super.updateByPrimaryKey(record);
        }
    }

}
