package com.demo.product.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.product.data.vo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
