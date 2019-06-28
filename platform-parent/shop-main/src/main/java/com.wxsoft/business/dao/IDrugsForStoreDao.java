package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.Drugs;

public interface IDrugsForStoreDao {


	long findCount(@Param("drugs") Drugs drugs);
	Integer getLastId(@Param("drugs") Drugs drugs);
	List<Drugs> findAll(@Param("page") PageHelper page, @Param("drugs") Drugs drugs);
	List<Drugs> findBy(@Param("drugs") Drugs drugs);
	
	public int insert(@Param("drugs") Drugs drugs);
	
	public int modify(@Param("drugs") Drugs drugs);
	
	public int delete(@Param("drugs") Drugs drugs);
	
}
