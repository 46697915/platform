package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.InstorageJz;

public interface IInstorageJzDao {


	long findCount(@Param("instorageJz") InstorageJz instorageJz);

	List<InstorageJz> findAll(@Param("page") PageHelper page, @Param("instorageJz") InstorageJz instorageJz);
	
	public int insert(@Param("instorageJz") InstorageJz instorageJz);
	
	public int modify(@Param("instorageJz") InstorageJz instorageJz);
	
	public int delete(@Param("instorageJz") InstorageJz instorageJz);
	
}
