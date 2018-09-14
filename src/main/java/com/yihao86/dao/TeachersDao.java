package com.yihao86.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;

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
	
	//注册教师账号
	Teachers RegisterTeacher(Teachers teacher);
	
	public Map<String,Object> findVideoByVid(@Param("vid") int vid);
	
	public Videos selectByVid(@Param("vid") int vid);
	
	public int updateByIdVideo(Videos videos);
}
