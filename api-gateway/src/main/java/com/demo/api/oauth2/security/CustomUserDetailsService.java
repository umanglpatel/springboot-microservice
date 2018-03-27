package com.demo.api.oauth2.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.api.user.data.dao.UserDao;
import com.demo.domain.data.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(userName);

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority roleUser = new SimpleGrantedAuthority(Authorities.ROLE_USER.name());
		grantedAuthorities.add(roleUser);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}
}
