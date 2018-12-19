package com.mutu.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private String fullName;
	private String phone;
	private String email;
}
