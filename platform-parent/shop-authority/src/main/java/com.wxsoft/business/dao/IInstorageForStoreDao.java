package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;

public interface IInstorageForStoreDao {


	long findCount(@Param("instorage") Instorage instorage);
	Integer getLastId(@Param("instorage") Instorage instorage);
	List<Instorage> findAll(@Param("page") PageHelper page, @Param("instorage") Instorage instorage);
	
	public int insert(@Param("instorage") Instorage instorage);
	
	public int modify(@Param("instorage") Instorage instorage);
	
	public int delete(@Param("instorage") Instorage instorage);
	
}
