package com.mytest;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 * @author chenliang
 *
 */
@SpringBootApplication
@EnableEurekaClient
//如果使用的服务只有一个，或者所有服务的配置（负载均衡算法等）都是一样的，就不需要具体写明了
//@RibbonClients(value={@RibbonClient(name="center-typeofinsurance",configuration=BeanConfig.class)})
@EnableHystrix    // 断路器
public class InsuranceApplication {

	public static void main(String[] s){
		SpringApplication.run(InsuranceApplication.class, s);
	}

}