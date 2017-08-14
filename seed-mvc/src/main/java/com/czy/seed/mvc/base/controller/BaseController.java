package com.czy.seed.mvc.base.controller;

import com.czy.seed.mvc.base.exception.IllegalleControllerNameException;
import com.czy.seed.mvc.base.param.Param;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.util.GenricUtil;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Res selectRelativeByPrimaryKey(@PathVariable long id) {
        T record = service.selectRelativeByPrimaryKey(id);
        return Res.ok(record);
    }

    @RequestMapping("/selectPageByParams/{pageNum}/{pageSize}")
    public Res selectPageByParams(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        Page<T> page = service.selectPageByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    @RequestMapping("/selectPageRelativeByParams/{pageNum}/{pageSize}")
    public Res selectPageRelativeByParams(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        Page<T> page = service.selectPageRelativeByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    @RequestMapping("/selectListByParams")
    public Res selectListByParams(@RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        List<T> list = service.selectListByParams(queryParams);
        return Res.ok(list);
    }

    @RequestMapping("/selectListRelativeByParams")
    public Res selectListRelativeByParams(@RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        List<T> list = service.selectListRelativeByParams(queryParams);
        return Res.ok(list);
    }

    @RequestMapping("/insert")
    public Res insert(T record) {
        service.insert(record);
        return Res.ok(record);
    }

    public Res insertList(List<T> records) {
        int num = service.insertList(records);
        return Res.ok(num);
    }

    @RequestMapping("/updateSelectiveByPrimaryKey")
    public Res updateSelectiveByPrimaryKey(T record) {
        service.updateSelectiveByPrimaryKey(record);
        return Res.ok(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Res updateByPrimaryKey(T record) {
        service.updateByPrimaryKey(record);
        return Res.ok(record);
    }

    @RequestMapping("/updateByParams")
    public Res updateByParams(T record, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.updateByParams(record, queryParams);
        return Res.ok(num);
    }

    @RequestMapping("/updateSelectiveByParams")
    public Res updateSelectiveByParams(T record, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.updateSelectiveByParams(record, queryParams);
        return Res.ok(num);
    }

    @RequestMapping("/deleteByPrimaryKey/{id}")
    public Res deleteByPrimaryKey(@PathVariable Long id) {
        int num = service.deleteByPrimaryKey(id);
        return Res.ok(num);
    }

    @RequestMapping("/deleteByParams")
    public Res deleteByParams(@RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(entityClass);
        int num = service.deleteByParams(queryParams);
        return Res.ok(num);
    }

    public static void main(String[] args) {
        String a = "test";
        String b = new String("test");
        String c = "test";
        System.out.println(a.equals(b));

        throw new NullPointerException("test");
    }

}
