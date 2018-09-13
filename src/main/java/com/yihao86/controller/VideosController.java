package com.yihao86.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.yihao86.pojo.Teachers;
import com.yihao86.pojo.Videos;
import com.yihao86.service.AlbumService;
import com.yihao86.service.CourseService;
import com.yihao86.service.FollowService;
import com.yihao86.service.TeachersService;
import com.yihao86.service.UsersService;
import com.yihao86.service.VideosService;

@Controller
public class VideosController {
	
	@Autowired
	private VideosService vs;
	
	@Autowired
	private UsersService us;
	
	@Autowired
	private AlbumService as;
	@Autowired
	private CourseService cs;
	@Autowired
	private TeachersService ts;
	@Autowired
	private FollowService fs;
	@Autowired
    private RedisTemplate redisTemplate;
	
	/**
	 * 根据教师id查询他所有的视频
	 * @param mav
	 * @param teachers
	 * @param session
	 * @return
	 */
	@RequestMapping("VodeoManager")
	public ModelAndView VodeoManager(ModelAndView mav,HttpSession session) {
		String skey =(String)session.getAttribute("skey");
		System.out.println("================================");
		System.out.println(skey);
		System.out.println("================================");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
		List<Map<String, Object>> list=vs.findByIdVideo(teacher.getTid());
		List<Map<String, Object>> ulist=us.findUsers(teacher.getTid());
		mav.addObject("teacher", teacher);
		mav.addObject("list",list);
		int num = fs.fandFollowNum(session);
		int follow = fs.fandDay(teacher.getTid());
		int pnum = fs.fandPnum(teacher.getTid());
		int pnumz = fs.fandNum(teacher.getTid());
		mav.addObject("num",num);
		mav.addObject("follow",follow);
		mav.addObject("pnum",pnum);
		mav.addObject("pnumz",pnumz);
		mav.setViewName("teacherManager.html");
		return mav;
	}
	
	
	
	/**
	 * 根据id获取视频信息
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("VideoInfo")
	public void VideoInfo(Integer vid,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		PrintWriter out= response.getWriter();		
		Videos da=vs.VideoById(vid);
	     String d=JSON.toJSONString(da);
	     out.write(d);
	     out.flush();
	     out.close();
	}
	

    /**
     * 获取yml文件中自定义属性
     */
    @Value("${Mydir.serverURI}")
    private String fileDir;

    /**
     * 文件上传
     * @param request
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile[] files,HttpServletRequest request,HttpSession session) throws IOException{
        System.out.println("进来了");
        String skey =(String)session.getAttribute("skey");
		System.out.println("================================");
		System.out.println(skey);
		System.out.println("================================");
		Teachers teacher = (Teachers)redisTemplate.opsForValue().get(skey);
        String vname=request.getParameter("vname");//视频名
		String cid=request.getParameter("cname");//课程名
		String zname=request.getParameter("zname");//章节名
		String nname=request.getParameter("nname");//视频难度
		String aid=request.getParameter("aname");//专辑id
		String tyName=request.getParameter("tyName");//类型id
		System.out.println("=======================================");
		System.out.println(teacher.getTid()+"\t"+vname+"\t"+cid+"\t"+aid+"\t"+nname);
		System.out.println("=======================================");
		String aname=as.findByIdName(Integer.valueOf(aid));
		String cname=cs.findByIdName(Integer.valueOf(cid));
		String t_name=ts.findByIdName(teacher.getTid());
        String path = fileDir+t_name+"/"+aname+"/"+cname+"/"+zname; // 设定文件保存的目录     
        String pa=t_name+"/"+aname+"/"+cname+"/"+zname+"/"+files[0].getOriginalFilename();
        String pimg =t_name+"/"+aname+"/"+cname+"/"+files[1].getOriginalFilename();
        System.out.println("=======================================");
        System.out.println(path);
        System.out.println(pa);
        System.out.println(pimg);
        System.out.println("=======================================");
        Videos videos=new Videos(vname,teacher.getTid(),Integer.valueOf(aid), Integer.valueOf(cid), pimg, pa,Integer.valueOf(tyName),nname);
		if(vs.InsertVideo(videos)>0){
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
        //ServletContext sc = request.getSession().getServletContext();
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
        			file.transferTo(new File(path+fileName));               	           
                    //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        		    FileOutputStream fos = new FileOutputStream(path
                            + fileName);
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
        }else{
        	System.out.println("文件上传失败！");
        }
    }
    
    
    /**
	 * 删除视频
	 * @param mav
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("delVideo")
	public void delVideo(Integer vid,HttpServletResponse response) throws IOException {
		OutputStream out= response.getOutputStream();
		//根据vid删除三张表里的数据(videos,collection,history)
//		if(ts.DisablleTeacher(tid,t_disable)){
//			out.write("Y".getBytes());
//		}else{
//			out.write("N".getBytes());
//		}
		out.flush();
		out.close();	
	}
	
	}
