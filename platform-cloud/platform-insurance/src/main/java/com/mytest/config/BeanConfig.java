package com.mytest.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * bean配置
 * 
 * @author chenliang
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	@LoadBalanced
	/**
	 * 添加了负载均衡，默认会轮询调用服务提供者
	 * @return
	 */
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	IRule loadBalanceRule(){
		return new RoundRobinRule();
	}
}