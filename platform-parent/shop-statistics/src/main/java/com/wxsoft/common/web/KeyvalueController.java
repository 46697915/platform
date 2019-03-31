package com.wxsoft.common.web;

import com.wxsoft.common.entity.Keyvalue;
import com.wxsoft.common.service.IKeyvalueService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/keyvalue")
public class KeyvalueController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IKeyvalueService service;

	/**
	 * 根据类型返回字段选项
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/kvByType")
	public List findByType(String type, HttpServletResponse response) throws IOException{
		logger.info("type: "+type);
		List<Keyvalue> keyvalueList = service.findByType(type);
		return keyvalueList ;
	}

}
