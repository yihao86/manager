package com.yihao86.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Follow {

	private int fid;//关注id
	private int f_uid;//用户id
	private int f_ftid;//教师id
	private String f_time;//关注时间
}
