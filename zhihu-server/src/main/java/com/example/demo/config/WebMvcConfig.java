package com.example.demo.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //windows本地文件目录
            registry.addResourceHandler("/zhiphoto/**").addResourceLocations("file:D:/xuexi/zhiphoto/");
        }
    }

//两个shift可以全局搜索
