package com.mutu.spring.rest.zconfig.oath2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mutu.spring.rest.dto.LoginUser;
import com.mutu.spring.rest.dto.UserDto;
import com.mutu.spring.rest.zgen.entity.User;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Service(value = "UserService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	public CustomPasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		LoginUser user = userDao.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public void create(UserDto userDto) {
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encodedPassword);
		userDao.insert(user);
	}
}