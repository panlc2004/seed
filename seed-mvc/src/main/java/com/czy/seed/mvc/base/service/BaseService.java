package com.czy.seed.mvc.base.service;

import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseService<T> {

    int insert(T record);

    int insertList(List<T> recordList);

    T selectByPrimaryKey(long key);

    T selectRelativeByPrimaryKey(long key);

    List<T> selectListByParams(QueryParams params);

    List<T> selectListRelativeByParams(QueryParams params);

    T selectOneByParams(QueryParams params);

    T selectOneRelativeByParams(QueryParams params);

    int selectCountByParams(QueryParams params);

    Page<T> selectPageByParams(int pageNo, int pageLimit, final QueryParams params);

    Page<T> selectPageRelativeByParams(int pageNo, int pageLimit, final QueryParams params);

    int updateByPrimaryKey(T record);

    int updateSelectiveByPrimaryKey(T record);

    int updateByParams(@Param("record") T record, @Param("params") QueryParams params);

    int updateSelectiveByParams(@Param("record") T record, @Param("params") QueryParams params);

    int deleteByPrimaryKey(long id);

    int deleteByParams(QueryParams params);

}
