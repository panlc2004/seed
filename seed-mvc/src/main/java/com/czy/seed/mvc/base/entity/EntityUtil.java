package com.czy.seed.mvc.base.entity;

import javax.persistence.Id;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-08-16.
 */
public class EntityUtil {

    public static final Map<String, String> idFieldCache = new HashMap<>();

    public static void setPreparedValue(Object record, String targetField, Object value) {
        PropertyDescriptor idDes;
        try {
            idDes = new PropertyDescriptor(targetField, record.getClass());
        } catch (IntrospectionException e) {
            return;
        }
        Method writeMethod = idDes.getWriteMethod();
        try {
            writeMethod.invoke(record, value);
        } catch (Exception e) {
            throw new RuntimeException("向对象:" + record.getClass().getName() + "的字段:" + targetField + "设置值时出错。" + e);
        }
    }

    public static Long getIdValule(Object record) {
        try {
            PropertyDescriptor idDes = new PropertyDescriptor("id", record.getClass());
            Method readMethod = idDes.getReadMethod();
            Object idValud = readMethod.invoke(record);
            return (Long) idValud;
        } catch (Exception e) {
            throw new RuntimeException("使用getId()方法获取主键值时出错，请确认类：" + record.getClass().getName()
                    + "中有使用getId方法返回主键值",e);
        }
    }

    /**
     * 取ID值
     *
     * @param recordClass
     * @return
     */
    private static String getIdFieldName(Class recordClass, String className) {
        if (idFieldCache.containsKey(className)) {
            return idFieldCache.get(className);
        }
        String idFieldName = null;
        Field[] declaredFields = recordClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                idFieldName = declaredField.getName();
                idFieldCache.put(className, idFieldName);
                return idFieldName;
            }
        }
        if (idFieldName == null) {
            Class superclass = recordClass.getSuperclass();
            if (!superclass.getName().equals("java.lang.Object")) {
                return getIdFieldName(superclass, className);
            }
        }
        return idFieldName;
    }

}
