package com.mutu.spring.rest.oauth2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mutu.spring.rest.dto.LoginUser;
import com.mutu.spring.rest.zgen.entity.User;
import com.mutu.spring.rest.zgen.entity.UserExample;
import com.mutu.spring.rest.zgen.mapper.UserMapper;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Service(value = "UserService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper userMapper;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		LoginUser user = userDao.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	public List<User> findAll() {
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}
}