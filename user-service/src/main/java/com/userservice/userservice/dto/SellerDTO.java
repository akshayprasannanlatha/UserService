package com.userservice.userservice.dto;

import com.userservice.userservice.entity.Seller;


public class SellerDTO {

	int sellerId;
	String name;
	String email;
	String phoneNumber;
	String password;

	int isActive;
	







		public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "SellerDTO [ name=" + name + ",  Email=" + email + ", PhoneNumber=" + phoneNumber
				+ ", Password=" + password + "]";
	}


		// Converts Entity into DTO
		public static SellerDTO valueOf(Seller sell) {
			SellerDTO sellDTO = new SellerDTO();
			sellDTO.setSellerId(sell.getSellerId());
			sellDTO.setName(sell.getUserName());
			sellDTO.setEmail(sell.getEmail());
			sellDTO.setPhoneNumber(sell.getPhoneNumber());
			sellDTO.setPassword(sell.getUserPassword());
			sellDTO.setIsActive(sell.getActive());
			
			return sellDTO;
		}

		// Converts DTO into Entity
		public Seller createEntitySeller() {
			Seller cust = new Seller();
			cust.setEmail(this.getEmail());
			cust.setPhoneNumber(this.getPhoneNumber());
			
			cust.setUserName(this.getName());
			cust.setUserPassword(this.getPassword());
			cust.setActive(this.getIsActive());
			return cust;
		}


}
