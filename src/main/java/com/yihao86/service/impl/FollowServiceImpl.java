package com.yihao86.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yihao86.dao.FollowDao;
import com.yihao86.dao.PurchaseDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao fdao;
	
	@Autowired
	private PurchaseDao pdao;
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Override
	public int fandFollowNum(HttpSession session) {
		String skey =(String)session.getAttribute("skey");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		int num = fdao.fandFollowNum(teacher.getTid(),null);
		return num;
	}

	@Override
	public Map<String,Object> fandFollow(HttpSession session) {
		
		String skey =(String)session.getAttribute("skey");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		List<String> list4 = new ArrayList<>();
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
			int pnum = pdao.fandPurchase(teacher.getTid(), defaultStartDate);
			int daynum = fdao.fandDay(teacher.getTid(), defaultStartDate);
			
			list1.add(num);
			list2.add(pnum);
			list3.add(daynum);
			list4.add(defaultStartDate);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("followNum", list1);
		map.put("pNum", list2);
		map.put("zfollowNum", list3);
		map.put("time", list4);
		return map;
	}

	@Override
	public int fandDay(int f_ftid) {
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String time = sdf.format(date);
		return fdao.fandFollowNum(f_ftid, time);
	}

	@Override
	public int fandPnum(int f_ftid) {
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String time = sdf.format(date);
		return pdao.fandPurchase(f_ftid,time);
	}

	@Override
	public int fandNum(int f_ftid) {
		return pdao.fandPurchase(f_ftid, null);
	}
	
	

}
