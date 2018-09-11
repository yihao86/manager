package com.yihao86.service;

import java.util.List;
import java.util.Map;

public interface UsersService {
	//根据教师id查询关注自己的用户
	public List<Map<String, Object>> findUsers(int f_ftid);

	//查询所有用户
	public List<Map<String, Object>> findAll();
}
