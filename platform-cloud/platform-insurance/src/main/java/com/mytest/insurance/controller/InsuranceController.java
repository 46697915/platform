package com.mytest.insurance.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InsuranceController {

	// 这里不会再自己写服务，而是通过rest调用provider的服务
	@Autowired
	private RestTemplate template;

//	private final String REST_URL_PREFIX = "http://localhost:8081";// provider的地址
	//注意此处访问的是服务名
	private final String REST_URL_PREFIX = "http://center-typeofinsurance";// provider的地址

	@GetMapping("list")
	@HystrixCommand(fallbackMethod = "hiError")
	public List<Object> list() {
		return template.getForObject(REST_URL_PREFIX + "/cbManager", List.class);
	}

	@GetMapping("saveInsurance")
	public List<Object> save() {
		List l = new ArrayList();
		Map m = new LinkedHashMap();
		m.put("msg:","先获取险种：");
		l.add(m);
		List l1 = template.getForObject(REST_URL_PREFIX + "/getXz", List.class);
		l.addAll(l1);
		m = new LinkedHashMap();
		m.put("msg:","再保存参保登记信息：");
		l.add(m);
		List l2 = template.getForObject(REST_URL_PREFIX + "/saveInsurance", List.class);
		l.add(l2);
		return l;
	}


	/**
	 * 专做错误处理
	 * @return
	 */
	public List<Object> hiError() {
		List<Object> l = new ArrayList<Object>();

		l.add("hello");
		l.add("error");

		return l;
	}
}