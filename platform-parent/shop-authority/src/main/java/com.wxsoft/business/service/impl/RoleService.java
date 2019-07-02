package com.wxsoft.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxsoft.business.dao.IRoleDao;
import com.wxsoft.business.pojo.Role;
import com.wxsoft.business.service.IRoleService;

@Service("roleService")
public class RoleService implements IRoleService {
	@Resource
	private IRoleDao roleDao;
	public int addRole(Role role) {
		return this.roleDao.addRole(role);
	}
	public int deleteById(Integer id) {
		int count = this.roleDao.deleteById(id);
		return count;
	}
	public int updateRole(Role role) {
		return this.roleDao.updateRole(role);
	}
	public Role findRoleById(Integer id) {
		return this.roleDao.findRoleById(id);
	}
	public List<Role> findAllRole(int i,int j) {
	    Map<String,Integer> params = new HashMap<String,Integer>();  
        params.put("pageSize", j);  
        params.put("offset", i); 
   	    return this.roleDao.findAllRole(params);
	}
	public Long findCount() {
		return this.roleDao.findCount();
	}
	public List<Role> findAll() {
		return this.roleDao.findAll();
	}


}
