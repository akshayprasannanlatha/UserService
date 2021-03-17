package com.userservice.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="cart")
@IdClass(CartId.class)
public class CartEntity {
    
	@Id
	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	@Id
	@Column(name="PRODID",nullable = false)
	private Integer productId;
	@Column(name="QUANTITY",nullable = false)
	private Integer quantity;
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer qnty) {
		quantity = qnty;
	}
	
	
	
	
}