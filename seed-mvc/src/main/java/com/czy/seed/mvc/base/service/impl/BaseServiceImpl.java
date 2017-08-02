package com.czy.seed.mvc.base.service.impl;

import com.czy.seed.mvc.auth.SecurityUser;
import com.czy.seed.mvc.base.entity.PrepareEntity;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.util.PrincipalUtil;
import com.czy.seed.mvc.util.TransactionUtil;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by PLC on 2017/4/30.
 */
public class BaseServiceImpl<T> implements BaseService<T> {


    @Autowired
    private BaseMapper<T> mapper;

    private int defaultBatchOperateLimit = 10000;   //批次新增时，每批次处理的最大数量

    public static final String DEFAULT_TM = "transactionManager";

    @PostConstruct
    public void prepareTransactionManager() {
//        SpringContextHelper.getBeanById(this.getClass().getSimpleName().substring(0).toLowerCase() +
//                this.getClass().getSimpleName().substring(1, this.getClass().getSimpleName().length()));
        try {
            String tmName = DEFAULT_TM;
            Class<? extends BaseMapper> mapperClass = this.getMapper().getClass();
            if (mapperClass.isAnnotationPresent(AutoMapper.class)) {
                AutoMapper annotation = mapperClass.getAnnotation(AutoMapper.class);
                tmName = annotation.value();
            }
            TransactionUtil.prepareTransactionManager(this.getClass(), tmName);
        } catch (Exception e) {
            throw new RuntimeException("动态设置事务管理器时出错", e);
        }
    }

    public BaseMapper<T> getMapper() {
        return mapper;
    }

    @Transactional
    public int insert(T record) {
        if (record instanceof PrepareEntity) {
            SecurityUser loginUser = PrincipalUtil.getLoginUser();
            PrepareEntity preparedRecord = (PrepareEntity) record;
            preparedRecord.setCreateDt(new Date());
            preparedRecord.setCreateBy(loginUser.getId());
            preparedRecord.setVersion(1);
        }
        return getMapper().insert(record);
    }

    @Transactional
    public int insertList(List<T> recordList) {
        return insertList(recordList, defaultBatchOperateLimit);
    }

    @Transactional
    public int insertList(List<T> recordList, int batchOperateLimit) {
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

    @Transactional
    public int updateByPrimaryKey(T record) {
        beforeUpdate(record);
        return getMapper().updateByPrimaryKey(record);
    }

    @Transactional
    public int updateSelectiveByPrimaryKey(T record) {
        beforeUpdate(record);
        return getMapper().updateSelectiveByPrimaryKey(record);
    }

    @Transactional
    public int updateByParams(T record, QueryParams params) {
        beforeUpdate(record);
        return getMapper().updateByParams(record, params);
    }

    @Transactional
    public int updateSelectiveByParams(T record, QueryParams params) {
        beforeUpdate(record);
        return getMapper().updateSelectiveByParams(record, params);
    }

    private void beforeUpdate(T record) {
        if (record instanceof PrepareEntity) {
            SecurityUser loginUser = PrincipalUtil.getLoginUser();

            PrepareEntity preparedRecord = (PrepareEntity) record;
            preparedRecord.setUpdateDt(new Date());
            preparedRecord.setUpdateBy(loginUser.getId());
            updateVersion(preparedRecord);
        }
    }

    /**
     * 更新版本号，下个版本将移至sql中计算，不采用加锁方式
     * @param record
     */
    private void updateVersion(PrepareEntity record) {
        synchronized (record.getClass().getName().intern()) {
            record.setVersion(record.getVersion() + 1);
        }
    }

    @Transactional
    public int deleteByPrimaryKey(long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Transactional
    public int deleteByParams(QueryParams params) {
        return getMapper().deleteByParams(params);
    }


}
