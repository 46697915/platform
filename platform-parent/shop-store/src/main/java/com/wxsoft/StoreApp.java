package com.wxsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//扫描Mybatis接口包(有@Mapper注解不用增加)
@MapperScan("com.wxsoft.business.dao")
public class StoreApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(StoreApp.class, args);
    }
}
