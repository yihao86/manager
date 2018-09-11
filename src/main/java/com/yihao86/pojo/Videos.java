package com.yihao86.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Videos implements Serializable{
	
	private int vid;//视频id
	
	private String v_name;//视频名称
	
	private int v_teacherId;//教师id
	
	private int v_aid;//专辑id
	
	private int v_crid;//课程id
	
	private String v_uploadtime;//上传时间
	
	private int v_payment;//是否付费视频
	
	private String v_imgs;//图片路径
	
	private int v_to_examine;//视频审核
	
	private int v_disable;//禁用
	
	private String v_vpath;//视频路径
	
	private int v_typeId;//类别ID
	
	private String v_difficulty;//视频难度

	public Videos( String v_name, int v_teacherId, int v_aid, int v_crid,String v_imgs, String v_vpath,
			int v_typeId,String v_difficulty) {
		super();
		this.v_name = v_name;
		this.v_teacherId = v_teacherId;
		this.v_aid = v_aid;
		this.v_crid = v_crid;
		this.v_imgs = v_imgs;
		this.v_vpath = v_vpath;
		this.v_typeId = v_typeId;
		this.v_difficulty = v_difficulty;
	}

	public Videos() {
		super();
	}
	
	

}
