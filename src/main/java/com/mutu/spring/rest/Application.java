package com.mutu.spring.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@SpringBootApplication
@MapperScan(basePackages = { "com.mutu.spring.rest.zgen.mapper", "com.mutu.spring.rest.custommapper." })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(Application.class); }
	 */
}
