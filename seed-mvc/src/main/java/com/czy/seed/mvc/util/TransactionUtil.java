package com.czy.seed.mvc.util;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.apache.ibatis.javassist.bytecode.annotation.Annotation;
import org.apache.ibatis.javassist.bytecode.annotation.StringMemberValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by 003914[panlc] on 2017-07-07.
 */
public class TransactionUtil {

    public static Logger logger = LoggerFactory.getLogger(TransactionUtil.class);

//    public static void main(String[] args) throws ClassNotFoundException, NotFoundException {
//        String str = "com.czy.seed.mvc.sys.service.impl.SysLogServiceImpl";
//        Class claz = Class.forName(str);
//        prepareTransactionManager(claz, "tm-ds1");
//    }

    public static void prepareTransactionManager(Class clazz, String tmName) throws NotFoundException, ClassNotFoundException {
        if (BaseServiceImpl.DEFAULT_TM.equals(tmName)) {
            return;
        }
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(clazz));    //设置classLoader
        //获取需要修改的类
        CtClass ct = pool.get(clazz.getName());
        CtClass baseService = getParentService(ct);
        //获取类里的所有方法
        CtMethod[] declaredMethods = baseService.getDeclaredMethods();
        for (CtMethod cms : declaredMethods) {
            Object annotation = cms.getAnnotation(Transactional.class);
            if (annotation != null) {                       //对标记了Transactional的方法，进行动态设置事务
                setTransactionManagerName(cms, tmName);
            }
        }
    }

    /**
     * 设置事务管理器
     * @param cms
     * @param tmName
     */
    private static void setTransactionManagerName(CtMethod cms, String tmName) {
        MethodInfo methodInfo = cms.getMethodInfo();
        ConstPool constPool = methodInfo.getConstPool();
        //获取注解信息
        AnnotationsAttribute attribute2 = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("org.springframework.transaction.annotation.Transactional", constPool);
        annotation.addMemberValue("transactionManager", new StringMemberValue(tmName, constPool));
        attribute2.setAnnotation(annotation);
        //打印修改后方法
//        Annotation annotation2 = attribute2.getAnnotation("org.springframework.transaction.annotation.Transactional");
//        String text = ((StringMemberValue) annotation2.getMemberValue("transactionManager")).getValue();
//        System.out.println("修改后的注解名称===" + text);
        logger.debug("设置事务管理器：{} -> {}", cms.getLongName(), tmName);
    }

    /**
     * 查找父类Service
     *
     * @param clazz
     * @return
     */
    private static CtClass getParentService(CtClass clazz) throws NotFoundException {
        while (true) {
            CtClass superclass = clazz.getSuperclass();
            if (BaseServiceImpl.class.getName().equals(superclass.getName())) {
                return superclass;
            }
        }
    }

}
