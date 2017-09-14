package com.czy.seed.mvc.base.service.impl;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.base.entity.EntityUtil;
import com.czy.seed.mvc.base.entity.IPrepare;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    private static final String CREATE_BY = "createBy";
    private static final String CREATE_DT = "createDt";
    private static final String UPDATE_BY = "updateBy";
    private static final String UPDATE_DT = "updateDt";
    private static final String SYS_ORG_ID = "sysOrgId";
    private static final String SYS_DEPT_ID = "sysDeptId";

    @Autowired
    private BaseMapper<T> mapper;

    private ILoginUserTool loginUserTool;

    @Autowired
    public void setLoginUserTool(ILoginUserTool loginUserTool) {
        this.loginUserTool = loginUserTool;
    }

    private int defaultBatchOperateLimit = 10000;   //批次新增时，每批次处理的最大数量

    public BaseMapper<T> getMapper() {
        return mapper;
    }

    public int insert(T record) {
        beforeInsert(record);
        return getMapper().insert(record);
    }

    public void beforeInsert(T record) {
        if(record instanceof IPrepare) {
            SecurityUser loginUser = loginUserTool.getLoginUser();
            if (loginUser != null) {
                EntityUtil.setPreparedValue(record, CREATE_BY, loginUser.getId());
                EntityUtil.setPreparedValue(record, SYS_ORG_ID, loginUser.getSysOrgId());
                EntityUtil.setPreparedValue(record, SYS_DEPT_ID, loginUser.getSysDeptId());
            }
            EntityUtil.setPreparedValue(record, CREATE_DT, new Date());
        }
    }

    public int insertList(List<T> recordList) {
        return insertList(recordList, defaultBatchOperateLimit);
    }

    public int insertList(List<T> recordList, int batchOperateLimit) {
        for (T record : recordList) {
            beforeInsert(record);
        }
        int res = 0;
        int i = 0;
        int per = recordList.size() / batchOperateLimit;
        while (i < per) {
            res += getMapper().insertList(recordList.subList(i * batchOperateLimit, (i + 1) * batchOperateLimit));
            i++;
        }
        if (per * batchOperateLimit != recordList.size()) {
            res += getMapper().insertList(recordList.subList(per * batchOperateLimit, recordList.size()));
        }
        return res;
    }

    public T selectByPrimaryKey(long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public T selectRelativeByPrimaryKey(long id) {
        return getMapper().selectRelativeByPrimaryKey(id);
    }

    public List<T> selectListByParams(QueryParams params) {
        return getMapper().selectListByParams(params);
    }

    public List<T> selectListRelativeByParams(QueryParams params) {
        return getMapper().selectListRelativeByParams(params);
    }

    public T selectOneByParams(QueryParams params) {
        return getMapper().selectOneByParams(params);
    }

    public T selectOneRelativeByParams(QueryParams params) {
        return getMapper().selectOneRelativeByParams(params);
    }

    public Page<T> selectPageByParams(int pageNo, int pageLimit, final QueryParams params) {
        Page<T> page = PageHelper.startPage(pageNo, pageLimit).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                selectListByParams(params);
            }
        });
        return page;
    }

    public Page<T> selectPageRelativeByParams(int pageNo, int pageLimit, final QueryParams params) {
        Page<T> page = PageHelper.startPage(pageNo, pageLimit).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                selectListRelativeByParams(params);
            }
        });
        return page;
    }

    public int selectCountByParams(QueryParams params) {
        return getMapper().selectCountByParams(params);
    }

    public int updateByPrimaryKey(T record) {
        beforeUpdate(record);
        return getMapper().updateByPrimaryKey(record);
    }

    public int updateSelectiveByPrimaryKey(T record) {
        beforeUpdate(record);
        return getMapper().updateSelectiveByPrimaryKey(record);
    }

    public int updateByParams(T record, QueryParams params) {
        beforeUpdate(record);
        return getMapper().updateByParams(record, params);
    }

    public int updateSelectiveByParams(T record, QueryParams params) {
        beforeUpdate(record);
        return getMapper().updateSelectiveByParams(record, params);
    }

    private void beforeUpdate(T record) {
        SecurityUser loginUser = loginUserTool.getLoginUser();
        if (loginUser != null) {
            EntityUtil.setPreparedValue(record, UPDATE_BY, loginUser.getId());
        }
        EntityUtil.setPreparedValue(record, UPDATE_DT, new Date());
    }

    public int deleteByPrimaryKey(long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByParams(QueryParams params) {
        return getMapper().deleteByParams(params);
    }


}
