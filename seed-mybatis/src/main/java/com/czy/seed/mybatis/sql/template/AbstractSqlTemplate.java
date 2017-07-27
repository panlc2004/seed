package com.czy.seed.mybatis.sql.template;

import com.czy.seed.mybatis.config.datasource.ApplicationProperties;
import com.czy.seed.mybatis.sql.entity.EntityTable;
import com.czy.seed.mybatis.sql.helper.EntityHelper;
import com.czy.seed.mybatis.tool.NullUtil;
import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by panlc on 2017-03-20.
 */
public abstract class AbstractSqlTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationProperties applicationProperties = SpringContextHelper.getBeanById("applicationProperties");

    /**
     * 生成mybatis需要的mapper.xml文件
     *
     * @param mapperClass 实体类
     * @return mapper.xml
     */
    public InputStream buildSqlFile(Class<?> mapperClass) {
        EntityTable entityTable = anayEntity(mapperClass);
        return sqlParse(entityTable);
    }

    /**
     * sql生成模板路
     *
     * @return 模板名称
     */
    public abstract String getSqlTemplateName();

    /**
     * 解析实体，获得对应的表结构
     *
     * @param mapperClass mapper类型
     * @return EntityTable
     */
    public EntityTable anayEntity(Class<?> mapperClass) {
        return EntityHelper.getEntityTableByMapperClass(mapperClass);
    }

    /**
     * 根据表结构生成默认mapper.xml文件。
     *
     * @param entityTable
     * @return InputStream
     */
    private InputStream sqlParse(EntityTable entityTable) {
        String xmlContent = FreeMarkerUtil.process(getSqlTemplateName(), entityTable);
        String genPath = applicationProperties.getMybatisMapperGenPath();
        if (NullUtil.isNotEmpty(genPath)) {
            String xmlName = entityTable.getMapperClass().getSimpleName() + ".xml";
            //生成文件，以备查看
            genXmlFile(genPath, xmlName, xmlContent);
        }
        //加载mapper文件
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes(Charset.forName("UTF-8")));
        return inputStream;
    }

    /**
     * 生成xml文件，以便测试验证
     *
     * @param genPath    xml文件生成路径
     * @param xmlName    xml文件名称
     * @param xmlContent xml文件内容
     * @throws IOException
     */
    private void genXmlFile(String genPath, String xmlName, String xmlContent) {
        logger.debug("生成mybatis mapper调试文件 -> ", "路径:", genPath, ", 名称:", xmlName);
        try {
            if (NullUtil.isNotEmpty(genPath)) {
                File genFolder = new File(genPath);
                if (!genFolder.exists()) {
                    genFolder.mkdirs();
                }
                String xmlPath = genFolder + File.separator + xmlName;
                File xml = new File(xmlPath);
                FileWriter fileWriter = new FileWriter(xml);
                fileWriter.write(xmlContent);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            logger.error("生成mybatis mapper调试文件时出错", e);
        }
    }
}
