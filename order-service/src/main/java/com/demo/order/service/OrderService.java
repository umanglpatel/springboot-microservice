package com.demo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.dao.OrderDao;
import com.demo.order.data.vo.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public Order addOrder(Order order) {
		return orderDao.addOrder(order);
	}

}
