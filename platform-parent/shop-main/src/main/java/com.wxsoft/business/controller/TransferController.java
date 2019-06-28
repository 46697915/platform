package com.wxsoft.business.controller;

import java.io.IOException;
import java.util.Date;
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
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.UserUtil;

import com.wxsoft.business.service.ITransferService;
import com.wxsoft.business.pojo.Transfer;
import com.wxsoft.business.pojo.User;

@Controller
@RequestMapping("/transfer")
public class TransferController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private ITransferService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "transfer/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Transfer transfer,HttpServletResponse response) throws IOException{
		
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(transfer));
		List<Transfer> transferList = service.findAll(page, transfer);
		dg.setRows(transferList);
		JsonUtil.toResponse(response, dg);

	}
	
	/**
	 * 结转数据
	 * @Description:TODO
	 * @参数： @param request
	 * @参数： @param response
	 * @参数： @param transfer
	 * @return void  
	 * @throws
	 * @author: chenliang
	 * @time:2018-6-12 下午3:01:33
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Transfer transfer) {
		try {
			User user = UserUtil.getUser(request);
			transfer.setOperator(user.getUsername());
			transfer.setOperatedate(new Date());

			transfer.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			
			String r = service.add(transfer);
//			String r = "";
//			String[] barcode = getBarcode();
//			for (String bc : barcode){
//				transfer.setBarcode(bc);
//				r = service.add(transfer);
//			}
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping(value = "/batchAddForDel", method = RequestMethod.POST)
	public void batchAddForDel(HttpServletRequest request,HttpServletResponse response, Transfer transfer) {
		try {
			User user = UserUtil.getUser(request);
			transfer.setOperator(user.getUsername());
			transfer.setOperatedate(new Date());

			transfer.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			
			String r = service.batchAddForDel(transfer);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
	public void batchAdd(HttpServletRequest request,HttpServletResponse response, Transfer transfer) {
		try {
			User user = UserUtil.getUser(request);
			transfer.setOperator(user.getUsername());
			transfer.setOperatedate(new Date());

			transfer.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			
			String r = service.batchAdd(transfer);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	public String[] getBarcode(){
		String bc = "86905629000656,83278520000762954258,81415230004332234605,6920302211307,6939261900207,6922628404449,6955394400117,6926285811602,6931774000023,6926247950165,6921575630154,6926247950677,6922521855935,86904036000341,6951891900389,6932721511098,6938706201732,6931222601666,6922321715897,6901070385094,86903022000549,6921233215822,86902389000827,A11000420502,86900088000100,86902891000421,86901191000216,86904489000264,86905541000192,86903596000389,86903596000709,86903705000903,86903365000268,86903706000407,86903596000686,6925614220993";
		String[] split = bc.split(",");
		return split ;
	}
	public static void main(String [] s){
//		String[] barcode = getBarcode();
//		System.out.println(barcode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Transfer transfer) {
		try {
			String r = service.modify(transfer);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Transfer transfer) {
		try {
			String r = service.delete(transfer);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
