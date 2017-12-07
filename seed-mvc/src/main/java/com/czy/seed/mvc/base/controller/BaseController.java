package com.czy.seed.mvc.base.controller;

import com.czy.seed.mvc.base.entity.EntityUtil;
import com.czy.seed.mvc.base.exception.IllegalleControllerNameException;
import com.czy.seed.mvc.base.param.Param;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.util.GenricUtil;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController<T> {

    public BaseController() {
        entityClass = GenricUtil.getGenericClass(this.getClass(), 0);
    }

    public static final String URL_PATH_SEPRATOR = "/";
    private Class entityClass;  //泛型T的类型

    @Autowired
    private BaseService<T> service;

    @RequestMapping("/toView/{viewPath}")
    public ModelAndView toView(@PathVariable String viewPath, @RequestParam Map<String, Object> params) {
        ModelAndView model = new ModelAndView(viewPath);
        model.addAllObjects(params);
        return model;
    }

    public void appendParamsBeforeSelect(Param param) {

    }

    private String getViewName(String className) {
        for (int i = 0; i < className.length(); i++) {
            if (i > 0 && Character.isUpperCase(className.charAt(i)) == true) {
                String viewPath = className.substring(0, i).toLowerCase();
                String viewName = className.substring(i, className.length() - 10).toLowerCase();
                return URL_PATH_SEPRATOR + viewPath + URL_PATH_SEPRATOR + viewName;
            }
        }
        throw new IllegalleControllerNameException();
    }

    @RequestMapping("/selectRelativeByPrimaryKey/{id}")
    @ResponseBody
    public Res selectRelativeByPrimaryKey(@PathVariable long id) {
        T record = service.selectRelativeByPrimaryKey(id);
        return Res.ok(record);
    }

    @RequestMapping("/selectPageByParams/{pageNum}/{pageSize}")
    @ResponseBody
    public Res selectPageByParams(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        Page<T> page = service.selectPageByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    /**
     * 目前在有一对多的情况下不能使用，只适应于One2One查询
     * @param pageNum
     * @param pageSize
     * @param param
     * @return
     */
    @RequestMapping("/selectPageRelativeByParams/{pageNum}/{pageSize}")
    @ResponseBody
    public Res selectPageRelativeByParams(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        Page<T> page = service.selectPageRelativeByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    @RequestMapping("/selectListByParams")
    @ResponseBody
    public Res selectListByParams(@RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        List<T> list = service.selectListByParams(queryParams);
        return Res.ok(list);
    }

    @RequestMapping("/selectListRelativeByParams")
    @ResponseBody
    public Res selectListRelativeByParams(@RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        List<T> list = service.selectListRelativeByParams(queryParams);
        return Res.ok(list);
    }

    @RequestMapping("/selectOneByParams")
    @ResponseBody
    public Res selectOneByParams(@RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        T record = service.selectOneByParams(queryParams);
        return Res.ok(record);
    }

    @RequestMapping("/selectOneRelativeByParams")
    @ResponseBody
    public Res selectOneRelativeByParams(@RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        T record = service.selectOneRelativeByParams(queryParams);
        return Res.ok(record);
    }

    @RequestMapping("/selectCountByParams")
    @ResponseBody
    public Res selectCountByParams(@RequestBody Param param) {
        appendParamsBeforeSelect(param);
        QueryParams queryParams = param.toQueryParams(entityClass);
        int count = service.selectCountByParams(queryParams);
        return Res.ok(count);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Res insert(T record) {
        service.insert(record);
        return Res.ok(record);
    }

    public Res insertList(List<T> records) {
        int num = service.insertList(records);
        return Res.ok(num);
    }

    @RequestMapping("/updateSelectiveByPrimaryKey")
    @ResponseBody
    public Res updateSelectiveByPrimaryKey(T record) {
        service.updateSelectiveByPrimaryKey(record);
        return Res.ok(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    public Res updateByPrimaryKey(T record) {
        service.updateByPrimaryKey(record);
        return Res.ok(record);
    }

    @RequestMapping("/updateByParams")
    @ResponseBody
    public Res updateByParams(T record, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.updateByParams(record, queryParams);
        return Res.ok(num);
    }

    @RequestMapping("/updateSelectiveByParams")
    @ResponseBody
    public Res updateSelectiveByParams(T record, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.updateSelectiveByParams(record, queryParams);
        return Res.ok(num);
    }

    @RequestMapping("/deleteByPrimaryKey/{id}")
    @ResponseBody
    public Res deleteByPrimaryKey(@PathVariable Long id) {
        int num = service.deleteByPrimaryKey(id);
        return Res.ok(num);
    }

    @RequestMapping("/deleteByParams")
    @ResponseBody
    public Res deleteByParams(@RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.deleteByParams(queryParams);
        return Res.ok(num);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Res save(@RequestBody T record) {

        if (EntityUtil.getIdValule(record) == null) {
            return insert(record);
        } else {
            return updateByPrimaryKey(record);
        }
    }

}
