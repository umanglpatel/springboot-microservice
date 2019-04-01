package com.demo.order.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.domain.data.Order;
import com.demo.order.service.OrderService;

//@CrossOrigin
@RestController
@RequestMapping("/v1.0/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	// https://springframework.guru/using-logback-spring-boot/
	// SLF4J is a façade for commonly used logging frameworks,
	// such as Java Util Logging, Log4J 2, and Logback.
	// By writing against SLF4J, our code remains decoupled

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		order = orderService.addOrder(order);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> test() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		String result = "Request served from : " + ip.getHostAddress() + " and host : " + ip.getHostName();
		logger.debug("order-service : Logback : This is a debug message");
		logger.info("order-service : Logback : This is an info message");
		logger.warn("order-service : Logback : This is a warn message");
		logger.error("order-service : Logback : This is an error message");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
