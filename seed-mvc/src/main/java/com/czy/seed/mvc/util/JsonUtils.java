package com.czy.seed.mvc.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonUtils {
    private static JacksonCustomObjectMapper objectMapper = new JacksonCustomObjectMapper();
    
    public static String toJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static JacksonCustomObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
