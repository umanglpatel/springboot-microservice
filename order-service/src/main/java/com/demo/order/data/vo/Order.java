package com.demo.order.data.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int productId;
	private int userId;
	private int qty;

	public Order() {
	}

	public Order(int productId, int userId, int qty) {
		this.productId = productId;
		this.userId = userId;
		this.qty = qty;
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

	@Column(name = "product_id", nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "qty", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
