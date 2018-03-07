package com.demo.product.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.data.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
