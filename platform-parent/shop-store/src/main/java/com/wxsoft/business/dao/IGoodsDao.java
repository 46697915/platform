package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsDao {


	long findCount(@Param("goods") Goods goods);
	List<Goods> findAll(@Param("page") PageHelper page, @Param("goods") Goods goods);

	long findCountForXS(@Param("goods") Goods goods);
	List<Goods> findAllForXS(@Param("page") PageHelper page, @Param("goods") Goods goods);
	
	List<Goods> findBy(@Param("goods") Goods goods);
	String getStockBy(@Param("goods") Goods goods);
	
	public int insert(Goods goods);
	
	public int modify(Goods goods);

	public int modifyByBarcode(Goods goods);
	
	public int delete(@Param("goods") Goods goods);
	public int deleteBy(@Param("goods") Goods goods);

	Goods findById(Goods goods);
	
}
