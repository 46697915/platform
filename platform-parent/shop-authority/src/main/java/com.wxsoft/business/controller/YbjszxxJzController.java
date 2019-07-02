package com.wxsoft.business.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.util.JsonUtil;

import com.wxsoft.business.service.IYbjszxxJzService;
import com.wxsoft.business.pojo.YbjszxxJz;

@Controller
@RequestMapping("/ybjszxxJz")
public class YbjszxxJzController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IYbjszxxJzService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "ybjszxxJz/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, YbjszxxJz ybjszxxJz,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(ybjszxxJz));
		List<YbjszxxJz> ybjszxxJzList = service.findAll(page, ybjszxxJz);
		dg.setRows(ybjszxxJzList);
		JsonUtil.toResponse(response, dg);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, YbjszxxJz ybjszxxJz) {
		try {
			String r = service.add(ybjszxxJz);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, YbjszxxJz ybjszxxJz) {
		try {
			String r = service.modify(ybjszxxJz);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, YbjszxxJz ybjszxxJz) {
		try {
			String r = service.delete(ybjszxxJz);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
