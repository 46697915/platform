package com.wxsoft.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.model.SysMenu;
import com.wxsoft.business.pojo.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

	public User findUserByName(User record);

	public List<SysMenu> getMenuByUserId(int userId);
	
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);
    int updateById(User record);
    int updateByPrimaryKey(User record);

	long findCount(@Param("user") User user);

	List<User> findAll(@Param("page") PageHelper page, @Param("user") User user);

	List<User> loadShops();
	
	int updateOpenidById(User user);
	public User getUserByOpenId(String openId);

}