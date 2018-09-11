package com.yihao86.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.UsersDao;
import com.yihao86.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao ud;

	@Override
	public List<Map<String, Object>> findUsers(int f_ftid) {
		return ud.TeacherByIdUsers(f_ftid);
	}

	@Override
	public List<Map<String, Object>> findAll() {
		return ud.SelectAll();
	}
	
	
}
