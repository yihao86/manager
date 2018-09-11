package com.yihao86.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihao86.dao.TypeDao;
import com.yihao86.pojo.Type;
import com.yihao86.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDao td;
	
	@Override
	public List<Type> selectAll() {
		// TODO Auto-generated method stub
		return td.findAll();
	}

	@Override
	public List<Type> selectType() {
		// TODO Auto-generated method stub
		return td.findType();
	}

	@Override
	public List<Type> selectSonList(int f_trid) {
		// TODO Auto-generated method stub
		return td.findSonList(f_trid);
	}

	@Override
	public String findNameById(int typeid) {
		// TODO Auto-generated method stub
		return td.findNameById(typeid);
	}

	@Override
	public List<Type> findAllList() {
		// TODO Auto-generated method stub
		return td.findAllList();
	}

}
