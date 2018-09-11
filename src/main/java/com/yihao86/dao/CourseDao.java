package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Course;

public interface CourseDao {
	
	public String findByIdName(@Param("crid") int crid);

	public int InsertCourse(Course course);
	public List<Course> findAll();
}
