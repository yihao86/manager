package com.yihao86.service;

import java.util.List;

import com.yihao86.pojo.Album;

public interface AlbumService {

	public String findByIdName(int aid);
	
	public int InsertAlbum(Album album);
	
	public List<Album> findAll(int tid);
}
