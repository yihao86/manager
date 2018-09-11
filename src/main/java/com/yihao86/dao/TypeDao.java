package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Type;

public interface TypeDao {
 
	public List<Type> findAll();
	
	public List<Type> findType();
	
	public List<Type> findSonList(@Param("f_trid") int f_trid);
	
	public String findNameById(@Param("typeid") int typeid);
	
	public List<Type> findAllList();
}
