package com.yihao86.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album {
	
	private int aid;//专辑id
	private String a_title;//专辑标题
	private int a_typeId;//类别id
	private int a_teacherId;//教师id
	private String a_abstract;//专辑简介
	private String a_img;//专辑图片
	public Album( String a_title, int a_typeId, int a_teacherId, String a_abstract, String a_img) {
		super();
		this.a_title = a_title;
		this.a_typeId = a_typeId;
		this.a_teacherId = a_teacherId;
		this.a_abstract = a_abstract;
		this.a_img = a_img;
	}
	public Album() {
		super();
	}

}
