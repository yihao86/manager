package com.yihao86.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
	private int crid;//课程id
	private String crname;//课程名称
	private int cr_aid;//专辑id
	private String cr_img;//课程图片
	private int cr_price;//课程价格
	
	public Course() {
		super();
	}

	public Course(String crname, int cr_aid, String cr_img, int cr_price) {
		super();
		this.crname = crname;
		this.cr_aid = cr_aid;
		this.cr_img = cr_img;
		this.cr_price = cr_price;
	}

}
