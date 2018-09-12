package com.yihao86.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Type;
import com.yihao86.service.TeachersService;
import com.yihao86.service.TypeService;
import com.yihao86.service.UsersService;
import com.yihao86.service.VideosService;
import com.yihao86.service.impl.RandomUtil;

@Controller
public class ManagerController {
	@Autowired
	private UsersService us;
	
	@Autowired
	private TeachersService ts;
	
	@Autowired
	private VideosService vs;
	
	@Autowired
	private TypeService tys;

	/**
	 * 首页的查询
	 * @param mav
	 * @param req
	 * @return
	 */
	@RequestMapping("UManager")
	public ModelAndView UManager(ModelAndView mav,HttpServletRequest req) {
		List<Map<String, Object>> ulist=us.findAll();
		List<Map<String, Object>> tlist=ts.findTeacherInfo();
		int count=ts.countTeacher();
		List<Map<String, Object>> vlist=vs.findAllVideo();
		List<Type> tylist=tys.selectType();
		mav.addObject("ulist",ulist);
		mav.addObject("tlist",tlist);
		mav.addObject("vlist",vlist);
		mav.addObject("tylist",tylist);
		mav.addObject("count", count);
		mav.setViewName("index.html");
		return mav;
	}
	
	/**
	 * 禁用教师
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("disableTeacher")
	public void disableTeacher(Integer tid,Integer t_disable,HttpServletResponse response) throws IOException {
		OutputStream out= response.getOutputStream();
		if(ts.DisablleTeacher(tid,t_disable)){
			out.write("Y".getBytes());
		}else{
			out.write("N".getBytes());
		}
		out.flush();
		out.close();	
	}
	
	/**
	 * 禁用视频
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("disableVideo")
	public void disableVideo(Integer vid,Integer v_disable,HttpServletResponse response) throws IOException {
		OutputStream out= response.getOutputStream();
		if(vs.DisablleVideo(vid,v_disable)){
			out.write("Y".getBytes());
		}else{
			out.write("N".getBytes());
		}
		out.flush();
		out.close();	
	}
	
	/**
	 * 视频审核通过
	 * @param vid
	 * @param v_disable
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("examineVideo")
	public void examineVideo(Integer vid,HttpServletResponse response) throws IOException {
		OutputStream out= response.getOutputStream();
		if(vs.Upexamine(vid)){
			out.write("Y".getBytes());
		}else{
			out.write("N".getBytes());
		}
		out.flush();
		out.close();	
	}
	
}
