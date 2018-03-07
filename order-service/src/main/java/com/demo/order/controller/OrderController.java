package com.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.domain.data.Order;
import com.demo.order.service.OrderService;

@RestController
@RequestMapping("/v1.0/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		order = orderService.addOrder(order);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
}
