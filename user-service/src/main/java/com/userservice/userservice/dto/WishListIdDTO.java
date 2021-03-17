package com.userservice.userservice.dto;

import com.userservice.userservice.entity.CartId;
import com.userservice.userservice.entity.WishListId;

public class WishListIdDTO {

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
	public WishListId createEntity() {
		WishListId wishListId = new WishListId();
		wishListId.setBuyerId(buyerId);
		wishListId.setProductId(productId);
		return wishListId;
	}
	@Override
	public String toString() {
		return "WishlistDTO [buyerId=" + buyerId + ", productId=" + productId + "]";
	}
	
}
