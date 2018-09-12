package com.yihao86.dao;

import org.apache.ibatis.annotations.Param;

public interface FollowDao {
	int fandFollowNum(@Param("f_ftid") int f_ftid , @Param("f_time") String f_time);
}
