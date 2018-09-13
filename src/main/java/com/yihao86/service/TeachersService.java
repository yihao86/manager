package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Teachers;

public interface TeachersService {

	public Teachers findTeacher(String skey,Teachers teachers);
	
	public List<Map<String, Object>> findTeacherInfo();
	
	public int countTeacher();
	
	public boolean DisablleTeacher(int tid,int t_disable);
	
	public String findByIdName(int tid);
	
	//注册教师账号
	Teachers RegisterTeacher(Teachers teacher);
	
	//发送邮件
	void eamil(Teachers teacher)  throws Exception;
}
