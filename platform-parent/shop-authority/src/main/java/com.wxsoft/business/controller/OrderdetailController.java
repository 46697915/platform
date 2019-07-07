package com.wxsoft.business.controller;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.OrderdetailSummary;
import com.wxsoft.business.service.IOrderdetailService;
import com.wxsoft.util.ExcelUtil;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/orderdetail")
public class OrderdetailController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IOrderdetailService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "orderdetail/list";
	}

	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Orderdetail orderdetail,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<Orderdetail> orderdetailList ;
		
		orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(orderdetail));
		orderdetailList = service.findAll(page, orderdetail);
		dg.setRows(orderdetailList);
		JsonUtil.toResponse(response, dg);

	}

	/**
	 * 订单销售数据汇总
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/salesSummary", method = RequestMethod.GET)
	public String salesSummary(Model model) {
		return "orderdetail/salesSummary";
	}
	/**
	 * 订单销售数据汇总返回数据
	 * @param request
	 * @param page
	 * @param orderdetail
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/salesSummaryData")
	public void salesSummaryData(HttpServletRequest request,PageHelper page, Orderdetail orderdetail,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<OrderdetailSummary> orderdetailList ;
		
		orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.salesSummaryCount(orderdetail));
		orderdetailList = service.salesSummaryAll(page, orderdetail);
		dg.setRows(orderdetailList);
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 订单销售数据汇总导出
	 * @param request
	 * @param page
	 * @param orderdetail
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,PageHelper page, Orderdetail orderdetail,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
		String title="销售汇总";
		String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
		orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.salesSummaryCount(orderdetail);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<OrderdetailSummary> orderdetailList = service.salesSummaryAll(page, orderdetail);
		for(int j=1;j<orderdetailList.size()+1;j++){
			OrderdetailSummary o=orderdetailList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			map.put("通用名", o.getCommonname());
			map.put("数量", o.getTotal());
			map.put("总金额", o.getAmount());
			map.put("规格", o.getSpecifications());
			map.put("单位", o.getUnitsname());
			map.put("条形码", o.getBarcode());
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}

	/**
	 * 订单销售数据汇总(医保和现金)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/salesAllSummary", method = RequestMethod.GET)
	public String salesAllSummary(Model model) {
		return "orderdetail/salesAllSummary";
	}
	/**
	 * 订单销售数据汇总返回数据(医保和现金)
	 * @param request
	 * @param page
	 * @param orderdetail
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/salesAllSummaryData")
	public void salesAllSummaryData(HttpServletRequest request,PageHelper page, Orderdetail orderdetail,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<OrderdetailSummary> orderdetailList ;
		
		orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.salesAllSummaryCount(orderdetail));
		orderdetailList = service.salesAllSummaryAll(page, orderdetail);
		dg.setRows(orderdetailList);
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 订单销售数据汇总导出(医保和现金)
	 * @param request
	 * @param page
	 * @param orderdetail
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportAllExcel")
	public void exportAllExcel(HttpServletRequest request,PageHelper page, Orderdetail orderdetail,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
//		String title="所有销售汇总"+orderdetail.getCreatedate_begin()+"至"+orderdetail.getCreatedate_end();	//入库汇总
		String title="SYSSHZ"+orderdetail.getCreatedate_begin()+"TO"+orderdetail.getCreatedate_end();
		String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
		orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.salesAllSummaryCount(orderdetail);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<OrderdetailSummary> orderdetailList = service.salesAllSummaryAll(page, orderdetail);
		for(int j=1;j<orderdetailList.size()+1;j++){
			OrderdetailSummary o=orderdetailList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			map.put("通用名", o.getCommonname());
			map.put("数量", o.getTotal());
			map.put("总金额", o.getAmount());
			map.put("规格", o.getSpecifications());
			map.put("单位", o.getUnitsname());
			map.put("条形码", o.getBarcode());
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Orderdetail orderdetail) {
		try {
			String r = "";
			
			orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.add(orderdetail);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Orderdetail orderdetail) {
		try {
			String r = "";
			
			orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(orderdetail);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Orderdetail orderdetail) {
		try {
			String r = "";
			
			orderdetail.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(orderdetail);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
