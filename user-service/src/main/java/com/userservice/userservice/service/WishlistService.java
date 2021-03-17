package com.userservice.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.userservice.dto.CartDTO;
import com.userservice.userservice.dto.CartIdDTO;
import com.userservice.userservice.dto.WishListIdDTO;
import com.userservice.userservice.dto.WishlistDTO;
import com.userservice.userservice.entity.CartEntity;
import com.userservice.userservice.entity.CartId;
import com.userservice.userservice.entity.WishListId;
import com.userservice.userservice.entity.WishlistEntity;
import com.userservice.userservice.repository.CartRepository;
import com.userservice.userservice.repository.WishListRepository;



@Service
public class WishlistService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WishListRepository wishListRepository;
	
	
	public void addToWishList(WishlistDTO wishListDTO) throws Exception {
		logger.info("======Cart Creation Request for data {}======", wishListDTO);
		WishListId wishListId = new WishListId();
		wishListId.setBuyerId(wishListDTO.getBuyerId());
		wishListId.setProductId(wishListDTO.getProductId());
		
		Optional <WishlistEntity> optCart = wishListRepository.findById(wishListId);
		if(optCart.isPresent()) {
			throw new Exception("Wishlist.PRESENT");
		}
		WishlistEntity wishList = wishListDTO.createEntity();
		wishListRepository.save(wishList);
		
	}
	
	public void deleteFromWishList(WishListIdDTO wishListIdDTO) {
		logger.info("======Cart Deletion Request for cart {}========", wishListIdDTO);
		
		WishListId wishListId = wishListIdDTO.createEntity();
		wishListRepository.deleteById(wishListId);
	}
	
	
}
