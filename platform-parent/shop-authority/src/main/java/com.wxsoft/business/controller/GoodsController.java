package com.wxsoft.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.wxsoft.business.pojo.Goods;
import com.wxsoft.business.service.IGoodsForStoreService;
import com.wxsoft.business.service.IGoodsService;
import com.wxsoft.util.ExcelUtil;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.wx.CacheUtil;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IGoodsService service;
	@Resource
	private IGoodsForStoreService serviceForStore;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "goods/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Goods goods,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		List<Goods> goodsList;
		
		goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(goods));
		goodsList = service.findAll(page, goods);
		
		dg.setRows(goodsList);
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 获取销售数据
	 * @param request
	 * @param page
	 * @param goods
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/datagridForXS")
	public void listForXS(HttpServletRequest request,PageHelper page, Goods goods,HttpServletResponse response) throws IOException{
		
		//将数据放到缓存 或 取出
		Object cacheGoods = CacheUtil.getCache("goods_sale");
		if(cacheGoods==null || "".equals(cacheGoods)){
			this.listToCathe(request, page, goods, response);
		}
		if(goods.getBarcode()==null){
			goods.setBarcode("");
		}
		
		//从缓存中取数据
		DataGrid dgCache = (DataGrid) CacheUtil.getCache("goods_sale");
		List<Goods> goodsListC = dgCache.getRows();
		int rows = page.getRows();
		int j = 0;
		List<Goods> goodsList = new ArrayList<Goods>();
		//匹配查询数据
		for(int i=0; i<goodsListC.size(); i++){
			if(j<rows){
				Goods goods2 = goodsListC.get(i);
				if(goods2.getBarcode2() == null ){
					goods2.setBarcode2("");
				}
				if(goods2.getBarcode2().contains(goods.getBarcode()) 
//						|| goods2.getBarcode().indexOf(goods.getBarcode())>=0
						|| goods2.getGoodsname().indexOf(goods.getBarcode())>=0
						|| goods2.getCommonname().indexOf(goods.getBarcode())>=0
						|| goods2.getCommonshotspell().indexOf(goods.getBarcode().toUpperCase())>=0
					){
					
					goodsList.add(goods2);
					j++;
				}
			}else{
				break ;
			}
		}
		DataGrid dg = new DataGrid();
		dg.setTotal((long) rows);
		dg.setRows(goodsList);
		
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 更新数据到缓存
	 * @Description:TODO
	 * @author: chenliang
	 * @time:2018-4-3 上午8:14:17
	 */
	@RequestMapping("/listToCathe")
	public void listToCathe(HttpServletRequest request,PageHelper page, Goods goods,HttpServletResponse response) throws IOException{

		//将数据放到缓存
		DataGrid dg = new DataGrid();
		goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCountForXS(goods));
		PageHelper page1 = new PageHelper();
		List<Goods> goodsList = service.findAllForXS(page1, goods);
		dg.setRows(goodsList);
		CacheUtil.putCache("goods_sale", dg);
	}
		
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,PageHelper page, Goods goods,HttpServletResponse response) throws IOException{
		List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();
		
		String title="药品库存";
		String[] headers = {"药品条码","药品名称","通用名","售价","成本价","购货单位"
				,"库存数量","生产批号","生成日期","有效期","保质期"
				,"摆放位置","存储位置","药品剂型","规格","操作人"
				,"操作日期","通用名拼音简码","通用名拼音码","医保对应关系"};
		goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		long count = service.findCount(goods);
		page.setEnd((int)count);
		page.setStart(0);
		page.setPage(1);
		page.setRows((int)count);
		List<Goods> goodsList = service.findAll(page, goods);
		for(int j=1;j<goodsList.size()+1;j++){
			Goods gd = goodsList.get(j-1);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  
			map.put("药品条码", gd.getBarcode());
			map.put("药品名称", gd.getGoodsname());
			map.put("通用名", gd.getCommonname());
			map.put("售价", gd.getPrice());
			map.put("成本价", gd.getCostprice());
			map.put("购货单位", gd.getUnits());
			map.put("库存数量", gd.getStock());
			map.put("生产批号", gd.getGeneratenum());
			map.put("生成日期", gd.getGeneratedate());
			map.put("有效期", gd.getValidityperiod());
			map.put("保质期", gd.getShelflife());
			map.put("摆放位置", gd.getPosition());
			map.put("存储位置", gd.getStorage());
			map.put("药品剂型", gd.getDosageformname());
			map.put("规格", gd.getSpecificationsname());
			map.put("操作人",gd.getOperator());
			map.put("操作日期", gd.getOperatedatestr());
			map.put("通用名拼音简码", gd.getCommonshotspell());
			map.put("通用名拼音码", gd.getCommonnamespell());
			map.put("医保对应关系", gd.getBxdygx());
					
			
			listMap.add(map);
		}
		ExcelUtil.exportExcel(response, title, headers, listMap);

	}
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Goods goods) {
		try {
			goods.setOperatedate(new Date());
			Integer userId = getloginId(request, response);
			goods.setOperator(userId+"");
			String r = "";
			
			goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.add(goods);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Goods goods) {
		try {
			String r = "";
			
			goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(goods);
			
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Goods goods) {
		try {
			String r = "";
			goods.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(goods);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
