package com.yihao86.dao;

import org.apache.ibatis.annotations.Param;

public interface PurchaseDao {

	int fandPurchase(@Param("a_teacherId") int a_teacherId , @Param("p_time") String p_time);
	
}
