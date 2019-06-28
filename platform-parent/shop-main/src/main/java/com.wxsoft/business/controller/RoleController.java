package com.wxsoft.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxsoft.business.model.easyui.Json;
import com.wxsoft.business.pojo.Role;
import com.wxsoft.business.service.IRoleService;
import com.wxsoft.util.JsonUtil;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IRoleService roleService;
	 /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "role/list";
    }
    @ResponseBody
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request,HttpServletResponse response,Role role){
    		Json json = new Json();
	        try {
	        	roleService.addRole(role);
	        	json.setSuccess(true);
				json.setObj(role);
				json.setMsg("添加成功");
				this.writeReturn(request,response, "添加成功");
	        } catch (Exception e) {
	        	this.write(response, e.getMessage());
	        }
//	        this.write(request,response, json);

	}
	@RequestMapping("/datagrid")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		Map<String, Object> pagingData = new HashMap<String, Object>();
		pagingData.put("total", roleService.findCount());
		pagingData.put("rows", roleService.findAllRole(1, 2));
		
		JsonUtil.toResponse(response, pagingData);
		return "/role/list";
	}
	@ResponseBody
	@RequestMapping(value = "/editRole", method = RequestMethod.POST)
	public void editRole(HttpServletRequest request,HttpServletResponse response,Role role){
		Json j = new Json();
		try {
			roleService.updateRole(role);
			j.setSuccess(true);
			j.setObj(role);
			j.setMsg("修改成功");
			this.writeReturn(request,response, "修改成功");
		} catch (Exception e) {
			this.write(response, e.getMessage());
		}
//		this.write(request,response, j);
	}
	@ResponseBody
	@RequestMapping("/deleteRole")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,Role role){
		Json j = new Json();
		try {
			roleService.deleteById(role.getId());
			j.setSuccess(true);
			j.setObj(role);
			j.setMsg("删除成功");
			this.writeReturn(request,response, "删除成功");
		} catch (Exception e) {
			this.write(response, e.getMessage());
		}
//		this.write(request,response, j);
	}
	
	@RequestMapping("/listAll")
	
	public String listAll(HttpServletRequest request,HttpServletResponse response){
		List<Role> list = roleService.findAll();
		request.setAttribute("list", list);
		
		return null;
	}
}
