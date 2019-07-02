package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goodstype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodstypeDao {


	long findCount(@Param("goodstype") Goodstype goodstype);

	List<Goodstype> findAll(@Param("page") PageHelper page, @Param("goodstype") Goodstype goodstype);
	
	public int insert(Goodstype goodstype);
	
	public int modify(Goodstype goodstype);
	
	public int delete(int id);
	
}
