package com.yihao86.service;

import java.util.List;

import com.yihao86.pojo.Type;

public interface TypeService {
	
	/**
	 * 查询所有类型的方法
	 * @return
	 */
	public List<Type> selectAll();
	
	/**
	 * 查询父类的方法
	 * @return
	 */
	public List<Type> selectType();
	
	/**
	 * 查询子类方法
	 * @param f_trid
	 * @return
	 */
	public List<Type> selectSonList(int f_trid);
	
	
	public String findNameById(int typeid);
	
	public List<Type> findAllList();
	
}
