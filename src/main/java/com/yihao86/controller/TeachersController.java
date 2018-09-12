package com.yihao86.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yihao86.pojo.Teachers;
import com.yihao86.service.TeachersService;
import com.yihao86.service.impl.RandomUtil;

@Controller
public class TeachersController {
	
	@Autowired
	private TeachersService ts;
	@Autowired
    RedisTemplate redisTemplate;
	
	/**
	 * 教师登录
	 * @param mav
	 * @param teachers
	 * @param session
	 * @return
	 */
	@RequestMapping("LoginTeacher")
	public ModelAndView LoginTeacher(ModelAndView mav,Teachers teachers,HttpSession session) {
		String skey = "teacher_"+RandomUtil.GetRandom();
		session.setAttribute("skey", skey);
		Teachers teacher = ts.findTeacher(skey,teachers);	
		if (teacher == null) {
			mav.addObject("message", "用户名或密码错误!");
			mav.setViewName("pages-teacherLogin.html");
		} else {
			redisTemplate.opsForValue().set(skey,teacher);
			mav.setViewName("forward:/VodeoManager");		
		}
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	/**
	 * 退出教师登录
	 * @param session
	 * @param request
	 * @param response
	 * @param zx
	 * @return
	 */
		@RequestMapping("/outLogin")
		public String outLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response,String zx) {
			String skey =(String)request.getSession().getAttribute("skey");
//			ServletContext application = request.getServletContext();
//			if (application.getAttribute(session.getAttribute("username").toString()) != null)
//				application.removeAttribute(session.getAttribute("teacher").toString());
//			if (session.getAttribute("teacher") != null)
//				session.removeAttribute("teacher");
//			if (session.getAttribute("username") != null)
//				session.removeAttribute("username");
//			session.invalidate();
			boolean res = redisTemplate.delete(skey);
			System.out.println(res);
//			Cookie[] cookie = request.getCookies();
//			if (cookie != null) {
//				for (Cookie c : cookie) {
//					if ("userMe".equals(c.getName())) {
//						c.setMaxAge(0);
//						response.addCookie(c);
//					}
//				}
//			}
//			if("zx".equals(zx)){
//				return "VodeoManager";
//			}
			request.setAttribute("message", "您退出了登陆");
			return "pages-teacherLogin.html";

		}
	
	}
