package com.mutu.spring.rest.zconfig.oath2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mutu.spring.rest.custommapper.SecurityMapper;
import com.mutu.spring.rest.dto.LoginUser;
import com.mutu.spring.rest.zgen.entity.User;
import com.mutu.spring.rest.zgen.entity.UserExample;
import com.mutu.spring.rest.zgen.entity.UserRolesKey;
import com.mutu.spring.rest.zgen.mapper.UserMapper;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao {
	@Autowired
	private SecurityMapper securityMapper;

	@Autowired
	private UserMapper userMapper;

	public LoginUser findByUsername(String username) {
		return securityMapper.loadUserByUsername(username);
	}
	
	public List<User> findAll() {
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}
	
	public void insert(User user) {
		int userId = userMapper.insertSelective(user);
		UserRolesKey key = new UserRolesKey();
		key.setUserId(1L * userId);
		key.setRoleId(1L);
	}
}
