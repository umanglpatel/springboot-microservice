package com.demo.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.product.data.vo.Product;
import com.demo.product.service.ProductService;

@RestController
@RequestMapping("/v1.0/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test() {
		return "hello world!";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		product = productService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		product.setId(id);
		product = productService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
}
