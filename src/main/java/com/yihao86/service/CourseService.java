package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Course;

public interface CourseService {

	public String findByIdName(int crid);
	
	public int InsertCourse(Course course);
	
	public  List<Map<String,Object>> findAll(int tid);
}
