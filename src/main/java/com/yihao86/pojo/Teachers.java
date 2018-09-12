package com.yihao86.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teachers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int tid;//教师id
	
	private String t_name;//教师名
	
	private String t_account;//教师账号
	
	private String t_password;//教师密码
	
	private String t_email;//教师邮箱
	
	private String t_photo;//教师头像
	
	private String t_abstract;//教师简历
	
	private String t_icard;//教师身份证
	
	private int t_disable;//禁用
	
	private int t_occupation;//教师职业
}
