package com.mutu.spring.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mutu.spring.rest.oauth2.UserServiceImpl;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List listUser() {
		return userService.findAll();
	}
}
