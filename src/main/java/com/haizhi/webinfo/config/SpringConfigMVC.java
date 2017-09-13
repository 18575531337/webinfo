package com.haizhi.webinfo.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.haizhi.webinfo.interceptor.AuthInterceptor;
import com.haizhi.webinfo.interceptor.TestInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by JuniFire on 2017/9/6.
 */
@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "com.haizhi.webinfo.controller")
public class SpringConfigMVC extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        stringConverter.setWriteAcceptCharset(false);
        FastJsonHttpMessageConverter fastJson = new FastJsonHttpMessageConverter();
        converters.add(fastJson);
        converters.add(stringConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor());
        //registry.addInterceptor(new TestInterceptor());
    }
}
