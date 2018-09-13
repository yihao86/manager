package com.yihao86.service;

import java.util.List;
import java.util.Map;

import com.yihao86.pojo.Videos;

public interface VideosService {
	
	public List<Map<String, Object>> findByIdVideo(int v_teacherId);

	public List<Map<String, Object>> findAllVideo();
	
	public Videos VideoById(int vid);
	
	public boolean DisablleVideo(int vid,int v_disable);
		
	public int InsertVideo(Videos videos);
	
	public boolean Upexamine(int vid);
}
