package com.wxsoft.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描Mybatis接口包(有@Mapper注解不用增加)
@MapperScan("com.wxsoft.shiro.business.**.mapper")
public class ShrioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShrioApplication.class,args);
    }
}
