package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Keyvalue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IKeyvalueDao {


	long findCount(@Param("keyvalue") Keyvalue keyvalue);

	List<Keyvalue> findAll(@Param("page") PageHelper page, @Param("keyvalue") Keyvalue keyvalue);

	List<Keyvalue> findByType(@Param("type") String type);
	
	public int insert(Keyvalue keyvalue);
	
	public int modify(Keyvalue keyvalue);
	
	public int delete(int id);
	
}
