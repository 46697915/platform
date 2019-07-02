package com.wxsoft.business.controller;

import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.Tree;
import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IDrugStoreService;
import com.wxsoft.business.service.IUserService;
import com.wxsoft.util.RequestUtil;
import com.wxsoft.util.UserCookieUtil;
import com.wxsoft.util.common.Const;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zh
 * 2014-7-26
 */
@Controller
public class SystemController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IUserService userService;
	
	@Resource
	private IDrugStoreService service;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String home() {
		log.info("返回首页！");
		return "index";
	}
	@RequestMapping(value = "/weixin",method = RequestMethod.GET)
	public String homeWX() {
		log.info("返回微信登录页！");
		return "loninWX";
	}
	
    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,HttpServletResponse response,
    		String loginname, String password, 
    		String code,String autologinch) throws Exception{
    	if (code!=null){
    		
		//if (code!=null && code.toLowerCase().equals(request.getSession().getAttribute("RANDOMCODE").toString().toLowerCase())){
			User user = userService.findUserByName(loginname);
			if (user == null) {
				log.info("登陆用户名不存在");  
	    		request.getSession().setAttribute("message", "用户名不存在，请重新登录");
	    		return "login"; 
			}else {
				if (user.getPassword().equals(password)) {
					
					if(autologinch!=null && autologinch.equals("Y")){ // 判断是否要添加到cookie中
						// 保存用户信息到cookie
						UserCookieUtil.saveCookie(user, response);
					}
					DrugStore drugStore = new DrugStore();
					if(user.getPharmacy()!=null&&!"".equals(user.getPharmacy())){
						drugStore = service.getDrugStoreByShotN(user.getPharmacy());
					}
					request.getSession().setAttribute(Const.SESSION_DRUGSTORE, drugStore);  
					// 保存用信息到session
					request.getSession().setAttribute(Const.SESSION_USER, user);  
	        		return "redirect:" + RequestUtil.retrieveSavedRequest();//跳转至访问页面
					
				}else {
					log.info("登陆密码错误");  
	        		request.getSession().setAttribute("message", "用户名密码错误，请重新登录");
	        		return "login"; 
				}
			} 
		}else {
			request.getSession().setAttribute("message", "验证码错误，请重新输入");
    		return "login"; 
		}
    }
    
	/**
	 * 用户注销
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session,HttpServletResponse response){
		session.removeAttribute(Const.SESSION_USER);
		UserCookieUtil.clearCookie(response);
		return "redirect:/";
	}

    /**
     * 获取菜单栏(easyUI Tree)
     * @param id
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getMenu", method = RequestMethod.POST)  
    public List<Tree> getMenu(HttpSession session){  
    	User user =  (User)session.getAttribute(Const.SESSION_USER); 
    	List<SysMenu> menuList = userService.getMenu(user.getId());
    	List<Tree> treeList = new ArrayList<Tree>();

        for (SysMenu menu : menuList) {
        	
			Tree node = new Tree();
			
			node.setId(menu.getId());
			node.setPid(menu.getPid());
			node.setText(menu.getName());
			node.setIconCls(menu.getIconcls());
			
			if(menu.getPid()!=0){	// 有父节点
				node.setPid(menu.getPid());
			}
			if(menu.getCountChildrens() > 0){	//有子节点
				node.setState("closed");
			}
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url", menu.getMenu());
			node.setAttributes(attr);
			treeList.add(node);
        }
    	return treeList;
    }

	
}
