package com.demo.user.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.user.data.vo.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
