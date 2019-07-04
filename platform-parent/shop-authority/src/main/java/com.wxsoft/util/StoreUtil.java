package com.wxsoft.util;

import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.util.common.Const;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

	/**
	 * 返回当前用户药店信息简称
	 * @return
	 */
	public static String getStoreShortName(){
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		DrugStore drugStore = (DrugStore)request.getSession().getAttribute(Const.SESSION_DRUGSTORE);
		if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
			return drugStore.getShortname();
		}
		return "";
	}
}
