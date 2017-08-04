package com.czy.seed.mvc.conf;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.czy.seed.mvc.util.JacksonCustomObjectMapper;

/**
 * Created by panlc on 2017-05-23.
 */
@Configuration
public class JsonConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter ret = new MappingJackson2HttpMessageConverter();
        ret.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        ret.setObjectMapper(new JacksonCustomObjectMapper());
        return ret;
    }
}
