package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Paylog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPaylogDao {


	long findCount(@Param("paylog") Paylog paylog);

	List<Paylog> findAll(@Param("page") PageHelper page, @Param("paylog") Paylog paylog);
	
	public int insert(Paylog paylog);
	
	public int modify(Paylog paylog);
	
	public int delete(Paylog paylog);
	
}
