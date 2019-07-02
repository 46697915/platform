package com.wxsoft.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IDrugStoreService;
import com.wxsoft.business.service.IUserService;
import com.wxsoft.util.wx.CoreService;
import com.wxsoft.util.wx.SignUtil;

@Controller
@RequestMapping("/weixincore")
public class WeiXinCoreController extends BaseController{

	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/login.html", method = RequestMethod.GET)
	public String toWeixinLogin(HttpServletRequest request){
		String openid = request.getParameter("openid");
		request.getSession().setAttribute("openid", openid);
		return "redirect:/weixin";
	}
	
	@RequestMapping(value="/loginWX", method = RequestMethod.POST)
	public String weixinLogin(HttpServletRequest request,
    		String loginname, String password,String openid, 
    		String code){
		if (code!=null && code.toLowerCase().equals(request.getSession().getAttribute("RANDOMCODE").toString().toLowerCase())){
			User user = userService.findUserByName(loginname);
			if (user == null) {
				request.getSession().setAttribute("openid", openid);
	    		request.getSession().setAttribute("errInfo", "用户名不存在，请重新登录");
			}else {
				if (user.getPassword().equals(password)) {
					if(openid!=null&&!"".equals(openid)&&!"null".equals(openid)){
						user.setOpenid(openid);
						userService.updateOpenidById(user);
						request.getSession().setAttribute("status", "success");
					}
				}
			}
			
		}else {
			request.getSession().setAttribute("errInfo", "验证码错误，请重新输入");
		}
		return "redirect:/weixin";
	}
	
	@RequestMapping(value="/queryData", method = RequestMethod.GET)
	@ResponseBody
	public String checkSignature(HttpServletRequest request,HttpServletResponse response ) {
		// 微信加密签名
		  String signature = request.getParameter("signature");
		  // 时间戳
		  String timestamp = request.getParameter("timestamp");
		  // 随机数
		  String nonce = request.getParameter("nonce");
		  // 随机字符串
		  String echostr = request.getParameter("echostr");
		  // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		  if (SignUtil.checkSignature(signature, timestamp, nonce)) {
		   return echostr;
		  }
		return "error";
	}
	@RequestMapping(value="/queryData", method = RequestMethod.POST)
	@ResponseBody
	public String replyMessage(HttpServletRequest request,HttpServletResponse response,Drugs drugs) {
		response.setContentType("application/xml");
		response.setCharacterEncoding("UTF-8");
		// 消息的接收、处理、响应  
		CoreService coreService = new CoreService();
		String responsestr = coreService.processRequest(request);
		return responsestr;
	}	
	
 
}
