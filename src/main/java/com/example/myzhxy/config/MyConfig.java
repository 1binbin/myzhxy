package com.example.myzhxy.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hongxiaobin
 * @Time 2022/10/10-21:16
 */
@Configuration
@MapperScan("com.example.myzhxy.mapper")
public class MyConfig {


    /** 分页插件
     * @Param:
     * @Return:
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
