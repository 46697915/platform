package com.wxsoft.common.mapper;


import com.wxsoft.common.entity.Keyvalue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IKeyvalueDao {


	long findCount(@Param("keyvalue") Keyvalue keyvalue);


	List<Keyvalue> findByType(@Param("type") String type);
	
	public int insert(Keyvalue keyvalue);
	
	public int modify(Keyvalue keyvalue);
	
	public int delete(int id);
	
}
