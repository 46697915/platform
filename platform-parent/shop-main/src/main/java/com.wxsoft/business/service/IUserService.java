package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.User;

public interface IUserService {
	public User getUserById(int userId);
	public void addUser(User user);
	public long findCount(User user);
	public List<User> findAllUser(PageHelper page, User user);
	public User findUserByName(String username);
	/**
	 * 获取该用户权限的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getMenu(int userId);
	public void saveOrUpdateUser(User user);
	public void deleteUser(int userId);
	public List<User> loadShops();
	//微信更新openid
	public int updateOpenidById(User user);
	public User getUserByOpenId(String openId);
	
	
}
