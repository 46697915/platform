package com.wxsoft.business.controller;

import java.io.IOException;
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
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;

import com.wxsoft.business.service.IPaylogService;
import com.wxsoft.business.pojo.Paylog;

@Controller
@RequestMapping("/paylog")
public class PaylogController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IPaylogService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "paylog/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Paylog paylog,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		paylog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(paylog));
		List<Paylog> paylogList = service.findAll(page, paylog);
		dg.setRows(paylogList);
		JsonUtil.toResponse(response, dg);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Paylog paylog) {
		try {
			paylog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.add(paylog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Paylog paylog) {
		try {
			paylog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.modify(paylog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Paylog paylog) {
		try {
			paylog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.delete(paylog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
