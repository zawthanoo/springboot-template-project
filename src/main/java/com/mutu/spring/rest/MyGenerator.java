package com.mutu.spring.rest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

public class MyGenerator {

	public static void main(String[] args) throws Exception {
		/*
		 * useGeneratedKeys="true" keyColumn="EMPLOYEE_ID" keyProperty="employeeId"
		 */
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(new FileInputStream("src/main/resources/mybatic-generator.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("Generation Completed");
	}
}