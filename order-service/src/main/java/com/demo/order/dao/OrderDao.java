package com.demo.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.order.data.vo.Order;

@Repository
public class OrderDao {

	@Autowired
	private OrderRepository orderRepo;

	public Order addOrder(Order order) {
		return orderRepo.save(order);
	}

}
