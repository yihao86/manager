package com.yihao86.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihao86.pojo.Album;

public interface AlbumDao {
	
	public String findByIdName(@Param("aid") int aid);

	public int InsertAlbum(Album album);
	
	public List<Album> findAll(@Param("tid") int tid);
}
