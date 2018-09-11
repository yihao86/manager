package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UsersDao {
	public List<Map<String, Object>> TeacherByIdUsers(@Param("f_ftid") int f_ftid);
	
	public List<Map<String, Object>> SelectAll();
}
