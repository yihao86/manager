package com.yihao86.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.VideosDao;
import com.yihao86.pojo.Videos;
import com.yihao86.service.VideosService;

@Service
public class VideosServiceImpl implements VideosService{

	@Autowired
	private VideosDao vd;
	
	@Override
	public List<Map<String, Object>> findByIdVideo(int v_teacherId) {
		return vd.SelectByIdVideo(v_teacherId);
	}

	@Override
	public List<Map<String, Object>> findAllVideo() {
		return vd.SelectAllVideo();
	}

	@Override
	public Videos VideoById(int vid) {
		return vd.VideoById(vid);
	}

	@Override
	public boolean DisablleVideo(int vid, int v_disable) {
		if(vd.DisablleVideo(vid,v_disable)>0)
			return true;
		return false;
	}

	@Override
	public int InsertVideo(Videos videos) {
		// TODO Auto-generated method stub
		return vd.InsertVideo(videos);
	}

	
}
