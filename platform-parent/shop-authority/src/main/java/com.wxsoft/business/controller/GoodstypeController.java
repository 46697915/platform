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

import com.wxsoft.business.service.IGoodstypeService;
import com.wxsoft.business.pojo.Goodstype;

@Controller
@RequestMapping("/goodstype")
public class GoodstypeController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IGoodstypeService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "goodstype/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(PageHelper page, Goodstype goodstype,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(goodstype));
		List<Goodstype> goodstypeList = service.findAll(page, goodstype);
		dg.setRows(goodstypeList);
		JsonUtil.toResponse(response, dg);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Goodstype goodstype) {
		try {
			String r = service.add(goodstype);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Goodstype goodstype) {
		try {
			String r = service.modify(goodstype);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Goodstype goodstype) {
		try {
			String r = service.delete(goodstype.getId());
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
