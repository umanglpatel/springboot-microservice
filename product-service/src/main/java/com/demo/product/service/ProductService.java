package com.demo.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.data.Product;
import com.demo.product.data.dao.ProductDao;
import com.demo.product.exception.ProductNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}

	public Product getProductById(int id) {
		Product product = productDao.getProductById(id);
		if (product == null)
			throw new ProductNotFoundException(id);
		return product;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = productDao.getProductById(product.getId());
		if (existingProduct == null)
			throw new ProductNotFoundException(product.getId());
		return productDao.updateProduct(product);
	}

}
