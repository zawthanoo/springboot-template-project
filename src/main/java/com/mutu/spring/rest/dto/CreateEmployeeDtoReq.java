package com.mutu.spring.rest.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDtoReq {
	@NotNull
	@NotBlank
	@Size(min=3, max=255)
	private String fullName;
	@NotNull
	@NotBlank
	private String phone;
	@NotNull
	@NotBlank
	@Email
	private String email;
	@NotNull
	private Gender gender;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
}
