package com.mutu.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.dao.EmployeeDao;
import com.mutu.spring.rest.entity.Employee;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeService {
	@Autowired
	private EmployeeDao dmployeeDao;
	
	public List<Employee> getEmployeeList() {
		return dmployeeDao.getEmployeeList();
	}
}
