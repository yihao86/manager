package com.yihao86.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService{
	
	@Autowired
	private TeachersDao dao;
	
	@Override
	public Teachers findTeacher(String skey,Teachers teachers) {
		System.out.println("首次进入"+skey);
		return dao.findTeacher(teachers);
	}

	@Override
	public List<Map<String, Object>> findTeacherInfo() {
		return dao.SelectTeacherInfo();
	}

	@Override
	public int countTeacher() {
		return dao.countTeacher();
	}

	@Override
	public boolean DisablleTeacher(int tid,int t_disable) {
		if(dao.DisablleTeacher(tid,t_disable)>0)
			return true;
		return false;
	}

	@Override
	public String findByIdName(int tid) {
		// TODO Auto-generated method stub
		return dao.findByIdName(tid);
	}

}
