package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ybjszxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IYbjszxxDao {


	long findCount(@Param("ybjszxx") Ybjszxx ybjszxx);

	List<Ybjszxx> findAll(@Param("page") PageHelper page, @Param("ybjszxx") Ybjszxx ybjszxx);
	
	public int insert(Ybjszxx ybjszxx);
	
	public int modify(Ybjszxx ybjszxx);
	
	public int delete(Ybjszxx ybjszxx);
	
}
