package com.mutu.spring.rest.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mutu.spring.rest.zconfig.MessageCode;
import com.mutu.spring.rest.zconfig.exception.BusinessLogicException;

public class Utils {
	public static Date getDate(String value) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			return format.parse(value);
		} catch (ParseException e) {
			throw new BusinessLogicException(MessageCode.UNEXPECTED_ERROR, "Invalid Date Format");
		}
	}
}
