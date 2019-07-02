package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.pojo.Role;

public interface IRoleService {
	int addRole(Role role);
	
	//删除
	int deleteById(Integer id);
	
	//修改
	int updateRole(Role role);
	
	//查询
	Role findRoleById(Integer id);
	
	//查询所有
	
	List<Role> findAllRole(int i, int j);

	Long findCount();

	List<Role> findAll();

}
