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
	public List<Type> selectAll(int f_trid) {
		// TODO Auto-generated method stub
		return td.findAll(f_trid);
	}

	@Override
	public List<Type> selectType() {
		return td.findType();
	}

	@Override
	public String findNameById(int typeid) {
		// TODO Auto-generated method stub
		return td.findNameById(typeid);
	}


	@Override
	public int findByTypeId(int a_typeId) {
		// TODO Auto-generated method stub
		return td.findByTypeId(a_typeId);
	}

	@Override
	public List<Type> findAllAnameType() {
		// TODO Auto-generated method stub
		return td.findAllAnameType();
	}

}
