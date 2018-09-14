package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Type;

public interface TypeService {
	
	/**
	 * 查询专辑下所有类型的方法
	 * @return
	 */
	public List<Type> selectAll(int f_trid);
	
	/**
	 * 查询父节点
	 * @return
	 */
	public List<Type> selectType();
	
	
	public String findNameById(int typeid);

	
	/**
	*根据id查询专辑类型名称
	 */
	public int findByTypeId(int a_typeId);
	
	/**
	 * 查询所有子类
	 * @return
	 */
	public List<Type> findAllAnameType();
	
	public List<Integer> findAllFrId();
	
	
	public Map<Integer,Object> findAllType();
}
