package com.czy.seed.mvc.base.service.impl;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.tool.NullUtil;
import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import javax.annotation.PostConstruct;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by PLC on 2017/4/30.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private int defaultBatchOperateLimit = 10000;

    private BaseMapper<T> mapper;

    public BaseMapper<T> getMapper() {
        return mapper;
    }

    /**
     * 初始化mapper类
     * 默认从Spring容器中查找id为：泛型实体类名 + Mapper 的mapper实例
     * 可被getMapperName()方法重定向
     */
    @PostConstruct
    public void initMapper() {
        Class<?> entityClass = getSuperClassGenricType(this.getClass(), 0);
        String mapperName = getMapperName();
        if (NullUtil.isEmpty(mapperName)) {
            mapperName = entityClass.getSimpleName() + "Mapper";
        }
        mapper = SpringContextHelper.getBeanById(mapperName.substring(0, 1).toLowerCase() + mapperName.substring(1, mapperName.length()));
    }

    /**
     * 指定对应的mapper名称
     * 不重写时，本service默认从Spring容器中查找id为：泛型实体类名 + Mapper 的mapper实例
     *
     * @return
     */
    public String getMapperName() {
        return null;
    }

    private Class<Object> getSuperClassGenricType(final Class clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (params[index] instanceof TypeVariableImpl) {
            GenericDeclaration genericDeclaration = ((TypeVariableImpl) params[index]).getGenericDeclaration();
            genericDeclaration.getTypeParameters()[index].getName();
            return null;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public int insert(T record) {
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

    public int selectCountByParams(QueryParams params) {
        return getMapper().selectCountByParams(params);
    }

    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getMapper().updateByPrimaryKeySelective(record);
    }

    public int updateByParams(T record, QueryParams params) {
        return getMapper().updateByParams(record, params);
    }

    public int updateSelectiveByParams(T record, QueryParams params) {
        return getMapper().updateSelectiveByParams(record, params);
    }

    public int deleteByPrimaryKey(long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByParams(QueryParams params) {
        return getMapper().deleteByParams(params);
    }

}
