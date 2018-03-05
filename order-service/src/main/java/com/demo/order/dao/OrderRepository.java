package com.demo.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.order.data.vo.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
