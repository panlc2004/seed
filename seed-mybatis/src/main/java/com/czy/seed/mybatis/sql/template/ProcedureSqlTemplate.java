package com.czy.seed.mybatis.sql.template;

import com.czy.seed.mybatis.base.Procedure;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by panlc on 2017-04-17.
 */
public class ProcedureSqlTemplate {

    /**
     *生成存储过程
     * @param procedure
     * @return
     */
    public static InputStream createSqlInputStream(Procedure procedure) {

        String process = FreeMarkerUtil.process("procedure", procedure);
        InputStream inputStream = new ByteArrayInputStream(process.getBytes());
        return inputStream;
    }
}
