package com.wxsoft.business.web;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StoreCheck;
import com.wxsoft.business.service.IStoreCheckService;
import com.wxsoft.util.ExcelUtil;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.UserUtil;
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
@RequestMapping("/storeCheck")
public class StoreCheckController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IStoreCheckService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "storeCheck/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, StoreCheck storeCheck,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(storeCheck));
		page.setSort("crrcheckdate");
		page.setOrder("desc");
		List<StoreCheck> storeCheckList = service.findAll(page, storeCheck);
		dg.setRows(storeCheckList);
		JsonUtil.toResponse(response, dg);

	}
	@RequestMapping("/exportStoreCheckExcel")
	public void exportStoreCheckExcel(HttpServletRequest request,PageHelper page,StoreCheck storeCheck,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

		String title="kcpd";
		String[] headers = {"药品条码","药品名称","通用名","期初库存","入库数量",
				"销售数量","最新库存","当前实际库存","本次盘点日期","上次盘点日期",
				"盘点人","盘点日期","是否最后一次盘点","上次盘点ID","医保对应关系",
				"药品内部代码"};
		storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.findCount(storeCheck);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<StoreCheck> storeCheckList = service.findAll(page, storeCheck);
		for(int j=1;j<storeCheckList.size()+1;j++){
			StoreCheck sc = storeCheckList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			
			map.put("药品条码", sc.getBarcode());
			map.put("药品名称", sc.getDrugsname());
			map.put("通用名", sc.getCommonname());
			map.put("期初库存", sc.getInitstore());
			map.put("入库数量", sc.getInstore());
			map.put("销售数量", sc.getSalecount());
			map.put("最新库存", sc.getNewstore());
			map.put("当前实际库存", sc.getCurrstore());
			map.put("本次盘点日期", sc.getCrrcheckdate());
			map.put("上次盘点日期", sc.getLastcheckdate());
			map.put("盘点人", sc.getChecker());
			map.put("盘点日期", sc.getCheckdate());
			map.put("是否最后一次盘点", sc.getIslastcheck());
			map.put("上次盘点ID", sc.getLastcheckid());
			map.put("医保对应关系", sc.getBxdygx());
			map.put("药品内部代码", sc.getDrugscode());
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, StoreCheck storeCheck) {
		try {
			storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.add(storeCheck);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, StoreCheck storeCheck) {
		try {
			storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.modify(storeCheck);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, StoreCheck storeCheck) {
		try {
			storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.delete(storeCheck);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 定期盘点库存
	 * @param request
	 * @param response
	 * @param storeCheck
	 */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response, StoreCheck storeCheck) {
		try {
			storeCheck.setChecker(UserUtil.getUser(request).getUsername());
			storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.check(storeCheck);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param storeCheck
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSCByDate", method = RequestMethod.POST)
	public void deleteSCByDate(HttpServletRequest request,HttpServletResponse response, StoreCheck storeCheck) {
		try {
			storeCheck.setChecker(UserUtil.getUser(request).getUsername());
			storeCheck.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.deleteSCByDate(storeCheck);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
}
