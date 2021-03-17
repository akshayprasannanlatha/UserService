package com.userservice.userservice.dto;

import com.userservice.userservice.entity.CartId;

public class CartIdDTO {

	private Integer buyerId;
	private Integer productId;
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
	public CartId createEntity() {
		CartId cartId = new CartId();
		cartId.setBuyerId(buyerId);
		cartId.setProductId(productId);
		return cartId;
	}
	@Override
	public String toString() {
		return "CartIdDTO [buyerId=" + buyerId + ", productId=" + productId + "]";
	}
	
}
