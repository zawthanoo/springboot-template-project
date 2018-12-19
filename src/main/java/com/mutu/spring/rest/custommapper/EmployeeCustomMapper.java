package com.mutu.spring.rest.custommapper;

import org.apache.ibatis.annotations.Mapper;

import com.mutu.spring.rest.dto.EmployeeDto;
/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Mapper
public interface EmployeeCustomMapper {
	public EmployeeDto findByEmail(String email);
}
