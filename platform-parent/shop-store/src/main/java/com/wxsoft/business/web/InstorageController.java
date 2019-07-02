package com.wxsoft.business.web;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;
import com.wxsoft.business.pojo.InstorageSummary;
import com.wxsoft.business.pojo.User;
//import com.wxsoft.business.service.IInstorageForStoreService;
import com.wxsoft.business.service.IInstorageService;
import com.wxsoft.util.ExcelUtil;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.UserUtil;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/instorage")
public class InstorageController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IInstorageService service;
//	@Resource
//	private IInstorageForStoreService serviceForStore;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "instorage/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Instorage instorage,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<Instorage> instorageList;
		instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(instorage));
		instorageList = service.findAll(page, instorage);
		dg.setRows(instorageList);
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 入库汇总信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/instorageSummary", method = RequestMethod.GET)
	public String instorageSummary(Model model) {
		return "instorage/instorageSummary";
	}
	@RequestMapping("/instorageSummaryData")
	public void instorageSummaryData(HttpServletRequest request,PageHelper page, Instorage instorage,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<InstorageSummary> instorageList;
		instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.instorageSummaryCount(instorage));
		instorageList = service.instorageSummaryAll(page, instorage);
		dg.setRows(instorageList);
		JsonUtil.toResponse(response, dg);

	}
	@RequestMapping("/exportInstorageExcel")
	public void exportInstorageExcel(HttpServletRequest request,PageHelper page, Instorage instorage,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
		String title="rk";
		String[] headers = {"药品条码","药品名称","通用名","入库数量","入库类型","入库日期"
				,"入库人","入库金额","生产日期","有效期","保质期"
				,"生产批号","复核人","复合日期","备注"};
		instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.findCount(instorage);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<Instorage> instorageList = service.findAll(page, instorage);
		for(int j=1;j<instorageList.size()+1;j++){
			Instorage itg=instorageList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			map.put("药品条码",itg.getBarcode());
			map.put("药品名称",itg.getDrugsname());
			map.put("通用名",itg.getCommonname());
			map.put("入库数量",itg.getInquantity());
			map.put("入库类型",itg.getIntypename());
			map.put("入库日期",itg.getIndate());
			map.put("入库人",itg.getInperson());
			map.put("入库金额",itg.getMoney());
			map.put("生产日期",itg.getGeneratedate());
			map.put("有效期",itg.getValidityperiod());
			map.put("保质期",itg.getShelflife());
			map.put("生产批号",itg.getGeneratenum());
			map.put("复核人",itg.getReviewer());
			map.put("复合日期",itg.getReviewdate());
			map.put("备注",itg.getRemark());
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,PageHelper page, Instorage instorage,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
//		String title="入库汇总"+instorage.getIndate_begin()+"至"+instorage.getIndate_end(); //乱码问题
		String title="RKHZ"+instorage.getIndate_begin()+"TO"+instorage.getIndate_end();
		String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
		instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.instorageSummaryCount(instorage);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<InstorageSummary> instoragedetailList = service.instorageSummaryAll(page, instorage);
		for(int j=1;j<instoragedetailList.size()+1;j++){
			InstorageSummary o = instoragedetailList.get(j-1);
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
	public void add(HttpServletRequest request,HttpServletResponse response, Instorage instorage) {
		try {
			User user = UserUtil.getUser(request);
			instorage.setInperson(user.getUsername());
			instorage.setLoggingdate(DateUtil.currentDatetime());
			String r = "";
			instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.add(instorage);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Instorage instorage) {
		try {
			String r = "";
			instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(instorage);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Instorage instorage) {
		try {
			String r = "";
			instorage.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(instorage);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
