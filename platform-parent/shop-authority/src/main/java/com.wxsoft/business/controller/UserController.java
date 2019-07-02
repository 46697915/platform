package com.wxsoft.business.controller;

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
import com.wxsoft.business.model.easyui.Json;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IUserService;
import com.wxsoft.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private IUserService userService;

	/**
	 * 跳转到列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "user/list";
	}

	@RequestMapping("/showUser.do")
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "list";
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, Model model) {
		return "list";
	}

	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request,
			HttpServletResponse response, User user) {
		String username = getloginName(request, response);
		Json j = new Json();
		String r = "";
		try {
			if (userService.findUserByName(user.getUsername()) != null) {
				j.setSuccess(false);
				j.setMsg("已经存在该用户名！");
				j.setObj(user);
				r = "已经存在该用户名！";
			} else {
				user.setUpdater(username);
				user.setCreator(username);
				userService.addUser(user);
				j.setSuccess(true);
				j.setMsg("保存成功！");
				j.setObj(user);
				r = "保存成功！";
			}
		} catch (Exception e) {
			this.write(response, e.getMessage());
		}

		this.writeReturn(request,response, r);
//		this.write(request, response, j);
	}

	@RequestMapping("/datagrid")
	public String list(PageHelper page, User user,
			HttpServletResponse response, Model model) {

		DataGrid dg = new DataGrid();
		dg.setTotal(userService.findCount(user));
		List<User> userList = userService.findAllUser(page, user);
		dg.setRows(userList);
		JsonUtil.toResponse(response, dg);

		return "/user/list";
	}

	@RequestMapping("/loadshops")
	public String loadshops(PageHelper page, User user,
			HttpServletResponse response, Model model) {
		List<User> list = userService.loadShops();

		JsonUtil.toResponse(response, list);
		/*
		 * List<Map<Integer,String>> lists =new ArrayList<Map<Integer,String>>();
		 * for (int i = 0; i < list.size(); i++) { Map<Integer,String> map =
		 * new HashMap<Integer, String>(); map.put(list.get(i).getId(),
		 * list.get(i).getUsername()); lists.add(map); } JSONArray jsons =
		 * JSONArray.fromObject(lists); DataGrid dg = new DataGrid();
		 * dg.setTotal(userService.findCount(user)); List<User> userList =
		 * userService.findAllUser(page, user); dg.setRows(userList);
		 * JsonUtil.toResponse(response, jsons);
		 */

		return "";
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void saveOrUpdateUser(HttpServletRequest request,
			HttpServletResponse response, User user) {
		String username = getloginName(request, response);
		Json j = new Json();
		try {
			user.setUpdater(username);
			userService.saveOrUpdateUser(user);
			j.setSuccess(true);
			j.setMsg("操作成功！");
			j.setObj(user);
			this.writeReturn(request, response, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
			this.write(request, response, j);
		}
		// this.writeReturn(response, r);
//		this.write(request, response, j);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(HttpServletRequest request,
			HttpServletResponse response, User user, Model model) {
		Json j = new Json();
		logger.debug("穿过来的用户ID为：" + user.getId());
		try {
			userService.deleteUser(user.getId());

			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			new RuntimeException("删除失败");
		}
		this.write(request, response, j);
		return "/user/list";
	}
}
