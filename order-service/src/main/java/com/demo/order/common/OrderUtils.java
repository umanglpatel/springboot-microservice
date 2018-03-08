package com.demo.order.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Component
public class OrderUtils {

	@Value("${service.product.serviceId}")
	private String productServiceId;

	@Autowired
	EurekaClient eurekaClient;

	public String getProductServiceBaseURL() {
		Application productApplication = eurekaClient.getApplication(productServiceId);
		InstanceInfo instanceInfo = productApplication.getInstances().get(0);
		return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/v1.0/products/";
	}

	public EurekaClient getEurekaClient() {
		return eurekaClient;
	}

}
