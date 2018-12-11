package com.mutu.spring.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutu.spring.rest.entity.Employee;
import com.mutu.spring.rest.services.EmployeeService;

@RestController
public class AppController {
	@Autowired
	private EmployeeService employeeService;
	
    @RequestMapping()
    public String greeting() {
    	return "Spring-Rest API is tarted.....";
    }
    
    @RequestMapping("/employee-list")
    public List<Employee> getEmployeeList() {
    	return employeeService.getEmployeeList();
    }
}
