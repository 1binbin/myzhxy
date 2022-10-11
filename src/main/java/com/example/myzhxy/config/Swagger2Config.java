package com.example.myzhxy.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/10-21:21
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    public Docket webApiConfig() {
//        添加head参数start
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("userId")
                .description("用户ID")
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        ParameterBuilder tmpPar = new ParameterBuilder();
        tmpPar.name("userTempId")
                .description("临时用户ID")
                .defaultValue("1")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tmpPar.build());

//        添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("atguigu", "http://atguigu.com", "512111559@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文描述了后台管理系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("atguigu", "http://atguigu.com", "512111559@qq.com"))
                .build();
    }
}
