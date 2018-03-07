package com.demo.user.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.data.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
