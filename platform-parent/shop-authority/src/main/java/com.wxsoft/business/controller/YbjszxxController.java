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
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.pojo.Ybjszxx;
import com.wxsoft.business.service.IYbjszxxService;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.common.Const;

@Controller
@RequestMapping("/ybjszxx")
public class YbjszxxController extends BaseController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IYbjszxxService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "ybjszxx/list";
	}
	
	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Ybjszxx ybjszxx,HttpServletResponse response) throws IOException{

		ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		DataGrid dg = new DataGrid();
		dg.setTotal(service.findCount(ybjszxx));
		List<Ybjszxx> ybjszxxList = service.findAll(page, ybjszxx);
		dg.setRows(ybjszxxList);
		JsonUtil.toResponse(response, dg);

	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.add(ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.modify(ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			String r = service.delete(ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 获取医保结算信息
	 * @param request
	 * @param response
	 * @param ybjszxx
	 */
	@ResponseBody
	@RequestMapping(value = "/getYBJSXX", method = RequestMethod.POST)
	public void getYBJSXX(HttpServletRequest request,HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			// 获取用信息从session
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
			String r = service.getYBJSXX(user,ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 根据时间段更新医保信息
	 * @param request
	 * @param response
	 * @param ybjszxx
	 */
	@ResponseBody
	@RequestMapping(value = "/getYBJSXXBySJD", method = RequestMethod.POST)
	public void getYBJSXXBySJD(HttpServletRequest request,HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			// 获取用信息从session
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
			String r = service.getYBJSXXBySJD(user,ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 更新库存 通过日期
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateStoreByDate", method = RequestMethod.POST)
	public void updateStoreByDate(HttpServletRequest request,
			HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			// 获取用信息从session
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
			String r = service.updateStoreByDate(user,ybjszxx);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 通过选择记录，更新库存（未完成）
	 * @param request
	 * @param response
	 * @param ybjszxx
	 */
	@RequestMapping(value = "/updateStoreBySelect", method = RequestMethod.POST)
	public void updateStoreBySelect(HttpServletRequest request,
			HttpServletResponse response, Ybjszxx ybjszxx) {
		try {
			ybjszxx.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			// 获取用信息从session
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
//			String r = service.updateStoreBySelect(user,ybjszxx);
//			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	
}
