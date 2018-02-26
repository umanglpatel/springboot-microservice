package com.demo.product.data.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String brand;
	private String category;

	public Product() {
	}

	public Product(String name, String brand) {
		this.name = name;
		this.brand = brand;
	}

	public Product(String name, String brand, String category) {
		this.name = name;
		this.brand = brand;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "brand", nullable = false, length = 45)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "category", length = 45)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
