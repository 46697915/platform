package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.PageHelper;

public interface IMenuService {
	
	/**
	 * 获取总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(SysMenu menu);

	/**
	 * 获取一级列表
	 * @param page
	 * @return
	 */
	public List<SysMenu> datagridMenu(PageHelper page);
	
	/**
	 * 获取所有列表
	 * @param page
	 * @return
	 */
	public List<SysMenu> getAll(PageHelper page);

	public void deleteMenuById(Integer menuId);

	public SysMenu getMenuById(Integer menuId);

	public List<SysMenu> listAllParentMenu();

	public void saveMenu(SysMenu menu);

	public List<SysMenu> listSubMenuByParentid(Integer parentid);
	
	public List<SysMenu> listAllMenu();

	public List<SysMenu> listAllSubMenu();
}
