package com.yihao86.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TeachersDao;
import com.yihao86.pojo.Teachers;
import com.yihao86.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService{
	
	@Autowired
	private TeachersDao dao;
	
	@Override
	public Teachers findTeacher(String skey,Teachers teachers) {
		System.out.println("首次进入"+skey);
		return dao.findTeacher(teachers);
	}

	@Override
	public List<Map<String, Object>> findTeacherInfo() {
		return dao.SelectTeacherInfo();
	}

	@Override
	public int countTeacher() {
		return dao.countTeacher();
	}

	@Override
	public boolean DisablleTeacher(int tid,int t_disable) {
		if(dao.DisablleTeacher(tid,t_disable)>0)
			return true;
		return false;
	}

	@Override
	public String findByIdName(int tid) {
		// TODO Auto-generated method stub
		return dao.findByIdName(tid);
	}
	
	
	
	public static final String SMTPSERVER = "smtp.qq.com";
    public static final String SMTPPORT = "465";
    public static final String ACCOUT = "528164108@qq.com";
    public static final String PWD = "lfncdaukoxatbggb";

	/**
	 * 注册教师信息
	 */
	@Override
	public Teachers RegisterTeacher(Teachers teacher) {	
		
		return dao.RegisterTeacher(teacher);
	}
	
	
	@Override
	public void eamil(Teachers teacher) throws Exception {
		// TODO Auto-generated method stub
		// 创建邮件配置
		//进入发送邮箱
		System.out.println("进入发送邮箱");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl


        // 根据邮件配置创建会话，注意session别导错包
        Session session = Session.getDefaultInstance(props);
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件
        MimeMessage message = createEmail(session,teacher);
        //获取传输通道
        Transport transport = session.getTransport();
        transport.connect(SMTPSERVER,ACCOUT, PWD);
        //连接，并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}


    public static MimeMessage createEmail(Session session,Teachers teacher) throws Exception {
        // 根据会话创建邮件
    	System.out.println("根据会话创建邮件");
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "注册信息", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                "503453843@qq.com", "test", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject("教师注册信息验证", "utf-8");
       /* msg.setContent("<h1>有一个新的注册信息</h1>"
        		+ "<td style=\"padding:0; padding-top:25px; font-family:'Microsoft Yahei', Verdana, Simsun, sans-serif; font-size:14px; color:#2a2a2a;\">"
        		+ "<table border=\"0\" cellspacing=\"0\"><tbody><tr><td bgcolor=\"#2672ec\" style=\"background-color:#2672ec; padding-top: 5px; padding-right: 20px; padding-bottom: 5px; padding-left: 20px; min-width:50px;\">"
        		+ "<a id=\"i7\" style=\"font-family: 'Microsoft Yahei', Verdana, Simsun, sans-serif; font-size:14px; text-align:center; text-decoration:none; font-weight:600; letter-spacing:0.02em; color:#fff;\" "
        		+ "href=\"http://http://localhost:8080/yihao/registerTeacher;info=RsRqdn09rYox26D0wU6zm%2bdU8D9YhOXc92vVrMS9F8yGN0mVnMtGjA%3d%3d&amp;date=VDlsKJDdrYw8zfIryWv9%2fYnqUZlEp306\" "
        		+ "rel=\"noopener\" target=\"_blank\">确认注册信息</a></td></tr></tbody></table></td>","text/html;charset=utf-8");*/
        
        
        // 设置显示的发件时间
        msg.setContent("<div style=\"text-align:center;\" ><tbody ><tr style=\"width:685px;\" class=\"devWidth\">\n" + 
        		"          <td class=\"centerColumn centerColumnSpacer\" style=\"padding-right: 36px;padding-left: 40px; \">\n" + 
        		"            <table style=\"font-size: inherit; line-height: inherit; padding: 0px 0px 0px 200px; border: 0px;\">               \n" + 
        		"               <tbody><tr>																					\n" + 
        		"				   <td class=\"content spacerSalutation\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 15px;font-weight:300; line-height: 20px; padding-top: 38px;padding-bottom: 15px;text-align: left;\">\n" + 
        		"					   尊敬的管理员，您好：\n" + 
        		"				  </td>																												\n" + 
        		"			   </tr>\n" + 
        		"				<tr>\n" + 
        		"			   		<td class=\"content spacer\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 15px;font-weight:300; line-height: 20px; padding-bottom: 15px;text-align: left;\">\n" + 
        		"				   		您有一封新的教师注册邮件，请您仔细确认教师信息\n" + 
        		"				  </td></tr>\n" + 
        		"			   <tr>\n" + 
        		"			   	<td class=\"content spacer\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 15px;font-weight:300; line-height: 20px; padding-bottom: 15px;text-align: left;\">\n" + 
        		"				   		如果您不是本人，请忽略此消息\n" + 
        		"				   </td>\n" + 
        		"				</tr>\n" + 
        		"				\n" + 
        		"						<tr style=\"text-align:center;\"><td class=\"content spacer\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #34c5ff; font-size: 16px;font-weight:600; line-height: 10px; padding-bottom: 15px;text-align: center;\">\n" + 
        		"							此次提交的教师信息为：<p>姓名 ："+teacher.getT_name()+"<p>"
        				+ "								   <p>联系方式 ："+teacher.getT_abstract()+"<p>"
        						+ "						   <p>身份证号 ："+teacher.getT_icard()+"<p>\n"
        								+ "				   <p>邮箱号 ："+teacher.getT_email()+"<p>"
        										+ "		   <p>简介信息 ："+teacher.getT_abstract()+"<p>" + 
        		"						</td></tr>\n" + 
        		"					\n" + 
        		"			   			<tr><td class=\"content spacer\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 15px;font-weight:300; line-height: 20px; padding-bottom: 15px;text-align: left;\">\n" + 
        		"			   			请您及时联系申请教师，确认教师信息是否正确？\n" + 
        		"						</td></tr>\n" + 
        		"				    						   \n" + 
        		"			   	   		<tr><td class=\"content spacer3\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 20px;font-weight:300; line-height: 20px; padding-bottom: 15px;text-align: left;\">\n" + 
        		"					<a id=\"i7\" style=\"font-family: 'Microsoft Yahei', Verdana, Simsun, sans-serif; font-size:20px; text-align:center; text-decoration:none; font-weight:600; letter-spacing:0.02em; color:#0082f5;\" "
        		+ "						href=\"http://localhost:8080/yihao/registerTeacher;info=RsRqdn09rYox26D0wU6zm%2bdU8D9YhOXc92vVrMS9F8yGN0mVnMtGjA%3d%3d&amp;date=VDlsKJDdrYw8zfIryWv9%2fYnqUZlEp306\" rel=\"noopener\" target=\"_blank\">请求申请通过</a>\n" + 
        		"				   </td></tr>\n" + 
        		"				   				  \n" + 
        		"\n" + 
        		"			   <tr>						   \n" + 
        		"			   	   <td class=\"content spacer2\" style=\"word-break: break-word; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-smooth: always; -webkit-font-smoothing: antialiased; color: #333; font-size: 15px;font-weight:300; line-height: 20px; padding-bottom: 29px; text-align: left;\">\n" + 
        		"						yihao86 团队\n" + 
        		"				   </td>\n" + 
        		"			   </tr>						 		\n" + 
        		"         </tbody></table>\n" + 
        		"          </td>\n" + 
        		"        </tr>      \n" + 
        		"      </tbody></div>", "text/html;charset=utf-8");
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }
}
