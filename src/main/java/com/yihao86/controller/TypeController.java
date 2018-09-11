package com.yihao86.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yihao86.pojo.Type;
import com.yihao86.service.TypeService;

@Controller
public class TypeController {
	
	@Autowired
	private TypeService ts;
	
	/**
	 * 类型名
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("TypeAll")
	public void TypeAll(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		PrintWriter out= response.getWriter();		
		 List<Type> tylist=ts.selectAll();
	     String d=JSON.toJSONString(tylist);
	     System.out.println(d);
	     out.write(d);
	     out.flush();
	     out.close();
	}
	
}
