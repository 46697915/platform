package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.StorecheckJz;

public interface IStorecheckJzDao {


	long findCount(@Param("storecheckJz") StorecheckJz storecheckJz);

	List<StorecheckJz> findAll(@Param("page") PageHelper page, @Param("storecheckJz") StorecheckJz storecheckJz);
	
	public int insert(@Param("storecheckJz") StorecheckJz storecheckJz);
	
	public int modify(@Param("storecheckJz") StorecheckJz storecheckJz);
	
	public int delete(@Param("storecheckJz") StorecheckJz storecheckJz);
	
}
