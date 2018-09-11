package com.yihao86.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

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
import com.yihao86.pojo.Teachers;
import com.yihao86.service.AlbumService;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService as;

	@Autowired
	RedisTemplate redisTemplate;

	/**
	 * 查询专辑名称
	 * 
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("AlbumAll")
	public void AlbumAll(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		List<Album> alist = as.findAll();
		String adname = JSON.toJSONString(alist);
		System.out.println(adname);
		out.write(adname);
		out.flush();
		out.close();
	}

	/**
	 * 获取yml文件中自定义属性
	 */
	@Value("${Mydir.serverURI}")
	private String fileDir;

	/**
	 * 新增专辑
	 * 
	 * @param request
	 * @param files
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/insertAlbum")
	public ModelAndView insertAlbum(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
			HttpSession session,ModelAndView mav) throws IOException {
		System.out.println("进来了");
		String skey = (String) session.getAttribute("skey");
		System.out.println("================================");
		System.out.println(skey);
		System.out.println("================================");

		Teachers teacher = (Teachers) redisTemplate.opsForValue().get(skey);
		String a_name = request.getParameter("a_name");// 专辑标题
		String tyid = request.getParameter("atyName");// 类型id
		String a_abstract = request.getParameter("a_abstract");// 专辑简介

		System.out.println("=======================================");
		System.out.println(teacher.getTid() + "\t" + a_name + "\t" + tyid + "\t" + a_abstract);
		System.out.println("=======================================");

		String path = fileDir + teacher.getT_name() + "/" + a_name; // 设定文件保存的目录
		String pimg = teacher.getT_name() + "/" + a_name + "/" + files[0].getOriginalFilename();

		System.out.println("=======================================");
		System.out.println(path);
		System.out.println(pimg);
		System.out.println("=======================================");
		Album album = new Album(a_name, Integer.valueOf(tyid), teacher.getTid(), a_abstract, pimg);
		if (as.InsertAlbum(album) > 0) {
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
