package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.param.Param;
import com.czy.seed.mvc.sys.entity.SysDict;
import com.czy.seed.mvc.sys.entity.SysDictItem;
import com.czy.seed.mvc.sys.service.SysDictItemService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("sys/dictItem")
public class SysDictItemController extends BaseController<SysDictItem> {

    @Autowired
    private SysDictItemService sysDictItemService;

    @Override
    public void appendParamsBeforeSelect(Param param) {
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
        orderBy.put("orderNum", "asc");
        param.addOrderBy(orderBy);
    }

    /**
     * 根据父级部门id查找其下级部门
     * @param parentId 父级部门id
     * @return
     */
    @RequestMapping("/selectListByParentId/{parentId}/{sysDictId}")
    public Res selectListByParentId(@PathVariable long parentId, @PathVariable long sysDictId) {
        List<SysDict> sysDicts = sysDictItemService.selectChildNumListByParentId(parentId,sysDictId);
        return Res.ok(sysDicts);
    }

    @RequestMapping("/insertItem")
    public Res insertItem(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.insert(sysDictItem);
        return Res.ok();
    }

    @RequestMapping("/deleteChildByPrimaryKey/{id}")
    public Res deleteChildByPrimaryKey(@PathVariable long id) {
        sysDictItemService.deleteChildByPrimaryKey(id);
        return Res.ok();
    }

}
