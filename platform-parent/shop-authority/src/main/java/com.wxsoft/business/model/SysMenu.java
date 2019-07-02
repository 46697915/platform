/**
 * 
 */
package com.wxsoft.business.model;

import java.util.List;

/**
 * @author zh
 * 2014-8-1
 */
public class SysMenu {
	/**
	 * 菜单ID
	 */
	private Integer id;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 父级菜单ID 0表示根节点
	 */
	private Integer pid;
	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", name=" + name + ", pid=" + pid
				+ ", ordernum=" + ordernum + ", iconcls=" + iconcls + ", menu="
				+ menu + ", enable=" + enable + ", countChildrens="
				+ countChildrens + ", parentMenu=" + parentMenu + ", subMenu="
				+ subMenu + ", hasMenu=" + hasMenu + "]";
	}
	/**
	 * 菜单顺序
	 */
	private String ordernum;
	/**
	 * 菜单图标样式
	 */
	private String iconcls;	
	/**
	 * 菜单链接地址
	 */
	private String menu;
	/**
	 * 可用状态
	 */
	private Integer enable;
	/**
	 * 子节点个数
	 */
	private Integer countChildrens;
	
	private SysMenu parentMenu;
	private List<SysMenu> subMenu;
	private boolean hasMenu = false;
	
	public Integer getCountChildrens() {
		return countChildrens;
	}
	public void setCountChildrens(Integer countChildrens) {
		this.countChildrens = countChildrens;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SysMenu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(SysMenu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public List<SysMenu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<SysMenu> subMenu) {
		this.subMenu = subMenu;
	}
	public boolean isHasMenu() {
		return hasMenu;
	}
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
}
