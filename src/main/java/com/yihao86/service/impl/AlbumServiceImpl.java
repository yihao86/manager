package com.yihao86.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.AlbumDao;
import com.yihao86.pojo.Album;
import com.yihao86.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	private AlbumDao ad;
	@Override
	public String findByIdName(int aid) {
		// TODO Auto-generated method stub
		return ad.findByIdName(aid);
	}
	@Override
	public int InsertAlbum(Album album) {
		// TODO Auto-generated method stub
		return ad.InsertAlbum(album);
	}
	@Override
	public List<Album> findAll() {
		// TODO Auto-generated method stub
		return ad.findAll();
	}

}
