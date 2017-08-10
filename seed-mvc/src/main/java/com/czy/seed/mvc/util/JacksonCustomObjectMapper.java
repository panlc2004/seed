package com.czy.seed.mvc.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class JacksonCustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;
    
    public JacksonCustomObjectMapper() {
        super();

        configToStringSerializer();
        
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
//        // 转换对象为空输出空字符, 避免页面出现null
//        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
//                    throws IOException, JsonProcessingException {
//                jsonGenerator.writeString("");
//            }
//        });

        // 如果为空则不输出
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        
        // 对于空的对象转json的时候不抛出错误
        this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        // 禁用遇到未知属性抛出异常
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        
        // 视空字符串为null
//        this.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        
        // 禁用序列化日期为timestamps
//      this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

//        // 取消对非ASCII字符的转码
//         this.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
//        // xxx
//        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
    }
    
    
    private void configToStringSerializer() {
        // 输出数值加引号,解决long 数值类型过长,js 解析不了.
//      this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
      SimpleModule simpleModule = new SimpleModule();
      simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
      simpleModule.addSerializer(Double.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
      simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Float.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Float.TYPE, ToStringSerializer.instance);
      simpleModule.addSerializer(Long[].class, LongArraySerializer.instance);
      simpleModule.addSerializer(long[].class, LongArray2Serializer.instance);
      this.registerModule(simpleModule);
    }
    
    public static final class LongArraySerializer extends StdScalarSerializer<Long[]> {
        private static final long serialVersionUID = 1L;
        static final JacksonCustomObjectMapper.LongArraySerializer instance = new JacksonCustomObjectMapper.LongArraySerializer();

        public LongArraySerializer() {
            super(Long[].class);
        }

        @Override
        public void serialize(Long[] aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonGenerationException {
            if (aLong != null) {
                jsonGenerator.writeStartArray();
                for (Long val : aLong) {
                    jsonGenerator.writeString(String.valueOf(val));
                }
                jsonGenerator.writeEndArray();
            }
        }
    }
    
    public static final class LongArray2Serializer extends StdScalarSerializer<long[]> {
        private static final long serialVersionUID = 1L;
        static final JacksonCustomObjectMapper.LongArray2Serializer instance = new JacksonCustomObjectMapper.LongArray2Serializer();

        public LongArray2Serializer() {
            super(long[].class);
        }

        @Override
        public void serialize(long[] aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonGenerationException {
            if (aLong != null) {
                jsonGenerator.writeStartArray();
                for (long val : aLong) {
                    jsonGenerator.writeString(String.valueOf(val));
                }
                jsonGenerator.writeEndArray();
            }
        }
    }
}
