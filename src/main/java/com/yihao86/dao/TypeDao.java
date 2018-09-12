package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Type;

public interface TypeDao {
 
	public List<Type> findAll(@Param("f_trid") int f_trid);
	
	//查询所有子类
	public List<Type> findAllAnameType();
	
	public int findByTypeId(@Param("a_typeId") int a_typeId);
	
	//查询所有父节点
	public List<Type> findType();
	
	public String findNameById(@Param("typeid") int typeid);
}
