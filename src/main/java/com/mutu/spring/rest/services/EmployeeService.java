package com.mutu.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.dao.EmployeeDao;
import com.mutu.spring.rest.dto.EmployeeDto;
import com.mutu.spring.rest.zgen.entity.Employee;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeService {
	@Autowired
	private EmployeeDao dmployeeDao;

	public List<Employee> getEmployeeList() {
		return dmployeeDao.getEmployeeList();
	}

	public EmployeeDto findByEmail(String email) {
		return dmployeeDao.findByEmail(email);
	}
}
