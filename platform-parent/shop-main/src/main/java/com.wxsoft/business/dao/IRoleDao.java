package com.wxsoft.business.dao;

import java.util.List;
import java.util.Map;

import com.wxsoft.business.pojo.Role;

public interface IRoleDao {
		//增加爱
		int addRole(Role role);
		
		//删除
		int deleteById(Integer id);
		
		//修改
		int updateRole(Role role);
		
		//查询
		Role findRoleById(Integer id);
		
		//查询所有
		
		List<Role> findAllRole(Map<String, Integer> params);

		Long findCount();

		List<Role> findAll();

}
