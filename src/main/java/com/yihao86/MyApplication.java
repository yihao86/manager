package com.yihao86;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages="com.yihao86.dao")
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(MyApplication.class);
		sa.run(args);
	}
}
