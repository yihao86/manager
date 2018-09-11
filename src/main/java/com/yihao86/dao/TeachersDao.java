package com.yihao86.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;

public interface TeachersDao {
	//老师登录账号
	public Teachers findTeacher(Teachers teachers);
	
	//管理员教师信息查询
	public List<Map<String, Object>> SelectTeacherInfo();
	
	//总教师人数
	public int countTeacher();
	
	//禁用老师
	public int DisablleTeacher(@Param("tid") int tid,@Param("t_disable") int t_disable);
	
	public String findByIdName(@Param("tid") int tid);
}
