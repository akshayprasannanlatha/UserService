package com.userservice.userservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
@IdClass(WishListId.class)
public class WishlistEntity {
	@Id
	@Column(name="BUYERID",nullable = false)
	private Integer buyerId;
	public Integer getBuyerId() {
		return buyerId;
	}
	@Id
	@Column(name="PRODID",nullable = false)
	private Integer productId;
	
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	}
