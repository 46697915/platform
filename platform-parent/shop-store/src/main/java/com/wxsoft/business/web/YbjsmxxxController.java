package com.wxsoft.business.web;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ybjsmxxx;
import com.wxsoft.business.pojo.YbjsmxxxSummary;
import com.wxsoft.business.service.IYbjsmxxxService;
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
@RequestMapping("/ybjsmxxx")
public class YbjsmxxxController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IYbjsmxxxService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "ybjsmxxx/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Ybjsmxxx ybjsmxxx,HttpServletResponse response) throws IOException{

		ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(ybjsmxxx));
		List<Ybjsmxxx> ybjsmxxxList = service.findAll(page, ybjsmxxx);
		dg.setRows(ybjsmxxxList);
		JsonUtil.toResponse(response, dg);

	}
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,PageHelper page, Ybjsmxxx ybjsmxxx,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
		String title="ybjsmx";
		String[] headers = {"医保结算号","医院项目名称","项目单价","项目数量","项目金额","划价日期"
				,"开单医生姓名","取药窗口/执行科室","处方号","处方内序号","医院项目编号"
				,"对应医保项目编码","是否更新库存","项目规格","项目单位","项目剂型"
				,"是否医保项目","每次用量","使用频次","用法","执行天数","住院号"};
		ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.findCount(ybjsmxxx);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<Ybjsmxxx> ybjsmxxxlList = service.findAll(page, ybjsmxxx);
		for(int j=1;j<ybjsmxxxlList.size()+1;j++){
			Ybjsmxxx ybxxx = ybjsmxxxlList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			map.put("医保结算号", ybxxx.getYbjsh());
			map.put("医院项目名称", ybxxx.getYyxmmc());
			map.put("项目单价", ybxxx.getXmdj());
			map.put("项目数量", ybxxx.getXmsl());
			map.put("项目金额", ybxxx.getXmje());
			map.put("划价日期", ybxxx.getHjrq());
			map.put("开单医生姓名", ybxxx.getKdysxm());
			map.put("取药窗口/执行科室", ybxxx.getZxks());
			map.put("处方号", ybxxx.getCfh());
			map.put("处方内序号", ybxxx.getCfnxh());
			map.put("医院项目编号", ybxxx.getYyxmbm());
			map.put("对应医保项目编码", ybxxx.getYbxmbm());
			map.put("是否更新库存", ybxxx.getIsupdate());
			map.put("项目规格", ybxxx.getXmgg());
			map.put("项目单位", ybxxx.getXmdw());
			map.put("项目剂型", ybxxx.getXmjx());
			map.put("是否医保项目", ybxxx.getSfybxm());
			map.put("每次用量", ybxxx.getMcyl());
			map.put("使用频次", ybxxx.getSypc());
			map.put("用法", ybxxx.getYf());
			map.put("执行天数", ybxxx.getZxts());
			map.put("住院号", ybxxx.getZyh());
			
			
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	/**
	 * 医保订单销售数据汇总
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ybSalesSummary", method = RequestMethod.GET)
	public String ybSalesSummary(Model model) {
		return "ybjsmxxx/ybSalesSummary";
	}
	/**
	 * 医保订单销售数据汇总返回数据
	 * @param request
	 * @param page
	 * @param orderdetail
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/ybSalesSummaryData")
	public void ybSalesSummaryData(HttpServletRequest request,PageHelper page,Ybjsmxxx ybjsmxxx,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<YbjsmxxxSummary> ybjsmxmmSList ;
		
		ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.ybSalesSummaryCount(ybjsmxxx));
		ybjsmxmmSList = service.ybSalesSummaryAll(page, ybjsmxxx);
		dg.setRows(ybjsmxmmSList);
		JsonUtil.toResponse(response, dg);

	}
	@RequestMapping("/exportYbSalesSummaryExcel")
	public void exportYbSalesSummaryExcel(HttpServletRequest request,PageHelper page, Ybjsmxxx ybjsmxxx,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
		String title="ybxshz";
		String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
		ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.ybSalesSummaryCount(ybjsmxxx);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<YbjsmxxxSummary> ybjsmxxxList = service.ybSalesSummaryAll(page, ybjsmxxx);
		for(int j=1;j<ybjsmxxxList.size()+1;j++){
			YbjsmxxxSummary ybxxx = ybjsmxxxList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			
			map.put("通用名", ybxxx.getCommonname());
			map.put("数量", ybxxx.getTotal());
			map.put("总金额", ybxxx.getAmount());
			map.put("规格", ybxxx.getSpecifications());
			map.put("单位", ybxxx.getUnitsname());
			map.put("条形码", ybxxx.getBarcode());
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Ybjsmxxx ybjsmxxx) {
		try {
			ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.add(ybjsmxxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Ybjsmxxx ybjsmxxx) {
		try {
			ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.modify(ybjsmxxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Ybjsmxxx ybjsmxxx) {
		try {
			ybjsmxxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.delete(ybjsmxxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
