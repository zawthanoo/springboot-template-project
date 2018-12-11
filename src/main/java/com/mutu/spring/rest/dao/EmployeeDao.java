package com.mutu.spring.rest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.entity.Employee;
import com.mutu.spring.rest.entity.EmployeeExample;
import com.mutu.spring.rest.mapper.EmployeeMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDao {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmployeeList() {
//		if(true){
//			throw new RuntimeException("This is testing");
//		}
		EmployeeExample example = new EmployeeExample();
		return employeeMapper.selectByExample(example);
	}
}
