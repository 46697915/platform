package com.wxsoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.dao.IUserDao;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void addUser(User user) {
		// TODO Auto-generated method stub
		//int id = user.getId();
		this.userDao.insert(user);
		//id++;
		/*if(id>8){
			//throw new RuntimeException("测试事务");
		}
		user.setId(id);

		this.userDao.insert(user);*/
	}
	public long findCount(User user) {
		return userDao.findCount(user);
	}
	public List<User> findAllUser(PageHelper page,User user) {

        List<User> r = userDao.findAll(page,user);
		return r;
	}
	public User findUserByName(String username) {

		User user = new User();
		user.setUsername(username);
		return userDao.findUserByName(user);
	}
	/**
	 * 获取该用户权限的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getMenu(int userId) {
		return userDao.getMenuByUserId(userId);  
	}
	public void saveOrUpdateUser(User user) {
		if(user.getId()!=null && user.getId().intValue()>0){
			userDao.updateById(user);
		}else{
			userDao.insert(user);
		}
		
	}
	public void deleteUser(int userId) {
		userDao.deleteByPrimaryKey(userId);
		
	}
	public List<User> loadShops() {
		return userDao.loadShops();
	}
	public int updateOpenidById(User user) {
		return userDao.updateOpenidById(user);
	}
	public User getUserByOpenId(String openId){
		return userDao.getUserByOpenId(openId);
	}
}
