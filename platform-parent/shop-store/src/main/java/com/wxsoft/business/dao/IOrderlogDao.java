package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderlogDao {


	long findCount(@Param("orderlog") Orderlog orderlog);

	List<Orderlog> findAll(@Param("page") PageHelper page, @Param("orderlog") Orderlog orderlog);
	
	public int insert(Orderlog orderlog);
	
	public int modify(Orderlog orderlog);
	
	public int delete(Orderlog orderlog);
	
}
