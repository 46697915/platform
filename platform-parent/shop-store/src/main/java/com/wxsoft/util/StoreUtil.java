package com.wxsoft.util;

import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.util.common.Const;

import javax.servlet.http.HttpServletRequest;

public class StoreUtil {

	/**
	 * 返回当前用户药店信息
	 * @param request
	 * @return
	 */
	public static DrugStore getStore(HttpServletRequest request){
		DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
		return drugStore;
	}
	/**
	 * 返回当前用户药店信息简称
	 * @param request
	 * @return
	 */
	public static String getStoreShortName(HttpServletRequest request){
		DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
		if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
			return drugStore.getShortname();
		}
		return "";
	}
	/**
	 * 返回当前用户药店信息简称 添加‘_’后缀
	 * @param request
	 * @return
	 */
	public static String getSSNForTable(HttpServletRequest request){
		DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
		if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
			return drugStore.getShortname()+"_";
		}
		return "";
	}
}
