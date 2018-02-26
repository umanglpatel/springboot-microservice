package com.demo.product.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.product.data.vo.Product;

@Repository
public class ProductDao {

	@Autowired
	ProductRepository productRepo;

	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	public Product getProductById(int id) {
		return productRepo.findOne(id);
	}

	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}

}
