package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.PaylogJz;

public interface IPaylogJzDao {


	long findCount(@Param("paylogJz") PaylogJz paylogJz);

	List<PaylogJz> findAll(@Param("page") PageHelper page, @Param("paylogJz") PaylogJz paylogJz);
	
	public int insert(@Param("paylogJz") PaylogJz paylogJz);
	
	public int modify(@Param("paylogJz") PaylogJz paylogJz);
	
	public int delete(@Param("paylogJz") PaylogJz paylogJz);
	
}
