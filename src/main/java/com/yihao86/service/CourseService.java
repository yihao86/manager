package com.yihao86.service;

import java.util.List;

import com.yihao86.pojo.Course;

public interface CourseService {

	public String findByIdName(int crid);
	
	public int InsertCourse(Course course);
	
	public List<Course> findAll();
}
