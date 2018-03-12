package com.demo.order.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> test() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		String result = "Request served from : " + ip.getHostAddress() + " and host : " + ip.getHostName();
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
