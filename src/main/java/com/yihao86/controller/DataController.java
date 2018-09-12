package com.yihao86.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yihao86.service.FollowService;

@RestController
public class DataController {

	@Autowired
	private FollowService fs;
	
	@RequestMapping("/fandData")
	public List<Integer> dataInfo(HttpSession session) {
		List<Integer> list = fs.fandFollow(session);
		return list;
	}
}
