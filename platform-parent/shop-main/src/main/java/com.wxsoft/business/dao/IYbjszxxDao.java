package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.Ybjszxx;
import com.wxsoft.business.pojo.YbjszxxJz;

public interface IYbjszxxDao {


	long findCount(@Param("ybjszxx") Ybjszxx ybjszxx);

	List<Ybjszxx> findAll(@Param("page") PageHelper page, @Param("ybjszxx") Ybjszxx ybjszxx);
	
	public int insert(Ybjszxx ybjszxx);
	
	public int modify(Ybjszxx ybjszxx);
	
	public int delete(Ybjszxx ybjszxx);
	
}
