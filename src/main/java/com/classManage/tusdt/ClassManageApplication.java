package com.classManage.tusdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.classManage.*.dao","com.classManage.*.mapper"})
public class ClassManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassManageApplication.class, args);
	}

}
