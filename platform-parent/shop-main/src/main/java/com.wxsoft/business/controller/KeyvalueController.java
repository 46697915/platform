package com.wxsoft.business.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Keyvalue;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IKeyvalueService;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.common.Const;

@Controller
@RequestMapping("/keyvalue")
public class KeyvalueController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IKeyvalueService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "keyvalue/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(PageHelper page, Keyvalue keyvalue,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(keyvalue));
		List<Keyvalue> keyvalueList = service.findAll(page, keyvalue);
		dg.setRows(keyvalueList);
		JsonUtil.toResponse(response, dg);
	}
	
	/**
	 * 根据类型返回字段选项
	 * @param page
	 * @param keyvalue
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/kvByType")
	public void findByType(String type, HttpServletResponse response) throws IOException{
		logger.info("type: "+type);
		List<Keyvalue> keyvalueList = service.findByType(type);
		JsonUtil.toResponseNOPage(response, keyvalueList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Keyvalue keyvalue) {
		try {
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
			keyvalue.setOperator(user.getUsername());
			keyvalue.setOperatetime(new Date());
			String r = service.add(keyvalue);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Keyvalue keyvalue) {
		try {
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
			keyvalue.setOperator(user.getUsername());
			keyvalue.setOperatetime(new Date());
			String r = service.modify(keyvalue);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Keyvalue keyvalue) {
		try {
			String r = service.delete(keyvalue.getId());
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
