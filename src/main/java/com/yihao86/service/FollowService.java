package com.yihao86.service;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface FollowService {

	int fandFollowNum(HttpSession session);
	
	List<Integer> fandFollow(HttpSession session);
}
