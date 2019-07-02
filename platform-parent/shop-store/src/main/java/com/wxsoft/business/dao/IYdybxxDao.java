package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ydybxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IYdybxxDao {


	long findCount(@Param("ydybxx") Ydybxx ydybxx);

	List<Ydybxx> findAll(@Param("page") PageHelper page, @Param("ydybxx") Ydybxx ydybxx);
	
	public int insert(Ydybxx ydybxx);
	
	public int modify(Ydybxx ydybxx);
	
	public int delete(int id);
	
	public List<Ydybxx> findBy(@Param("ydybxx") Ydybxx ydybxx);
	
}
