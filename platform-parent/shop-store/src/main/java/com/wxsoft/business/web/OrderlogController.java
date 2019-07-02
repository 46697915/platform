package com.wxsoft.business.web;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;
import com.wxsoft.business.service.IOrderlogService;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.common.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/orderlog")
public class OrderlogController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IOrderlogService service;
//	@Resource
//	private IOrderlogForStoreService serviceForStore;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "orderlog/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Orderlog orderlog,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<Orderlog> orderlogList ;
		
		orderlog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(orderlog));
		orderlogList = service.findAll(page, orderlog);
		dg.setRows(orderlogList);
		JsonUtil.toResponse(response, dg);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Orderlog orderlog) {
		try {
			String r = "";
			
			orderlog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			orderlog.setOperatedate(DateUtil.currentDatetime());
			r = service.add(orderlog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Orderlog orderlog) {
		try {
			String r = "";
			
			orderlog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(orderlog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Orderlog orderlog) {
		try {
			String r = "";
			
			orderlog.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(orderlog);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
