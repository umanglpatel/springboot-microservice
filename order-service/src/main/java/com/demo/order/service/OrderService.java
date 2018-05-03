package com.demo.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.domain.data.Order;
import com.demo.domain.data.Product;
import com.demo.order.common.OrderUtils;
import com.demo.order.dao.OrderDao;
import com.demo.order.exception.ProductNotFoundException;

@Service
public class OrderService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderUtils orderUtils;

	@Autowired
	private OrderDao orderDao;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	public Order addOrder(Order order) {
		String url = orderUtils.getProductServiceBaseURL();
		url = url + order.getProductId();
		try {
			restTemplate.getForEntity(url, Product.class);
		} catch (RestClientException e) {
			logger.info("order-service : product not found with id : {} ", order.getProductId());
			throw new ProductNotFoundException(order.getProductId(), e);
		}
		return orderDao.addOrder(order);
	}

}
