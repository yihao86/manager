
package com.yihao86.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;
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
		System.out.println("bbbbbbbbbbbbbbbbbb");
		String skey = "teacher_"+RandomUtil.GetRandom();
		session.setAttribute("skey", skey);
		Teachers teacher = ts.findTeacher(skey,teachers);	
		if (teacher == null) {
			mav.addObject("message", "用户名或密码错误!");
			mav.setViewName("pages-teacherLogin.html");
		} else {
			System.out.println("bbbbbbbbbbbbbbbbbb");
			redisTemplate.opsForValue().set(skey,teacher);	
			System.out.println("88888888888888888888888");
			mav.setViewName("forward:/VodeoManager");		
		}
		return mav;
	}
	
	@RequestMapping("registerTeacher")
	public void registerTeacher(Teachers teacher){
		ts.RegisterTeacher(teacher);
	}
	
	@RequestMapping("email")
	public void email(Teachers teacher) {
		
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


/**
 * 查询视频详情
 * @param vid
 * @return
 */
	@ResponseBody
	@RequestMapping("findVideo")
	public Map<String,Object> findVideo(String vid){
		Map<String,Object> map=	ts.selectVideosByVid(Integer.valueOf(vid));			
		return map;		
	}
	
	@RequestMapping("/updateViodes")
	public String updateByVid(Videos videos,@RequestParam("files") MultipartFile files) throws IOException, Exception {
	    System.out.println("hzxhcxzc");
		ts.updateByVid(videos, files);
		  
		return "forward:/VodeoManager";			
	}
	
}