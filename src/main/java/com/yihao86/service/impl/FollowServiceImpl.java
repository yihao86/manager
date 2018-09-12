package com.yihao86.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yihao86.dao.FollowDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao fdao;
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Override
	public int fandFollowNum(HttpSession session) {
		String skey =(String)session.getAttribute("skey");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		int num = fdao.fandFollowNum(teacher.getTid(), null);
		return num;
	}

	@Override
	public List<Integer> fandFollow(HttpSession session) {
		
		String skey =(String)session.getAttribute("skey");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -i);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
			String defaultStartDate = sdf.format(dBefore);    //格式化前一天
			
			int num = fdao.fandFollowNum(teacher.getTid(), defaultStartDate);
			System.out.println(num);
			list.add(num);
		}
		return list;
	}
	
	

}
