package com.wxsoft.business.controller;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.Json;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.business.service.IDrugStoreService;
import com.wxsoft.util.JsonUtil;
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
import java.util.List;

@Controller
@RequestMapping("/drugStore")
public class DrugStoreController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IDrugStoreService service;

	private static List<String> tableList = new ArrayList<String>();
	static{
		tableList.add("drugs");
		tableList.add("drugs_del");
		tableList.add("goods");
		tableList.add("goods_del");
		tableList.add("goodstype");
		tableList.add("instorage");
		tableList.add("m_storecheck");
		tableList.add("m_ybjsmxxx");
		tableList.add("m_ybjsmxxx_del");
		tableList.add("m_ybjszxx");
		tableList.add("m_ybjszxx_del");
		tableList.add("orderdetail");
		tableList.add("orderlog");
		tableList.add("paylog");
		tableList.add("t_order");

	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "drugStore/list";
	}
	@RequestMapping("/findBy")
	public void findBy(HttpServletRequest request,DrugStore drugStore, HttpServletResponse response) throws IOException{
		List<DrugStore> keyvalueList;// = service.findBy(drugs);
		keyvalueList = service.findBy(drugStore);
		JsonUtil.toResponseNOPage(response, keyvalueList);
	}
	@RequestMapping("/datagrid")
	public void list(PageHelper page, DrugStore drugStore,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(drugStore));
		List<DrugStore> drugStoreList = service.findAll(page, drugStore);
		dg.setRows(drugStoreList);
		JsonUtil.toResponse(response, dg);
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, DrugStore drugStore) {
		Integer userId = getloginId(request, response);
		Json j = new Json();
		try {
			String Agent = request.getHeader("User-Agent");
			if(Agent.indexOf("ie")>=0){
				System.out.print(1);
			}
			//Date now = new Date();
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
			drugStore.setCreatetime(DateUtil.currentDatetime());//  dateFormat.format( now ) );
			drugStore.setCreator(userId);
			//简称即药店编码
			drugStore.setStorecode(drugStore.getShortname());
			service.add(drugStore);
			
			
//			service.addTable(tableList,drugStore.getShortname());
			j.setSuccess(true);
			j.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
			this.write(request,response, j);
		}
		this.write(request,response, j);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, DrugStore drugStore) {
		Integer userId = getloginId(request, response);
		Json j = new Json();
		try {
			//Date now = new Date();
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
			drugStore.setUpdater(userId);
			drugStore.setUpdatetime(DateUtil.currentDatetime());
			service.modify(drugStore);
			j.setSuccess(true);
			j.setMsg("操作成功！");
			//this.writeReturn(response, r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			this.write(request,response, j);
			//e.printStackTrace();
			//this.writeReturn(response, e.getMessage());
		}
		this.write(request,response, j);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, DrugStore drugStore) {
		Json j = new Json();
		try {
			drugStore = service.getDrugStoreById(drugStore.getId()+"");
			String a = service.deleteTables(tableList,drugStore.getShortname());
		} catch (Exception e) {
			System.out.print(e);
		}
		try {
			String r = service.delete(drugStore.getId());
			System.out.print(r);
			j.setSuccess(true);
	        j.setMsg("删除成功！");
		} catch (Exception e) {
			new RuntimeException("删除失败");
			j.setMsg(e.getMessage());
			this.write(request,response, j);
		}
		
		this.write(request,response, j);
	}
}
