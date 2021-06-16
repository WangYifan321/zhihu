package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).
                build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("王一凡","","877801903@qq.com");
        return new ApiInfo("创新实践HDU-ZHI API文档",
                "创新实践2作业，HDU-ZHI项目文档",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

}
