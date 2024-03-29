package com.wxsoft.business.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.DataGrid;
import com.wxsoft.business.model.easyui.Json;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.service.IMenuService;


@Controller
@RequestMapping(value = "/menu")
public class MenuController  extends BaseController {
    @Resource
    private IMenuService menuService;

    /**
     * 跳转到菜单表格页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "menu/list";
    }

    /**
     * 跳转到资源管理页面
     * @return
     */
    @RequestMapping(value = "/listtree", method = RequestMethod.GET)
    public String listTree(Model model) {
        return "menu/list_tree";
    }

    /**
     * 菜单信息-列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public DataGrid datagrid(PageHelper page, SysMenu menu) {
        DataGrid dg = new DataGrid();
        dg.setTotal(menuService.getDatagridTotal(menu));

        List<SysMenu> menuList = menuService.datagridMenu(page);
        dg.setRows(menuList);

        return dg;
    }

    /**
     * 菜单列表-树
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/treegrid", method = RequestMethod.POST)
    public void treegrid(HttpServletResponse response,PageHelper page) {
        List<SysMenu> menuList = menuService.getAll(page);
        String json = createTreeJson(menuList);
        this.write(response, json);
    }

    /**
     * 保存（新增，修改）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void add(HttpServletRequest request,HttpServletResponse response,SysMenu menu) {
    	Json json = new Json();
        try {
            menuService.saveMenu(menu);
            json.setSuccess(true);
			json.setObj(menu);
			json.setMsg("添加成功");
        } catch (Exception e) {
            this.write(response, e.getMessage());
        }
        this.write(request,response, json);

    }

    /**
     * 获取当前菜单的所有子菜单
     * @param menuId
     * @param response
     */
    @RequestMapping(value = "/sub")
    public void getSub(@RequestParam
    Integer menuId, HttpServletResponse response) {
        List<SysMenu> subMenu = menuService.listSubMenuByParentid(menuId);
        JSONArray arr = JSONArray.fromObject(subMenu);
        PrintWriter out;

        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();

            String json = arr.toString();
            out.write(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param out
     */
    @RequestMapping("/deleteMenu.do")
    public String  deleteUser(HttpServletRequest request,HttpServletResponse response,Model model) {
    	int id = Integer.parseInt(request.getParameter("menuID"));
        try {
            menuService.deleteMenuById(id);
        } catch (Exception e) {
        	
        	new RuntimeException("删除菜单失败");
        }

        return "menu/list";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    /**
      * 创建一颗树，以json字符串形式返回
      * @param list 原始数据列表
      * @return 树
      */
    private String createTreeJson(List<SysMenu> list) {
        JSONArray rootArray = new JSONArray();

        for (SysMenu menu : list) {
            if (menu.getPid() == 0 ) { //有父节点

                JSONObject rootObj = createBranch(list, menu);
                rootArray.add(rootObj);
            }
        }

        return rootArray.toString();
    }

    /**
     * 递归创建分支节点Json对象
     * @param list 创建树的原始数据
     * @param currentNode 当前节点
     * @return 当前节点与该节点的子节点json对象
     */
    private JSONObject createBranch(List<SysMenu> list, SysMenu currentNode) {
        /*
         * 将javabean对象解析成为JSON对象
         */
        JSONObject currentObj = JSONObject.fromObject(currentNode);
        JSONArray childArray = new JSONArray();

        /*
         * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
         * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
         * 节点放入当前节点的子节点列表中
         */
        for (SysMenu newNode : list) {
            if ((newNode.getPid() != 0) &&
                    (newNode.getPid().compareTo(currentNode.getId()) == 0)) {
                JSONObject childObj = createBranch(list, newNode);
                childArray.add(childObj);
            }
        }

        /*
         * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
         */
        if (!childArray.isEmpty()) {
            currentObj.put("children", childArray);
        }

        return currentObj;
    }
}
