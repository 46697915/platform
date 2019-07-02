package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;

public interface IGoodsForStoreDao {


	long findCount(@Param("goods") Goods goods);
	public Integer getLastId(@Param("goods") Goods goods);
	List<Goods> findAll(@Param("page") PageHelper page, @Param("goods") Goods goods);
	List<Goods> findBy(@Param("goods") Goods goods);
	
	public int insert(@Param("goods") Goods goods);
	
	public int modify(@Param("goods") Goods goods);
	
	public int delete(@Param("goods") Goods goods);

	Goods findById(@Param("goods") Goods goods);
	
}
