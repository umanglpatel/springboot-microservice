package com.demo.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.data.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
