package com.yihao86.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;

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
	
    public Map<String,Object> selectVideosByVid(int vid);
    
    public Videos findVideoByName(int vid);
    
    public int updateByVid(Videos videos,MultipartFile file)throws Exception; 
}
