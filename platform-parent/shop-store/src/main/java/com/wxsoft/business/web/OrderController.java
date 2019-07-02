package com.wxsoft.business.web;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Order;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IOrderService;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.UserUtil;
import com.wxsoft.util.common.JsonTools;
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
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService service;
//	@Resource
//	private IOrderForStoreService serviceForStore;
	@RequestMapping(value = "/quick", method = RequestMethod.GET)
	public String quick(Model model) {
		return "order/quick2";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "order/list";
	}

	/**
	 * 订单结算
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/calculateDetail", method = RequestMethod.POST)
	public void calculateDetail(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String totalMoney = request.getParameter("totalMoney");// 计算费用
			String receiveTotalMoney = request
					.getParameter("receiveTotalMoney");// 实收费用
			String rows = request.getParameter("rows");
			List<Map<String, String>> goodsDetList = JsonTools.jsonToList(rows);
			User user = UserUtil.getUser(request);
			
			String r = "";
//			DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
//			if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
//				List<Map<String, String>> list = new ArrayList<Map<String,String>>(); 
//				for(int i=0;i<goodsDetList.size();i++){
//					Map<String, String> map = goodsDetList.get(i);
//					map.put("drugStoreShortName", drugStore.getShortname());
//					list.add(map);
//				}
//				r = serviceForStore.charge(list, totalMoney, receiveTotalMoney,
//						user);
//			}else{
//				r = service.charge(goodsDetList, totalMoney, receiveTotalMoney,
//						user);
//			}
			String shortname = StoreUtil.getSSNForTable(request);
			for(int i=0;i<goodsDetList.size();i++){
				Map<String, String> map = goodsDetList.get(i);
				map.put("drugStoreShortName", shortname);
			}
			user.setDrugStoreShortName(shortname);
			r = service.charge(goodsDetList, totalMoney, receiveTotalMoney,
					user);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}

	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Order order, HttpServletResponse response)
			throws IOException {

		DataGrid dg = new DataGrid();
		List<Order> orderList;
		
		order.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(order));
		orderList = service.findAll(page, order);
		dg.setRows(orderList);
		JsonUtil.toResponse(response, dg);

	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Order order) {
		try {
			String r = "";
			
			order.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.add(order);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Order order) {
		try {
			String r = "";
			
			order.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(order);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Order order) {
		try {
			String r = "";
			
			order.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(order);
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
}
