package com.czy.seed.mvc.sys.controller;

        import com.czy.seed.mvc.base.controller.BaseController;
        import com.czy.seed.mvc.sys.entity.SysDict;
        import com.czy.seed.mvc.sys.service.SysDictService;
        import com.czy.seed.mvc.util.ILoginUserTool;
        import com.czy.seed.mvc.util.Res;
        import com.czy.seed.mybatis.base.QueryParams;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict> {
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private ILoginUserTool loginUserTool;

    /**
     * 根据父级部门id查找其下级部门
     * @param parentId 父级部门id
     * @return
     */
    @RequestMapping("/selectListByParentId")
    public Res selectListByParentId(Long parentId,String name) {
        List<SysDict> subDicts = sysDictService.selectChildNumListByParentId(parentId,name);
        return Res.ok(subDicts);
    }
}
