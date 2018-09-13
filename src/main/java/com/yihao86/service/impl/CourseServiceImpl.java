package com.yihao86.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.CourseDao;
import com.yihao86.pojo.Course;
import com.yihao86.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao cd;
	@Override
	public String findByIdName(int crid) {
		// TODO Auto-generated method stub
		return cd.findByIdName(crid);
	}
	@Override
	public int InsertCourse(Course course) {
		// TODO Auto-generated method stub
		return cd.InsertCourse(course);
	}
	@Override
	public List<Map<String, Object>> findAll(int tid) {
		
		return cd.findAll(tid);
	}
	

}
