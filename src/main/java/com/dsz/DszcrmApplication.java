package com.dsz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot项目启动类
 */
@SpringBootApplication	//SpringBoot应用标识
@MapperScan("com.dsz.mapper")	//扫描mapper文件
public class DszcrmApplication {
	
	public static void main(String[] args) {	//程序启动入口
		//启动嵌入式的Tomcat并初始化Spring及其各Spring组件
		SpringApplication.run(DszcrmApplication.class, args);
	}
		
}