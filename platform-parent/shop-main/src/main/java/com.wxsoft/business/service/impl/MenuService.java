package com.wxsoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxsoft.business.dao.MenuMapper;
import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.service.IMenuService;

@Service("menuService")
public class MenuService implements IMenuService  {
	
	@Resource
	private MenuMapper menuMapper;
	
	/**
	 * 获取总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(SysMenu menu) {
		return menuMapper.getDatagridTotal(menu);  
	}

	/**
	 * 获取一级列表
	 * @param page
	 * @return
	 */
	public List<SysMenu> datagridMenu(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return menuMapper.datagridMenu(page);  
	}
	
	/**
	 * 获取所有列表
	 * @param page
	 * @return
	 */
	public List<SysMenu> getAll(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return menuMapper.getAll(page);  
	}

	public void deleteMenuById(Integer menuId) {
		menuMapper.deleteMenuById(menuId);
	}

	public SysMenu getMenuById(Integer menuId) {
		return menuMapper.getMenuById(menuId);
	}

	public List<SysMenu> listAllParentMenu() {
		return menuMapper.listAllParentMenu();
	}

	public void saveMenu(SysMenu menu) {
		if(menu.getId()!=null && menu.getId().intValue()>0){
			menuMapper.updateMenu(menu);
		}else{
			menuMapper.insertMenu(menu);
		}
	}

	public List<SysMenu> listSubMenuByParentid(Integer parentid) {
		return menuMapper.listSubMenuByParentid(parentid);
	}
	
	public List<SysMenu> listAllMenu() {
		List<SysMenu> rl = this.listAllParentMenu();
		for(SysMenu menu : rl){
			List<SysMenu> subList = this.listSubMenuByParentid(menu.getId());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<SysMenu> listAllSubMenu(){
		return menuMapper.listAllSubMenu();
	}
	
}
