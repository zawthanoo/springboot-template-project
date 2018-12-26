package com.mutu.spring.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mutu.spring.rest.dto.CreateEmployeeDtoReq;
import com.mutu.spring.rest.dto.EmployeeDto;
import com.mutu.spring.rest.services.EmployeeService;
import com.mutu.spring.rest.zgen.entity.Employee;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping()
	public String greeting() {
		return "Spring-Rest API is tarted.....";
	}

	@RequestMapping("/list")
	public List<Employee> getEmployeeList() {
		return employeeService.getEmployeeList();
	}

	@RequestMapping("/search/{email}")
	public EmployeeDto getByEmail(@PathVariable("email") String email) {
		return employeeService.findByEmail(email);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void create(@Valid @RequestBody CreateEmployeeDtoReq employeeCreate) {
		employeeService.create(employeeCreate);
	}
}
