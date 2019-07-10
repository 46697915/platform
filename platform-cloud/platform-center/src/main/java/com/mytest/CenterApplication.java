package com.mytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//扫描Mybatis接口包
@MapperScan("com.mytest.sys.dao")
/*@EnableEurekaClient    //允许当前服务向eureka注册服务
@EnableDiscoveryClient    //发现服务使用*/
public class CenterApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CenterApplication.class, args);
	}

}