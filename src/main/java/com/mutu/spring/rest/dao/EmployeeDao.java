package com.mutu.spring.rest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.custommapper.EmployeeCustomMapper;
import com.mutu.spring.rest.dto.CreateEmployeeDtoReq;
import com.mutu.spring.rest.dto.EmployeeDto;
import com.mutu.spring.rest.zgen.entity.Employee;
import com.mutu.spring.rest.zgen.entity.EmployeeExample;
import com.mutu.spring.rest.zgen.mapper.EmployeeMapper;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDao {
	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private EmployeeCustomMapper employeeCustomMapper;

	public List<Employee> getEmployeeList() {
		EmployeeExample example = new EmployeeExample();
		return employeeMapper.selectByExample(example);
	}

	public EmployeeDto findByEmail(String email) {
		return employeeCustomMapper.findByEmail(email);
	}
	
	public void create(CreateEmployeeDtoReq dtoReq) {
		Employee employee = new Employee();
		employee.setName(dtoReq.getFullName());
		employee.setDob(dtoReq.getDob());
		employee.setEmail(dtoReq.getEmail());
		employee.setMobile(dtoReq.getPhone());
		employee.setGender(dtoReq.getGender().toString());
		employeeMapper.insert(employee);
	}
}
