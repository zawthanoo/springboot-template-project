package com.mutu.spring.rest.zconfig.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
