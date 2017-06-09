package com.czy.seed.mvc.wbm.msg.template;

import com.czy.seed.mvc.wbm.msg.ReportData;
import com.czy.seed.mybatis.config.exception.TemplateNotExistException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by panlc on 2017-04-25.
 */
public class TemplateLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateLoader.class);

    private static final Configuration CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    private static final Map<String, Template> TEMPLATE_MAPS = Collections.synchronizedMap(new HashMap<String, Template>());

    private static final StringWriter STRING_WRITER = new StringWriter();

    static {
        loadTemplate();
    }

    private static void loadTemplate() {
        try {
            String path = TemplateLoader.class.getResource("/template").getPath();
            path = URLDecoder.decode(path, "utf-8");        //转码，以防止路径内有空格
            File templateFolder = new File(path);
            CONFIGURATION.setDirectoryForTemplateLoading(templateFolder);
            CONFIGURATION.setDefaultEncoding("UTF-8");

            File[] files = templateFolder.listFiles();
            if (files != null) {
                for (File fileConsumer : files) {
                    try {
                        TEMPLATE_MAPS.put(fileConsumer.getName().replaceAll(".ftl", ""), CONFIGURATION.getTemplate(fileConsumer.getName()));
                    } catch (Exception e) {
                        LOGGER.error("get template error the file is " + templateFolder.getAbsolutePath(), e);
                    }
                }
            } else {
                LOGGER.error("templates nut found");
            }

        } catch (Exception e) {
            throw new RuntimeException("初始化模板加载路径时出错：", e);
        }
    }

    /**
     * 加载模板，构建报文
     *
     * @param templateName 模板名称
     * @param reportData   舱单报数据封装实体
     * @return 生成的报文
     */
    public static String parse(String templateName, ReportData reportData) {
        synchronized (templateName.intern()) {  //同一模板加锁
            if (TEMPLATE_MAPS.containsKey(templateName)) {
                STRING_WRITER.getBuffer().delete(0, STRING_WRITER.getBuffer().length());
                try {
                    TEMPLATE_MAPS.get(templateName).process(reportData, STRING_WRITER);
                } catch (Exception e) {
                    LOGGER.error("error occurred while process a ftl file", e);
                    throw new RuntimeException("error occurred while process a ftl file", e);
                }
                STRING_WRITER.flush();
                return STRING_WRITER.toString();
            } else {
                throw new TemplateNotExistException(" can't find the template: " + templateName
                        + ", maybe the method:loadTemplateToCache is needed to run first to load template first");
            }
        }
    }
}
