package com.wxsoft.business.controller;

import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IDrugsForStoreService;
import com.wxsoft.business.service.IDrugsService;
import com.wxsoft.util.JsonUtil;
import com.wxsoft.util.StoreUtil;
import com.wxsoft.util.UserUtil;
import com.wxsoft.util.wx.CacheUtil;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugsController extends BaseController{

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private IDrugsService service;

	@Resource
	private IDrugsForStoreService serviceForStore;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "drugs/list";
	}

	@RequestMapping("/datagrid")
	public void list(HttpServletRequest request,PageHelper page, Drugs drugs,HttpServletResponse response) throws IOException{

		DataGrid dg = new DataGrid();
		//dg.setTotal(service.findCount(drugs));
		List<Drugs> userList;// = service.findAll(page, drugs);

//		DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
//		if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
//			drugs.setDrugStoreShortName(drugStore.getShortname());
//			dg.setTotal(serviceForStore.findCount(drugs));
//			userList = serviceForStore.findAll(page, drugs);
//		}else{
//			dg.setTotal(service.findCount(drugs));
//			userList = service.findAll(page, drugs);
//		}
		drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		dg.setTotal(service.findCount(drugs));
		userList = service.findAll(page, drugs);

		dg.setRows(userList);
		JsonUtil.toResponse(response, dg);

	}
	/**
	 * 根据一些条件查询药品表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findBy")
	public void findBy(HttpServletRequest request,Drugs drugs, HttpServletResponse response) throws IOException{
		List<Drugs> keyvalueList;// = service.findBy(drugs);

//		drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
//		keyvalueList = service.findBy(drugs);

		//将数据放到缓存 或 取出
		Object cacheDrugs = CacheUtil.getCache("drugs_list");
		if(cacheDrugs==null || "".equals(cacheDrugs)){
			this.drugsToCathe(request, drugs);
		}
		keyvalueList = (List<Drugs>) cacheDrugs;

		JsonUtil.toResponseNOPage(response, keyvalueList);
	}
	/**
	 * 将药品信息放入缓存
	 * @Description:TODO
	 * @参数： @param request
	 * @参数： @param page
	 * @参数： @param goods
	 * @参数： @param response
	 * @参数： @throws IOException
	 * @return void
	 * @throws
	 * @author: chenliang
	 * @time:2018-10-1 上午11:02:26
	 */
	public void drugsToCathe(HttpServletRequest request,Drugs drugs) throws IOException{

		//将数据放到缓存
		List<Drugs> keyvalueList;// = service.findBy(drugs);

		drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
		keyvalueList = service.findBy(drugs);

		CacheUtil.putCache("drugs_list", keyvalueList);
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response, Drugs drugs) {
		try {
			User user = UserUtil.getUser(request);
			drugs.setOperator(user.getUsername());
			drugs.setOperatedate(new Date());
			String r = "";

			drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.add(drugs);

			//将缓存清空
			CacheUtil.putCache("drugs_list", null);

			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void modify(HttpServletRequest request,HttpServletResponse response, Drugs drugs) {
		try {
			String r ="";
			drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.modify(drugs);

			//将缓存清空
			CacheUtil.putCache("drugs_list", null);

			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response, Drugs drugs) {
		try {
			String r ="";
			drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(drugs,request);

			//将缓存清空
			CacheUtil.putCache("drugs_list", null);

			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	/**
	 * 停用某个药品
	 * @Description:TODO
	 * @author: chenliang
	 * @time:2018-3-17 下午8:05:07
	 */
	@ResponseBody
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(HttpServletRequest request,HttpServletResponse response, Drugs drugs) {
		try {
			String r ="";
			drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.delete(drugs,request);
//			String[] barcode = getBarcode();
//			for (String bc : barcode){
//				Drugs d = new Drugs();
//				d.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
//				d.setBarcode(bc);
//				service.delete(d,request);
//			}
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}



	/**
	 * 更新某个药品 的 库存  后台使用
	 * @Description:TODO
	 * @参数： @param request
	 * @参数： @param response
	 * @参数： @param drugs
	 * @return void
	 * @throws
	 * @author: chenliang
	 * @time:2018-7-1 下午1:14:01
	 */
	@ResponseBody
	@RequestMapping(value = "/updateStore", method = RequestMethod.POST)
	public void updateStore(HttpServletRequest request,HttpServletResponse response, Drugs drugs) {
		try {
			String r ="";
			drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
			r = service.updateStore(drugs,request);
//			String[] barcode = getBarcode();
//			for (String bc : barcode){
//				Drugs d = new Drugs();
//				d.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
//				d.setBarcode(bc);
//				service.updateStore(d,request);
//			}
			this.writeReturn(request,response, r);
		} catch (Exception e) {
			e.printStackTrace();
			this.writeReturn(request,response, e.getMessage());
		}
	}
	public String[] getBarcode(){
		String bc = "6901591114807,6934309400824,86902950000294,6930205910030,6935127803231,6926482261262,6924168200321,6901070384981,6940777520131,6901070383946,6923878310320,6926893501001,81744220013722191637,81270200965127948756,86903592000499,86904082001026,86900600000069,Y-Z015,86978470000472,Y-C030,86978996000338,86903592000741,86902848000580,86902860000155,86978637000123,86900415000858,86903498000548,86902175000529,Y-S005,Y-T015,Y-S029,Y-M021,86902621000189,86900415000872,86902667000273,Y-G024,C11000369872,86900676000505,Y-C013,Y-J004,Y-Z004,86978178000217,86902944001306,86902120000208,99999900000397,Y-B019,Y-H044,86905583000235,86905040000105,Y-Q029,Y-Y017,86904077000102,86900830000259,Y-C080,86900166000886,86978239000023,86903004000123,B11000219061,86902729001767";
		String[] split = bc.split(",");
		return split ;
	}
}
