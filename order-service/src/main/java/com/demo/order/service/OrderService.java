package com.demo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.domain.data.Order;
import com.demo.domain.data.Product;
import com.demo.order.dao.OrderDao;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
public class OrderService {

	@Value("${service.product.serviceId}")
	private String productServiceId;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EurekaClient eurekaClient;

	@Autowired
	private OrderDao orderDao;

	public Order addOrder(Order order) {
		Application productApplication = eurekaClient.getApplication(productServiceId);
		InstanceInfo instanceInfo = productApplication.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/v1.0/products/"
				+ order.getProductId();
		System.out.println("URL" + url);
		Product product = restTemplate.getForObject(url, Product.class);
		System.out.println(product);
		return orderDao.addOrder(order);
	}

}
