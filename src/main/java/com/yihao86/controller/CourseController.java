package com.yihao86.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yihao86.pojo.Album;
import com.yihao86.pojo.Course;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.AlbumService;
import com.yihao86.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService cs;

	@Autowired
	private AlbumService as;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	/**
	 * 课程名称
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("CourseAll")
	public void CourseAll(HttpServletResponse response,HttpSession session) throws IOException {
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		 String skey =(String)session.getAttribute("skey");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		PrintWriter out= response.getWriter();		
		List<Map<String, Object>> clist=cs.findAll(teacher.getTid());
	     String cdname=JSON.toJSONString(clist);
	     System.out.println(cdname);
	     out.write(cdname);
	     out.flush();
	     out.close();
	}
	

	/**
	 * 获取yml文件中自定义属性
	 */
	@Value("${Mydir.serverURI}")
	private String fileDir;

	/**
	 * 新增课程
	 * 
	 * @param request
	 * @param files
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/insertCourse")
	public ModelAndView insertCourse(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
			HttpSession session,ModelAndView mav) throws IOException {
		System.out.println("进来了");
		String skey = (String) session.getAttribute("skey");
		System.out.println("================================");
		System.out.println(skey);
		System.out.println("================================");

		Teachers teacher = (Teachers) redisTemplate.opsForValue().get(skey);
		
		String c_name = request.getParameter("c_name");// 课程标题
		String caname = request.getParameter("caname");// 专辑id
		String price = request.getParameter("price");// 课程价格

		System.out.println("=======================================");
		System.out.println(c_name + "\t" + caname + "\t" + price);
		System.out.println("=======================================");
		String aname=as.findByIdName(Integer.valueOf(caname));
		String path = fileDir + teacher.getT_name() + "/" + aname; // 设定文件保存的目录
		String pimg = teacher.getT_name() + "/" + aname + "/" + files[0].getOriginalFilename();

		System.out.println("=======================================");
		System.out.println(path);
		System.out.println(pimg);
		System.out.println("=======================================");
		Course course=new Course(c_name, Integer.valueOf(caname), pimg, Integer.valueOf(price));
		
		if (cs.InsertCourse(course)> 0) {
			List<String> list = new ArrayList<String>();
			// 获得项目的路径
			// ServletContext sc = request.getSession().getServletContext();
			// 上传位置
			File f = new File(path);

			if (!f.exists())
				f.mkdirs();
			for (int i = 0; i < files.length; i++) {
				// 获得原始文件名
				String fileName = files[i].getOriginalFilename();
				fileName = URLDecoder.decode(fileName, "utf-8");
				if (!files[i].isEmpty()) {
					try {
						MultipartFile file = files[i];
						file.transferTo(new File(path + fileName));
						// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
						FileOutputStream fos = new FileOutputStream(path + fileName);
						InputStream in = files[i].getInputStream();
						int b = 0;
						while ((b = in.read()) != -1) {
							fos.write(b);
						}
						fos.close();
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("上传图片到:" + path + fileName);
				list.add(path + fileName);

			}
			mav.setViewName("forward:/VodeoManager");
			
		} else {
			System.out.println("文件上传失败！");
		}
		return mav;
	}


}
