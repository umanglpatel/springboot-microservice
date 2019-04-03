package com.demo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.data.User;
import com.demo.user.data.dao.UserDao;
import com.demo.user.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public User addUser(User user) {
		return userDao.saveUser(user);
	}

	public User getUserById(int id) {
		User user = userDao.getUserById(id);
		if (user == null)
			throw new UserNotFoundException(id);
		return user;
	}

	public User updateUser(User user) {
		User existingUser = userDao.getUserById(user.getId());
		if (existingUser == null)
			throw new UserNotFoundException(user.getId());
		return userDao.updateUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}
