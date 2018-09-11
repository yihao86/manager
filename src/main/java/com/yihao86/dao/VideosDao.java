package com.yihao86.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Videos;


public interface VideosDao {
	
	//根据教师id查询他所有的视频
	public List<Map<String, Object>> SelectByIdVideo(@Param("v_teacherId") int v_teacherId);
	
	//所有有教师的视频，根据是否禁用进行排序
	public List<Map<String, Object>> SelectAllVideo();

	public Videos VideoById(@Param("vid") int vid);
	
	//禁用视频
	public int DisablleVideo(@Param("vid") int vid,@Param("v_disable") int v_disable);
	
	//新增一条视频
	public int InsertVideo(Videos videos);
}
