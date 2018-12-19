package com.mutu.spring.rest.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.custommapper.SecurityMapper;
import com.mutu.spring.rest.dto.LoginUser;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao {
	@Autowired
	private SecurityMapper securityMapper;

	public LoginUser findByUsername(String username) {
		return securityMapper.loadUserByUsername(username);
	}
}
