package com.yihao86.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface FollowService {

	int fandFollowNum(HttpSession session);
	
	Map<String,Object> fandFollow(HttpSession session);
	 
	int fandDay(int f_ftid);
	
	int fandPnum(int f_ftid);
	
	int fandNum(int f_ftid);
}
