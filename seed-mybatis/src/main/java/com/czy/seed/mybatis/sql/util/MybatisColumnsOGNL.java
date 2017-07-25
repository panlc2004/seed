package com.czy.seed.mybatis.sql.util;


import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.sql.entity.EntityField;
import com.czy.seed.mybatis.sql.helper.FieldHelper;
import com.czy.seed.mybatis.tool.SpringPropertiesUtil;
import com.czy.seed.mybatis.util.IdWorker;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Id;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * OGNL静态方法
 * <p>
 * Created by panlc on 2017-03-21.
 */
public abstract class MybatisColumnsOGNL {

    private static Logger logger = LoggerFactory.getLogger(MybatisColumnsOGNL.class);
    private static String idGenModel;

    static {
        idGenModel = SpringPropertiesUtil.getStringProperty("id-gen-model");
    }

    /**
     * 是否包含自定义查询列
     *
     * @param parameter
     * @return
     */
    public static boolean hasSelectColumns(Object parameter) {
        if (parameter != null && parameter instanceof QueryParams) {
            QueryParams Params = (QueryParams) parameter;
            if (Params.getSelectColumns() != null && Params.getSelectColumns().size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不包含自定义查询列
     *
     * @param parameter
     * @return
     */
    public static boolean hasNoSelectColumns(Object parameter) {
        return !hasSelectColumns(parameter);
    }

    /**
     * 判断参数的各字段是否有值
     *
     * @param record
     * @return
     * @throws IllegalAccessException
     */
    public static boolean hasValue(Object record) throws IllegalAccessException {
        boolean res = false;
        if (record == null) {
            throw new IllegalArgumentException("record can't be null!");
        } else {
            List<EntityField> all = FieldHelper.getAll(record.getClass());
            for (EntityField entityField : all) {
                Field field = entityField.getField();
                if (field != null) {
                    field.setAccessible(true);
                    Object o = field.get(record);
                    if (o != null) {
                        res = true;
                        break;
                    }
                }
            }
        }
        if (!res) {
            logger.error("All fields in the record(type:{}) are null! This is not allowed!", record.getClass());
            throw new IllegalArgumentException("All fields in the record are null! This is not allowed!");
        }
        return res;
    }

    public static boolean idIsNull(Object parameter) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        if ("seed".equals(idGenModel)) {
            System.out.println(123123);
            if (parameter == null) {
                throw new IllegalArgumentException("record can't be null!");
            } else {
                setPkColumnsValue(parameter);
                return false;
            }
        }
        return true;   //入库前未主动生成主键值
    }

    /**
     * 设置id —— 当前不支持复合ID，即同一实体类中不能出现两个@ID注解的字段
     *
     * @param record 要操作的实体类
     */
    private static void setPkColumnsValue(Object record) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = record.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Id.class)) {
                if (!"java.lang.Long".equals(field.getType().getName())) {
                    throw new RuntimeException("当前只支持Lang类型的主键值");
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), record.getClass());
                Method readMethod = pd.getReadMethod();
                if (readMethod.invoke(record) == null) {    //ID未从外部设置值时，才自动生成ID
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(record, IdWorker.getId());//执行set方法设置id
                }
                break;
            }
        }
    }

    /**
     * 为List批量增加主键
     * @param parameter
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    public static void initPkForList(Object parameter) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        DefaultSqlSession.StrictMap map = (DefaultSqlSession.StrictMap) parameter;
        List list = (List) map.get("list");
        for (Object record : list) {
            idIsNull(record);
        }
    }

}
