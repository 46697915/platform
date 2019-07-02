package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.Goods;
import com.wxsoft.business.pojo.Ydybxx;

public interface IYdybxxDao {


	long findCount(@Param("ydybxx") Ydybxx ydybxx);

	List<Ydybxx> findAll(@Param("page") PageHelper page, @Param("ydybxx") Ydybxx ydybxx);
	
	public int insert(Ydybxx ydybxx);
	
	public int modify(Ydybxx ydybxx);
	
	public int delete(int id);
	
	public List<Ydybxx> findBy(@Param("ydybxx") Ydybxx ydybxx);
	
}
