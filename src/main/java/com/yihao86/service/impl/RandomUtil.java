package com.yihao86.service.impl;

public class RandomUtil {
	public static String GetRandom() {
		int code = (int) (Math.random() * (400000000 - 100000000)) + 100000000; // 产生1000-9999之间的一个随机数
		String codestr = String.valueOf(code);
		return codestr;
	}

}
