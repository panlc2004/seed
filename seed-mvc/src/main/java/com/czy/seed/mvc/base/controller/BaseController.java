package com.czy.seed.mvc.base.controller;

import com.czy.seed.mvc.base.entity.BaseEntity;
import com.czy.seed.mvc.base.exception.IllegalleControllerNameException;
import com.czy.seed.mvc.base.service.BaseService;
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

/**
 * Created by PLC on 2017/5/21.
 */
public class BaseController<T extends BaseEntity> {

    public static final String URL_PATH_SEPRATOR = "/";

    @Autowired
    private BaseService<T> service;



    @RequestMapping("/index")
    public ModelAndView index() {
        String className = this.getClass().getSimpleName();
        String pageName = getViewName(className);
        return new ModelAndView(pageName);
    }

    /**
     * 跳转至指定页面
     *
     * @param viewPath 要跳转的页面路径
     * @return 参数指定页面视图
     */
    @RequestMapping("/toPage")
    public ModelAndView toView(String viewPath) {
        return new ModelAndView(viewPath);
    }

    /**
     * 跳转至指定页面
     *
     * @param viewPath 要跳转的页面路径
     * @param viewPath params 要跳转时带入的参数
     * @return 参数指定页面视图
     */
    @RequestMapping("/toPage")
    public ModelAndView toView(String viewPath, @RequestParam Map<String, Object> params) {
        ModelAndView model = new ModelAndView(viewPath);
        model.addAllObjects(params);
        return model;
    }

    /**
     * 获取页面名
     *
     * @param className 类名
     * @return 当前controller类名对应的index页面
     */
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

    /**
     * 保存数据:当传入数据有id时，进行修改操作，无id时，进行新增操作
     *
     * @param record 数据实体
     * @return 新增/修改数据的id值
     */
    public Res save(@RequestBody T record) {
        if (record.getId() != null) {
            service.insert(record);
        } else {
            service.updateByPrimaryKey(record);
        }
        return Res.ok(record.getId());
    }

    /**
     * 根据主键物理删除数据
     *
     * @param id 要删除数据的主键
     * @return
     */
    @RequestMapping("/deleteByPrimaryKey/{id}/{obj}")
    public Res deleteByPrimaryKey(@PathVariable Long id) {
        int num = service.deleteByPrimaryKey(id);
        return Res.ok(num);
    }

    /**
     * 分页查询
     *
     * @param pageNum  查询页号
     * @param pageSize 分页大小
     * @param params   查询参数
     * @return 返回分页数据
     */
    @RequestMapping("/selectPageByParams")
    public Res selectPageByParams(int pageNum, int pageSize, @RequestBody QueryParams params) {
        Page<T> page = service.selectPageByParams(pageNum, pageSize, params);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    /**
     * 查询所有数据
     *
     * @param params 查询参数
     * @return 所有数据
     */
    @RequestMapping("/selectListByParams")
    public Res selectListByParams(@RequestBody QueryParams params) {
        List<T> list = service.selectListByParams(params);
        return Res.ok(list);
    }



}
