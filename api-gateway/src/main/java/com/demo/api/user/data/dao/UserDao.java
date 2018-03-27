package com.demo.api.user.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.domain.data.User;

@Repository
public class UserDao {

	@Autowired
	UserRepository userRepo;

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public User getUserById(int id) {
		return userRepo.findOne(id);
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public User getUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

}
