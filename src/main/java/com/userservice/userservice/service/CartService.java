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
import com.userservice.userservice.entity.CartEntity;
import com.userservice.userservice.entity.CartId;
import com.userservice.userservice.repository.CartRepository;



@Service
public class CartService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CartRepository cartRepository;
	
	public List<CartDTO> getByBuyerId(Integer buyerId) {
		logger.info("======Cart  details : {}======", buyerId);
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		
		List<CartEntity> cartList = cartRepository.findByBuyerId(buyerId);
		if (!cartList.isEmpty()) {
			for(CartEntity cart : cartList) {
				CartDTO cartDTO = new CartDTO();
				cartDTO.setBuyerId(cart.getBuyerId());
				cartDTO.setProductId(cart.getProductId());
				cartDTO.setQuantity(cart.getQuantity());
				cartDTOList.add(cartDTO);
			}
		}

		return cartDTOList;
	}
	
	public void addToCart(CartDTO cartDTO) throws Exception {
		logger.info("======Cart Creation Request for data {}======", cartDTO);
		CartId cartId = new CartId();
		cartId.setBuyerId(cartDTO.getBuyerId());
		cartId.setProductId(cartDTO.getProductId());
		
		Optional <CartEntity> optCart = cartRepository.findById(cartId);
		if(optCart.isPresent()) {
			throw new Exception("CART.PRESENT");
		}
		CartEntity cart = cartDTO.createEntity();
		cartRepository.save(cart);
		
	}
	
	public void deleteFromCart(CartIdDTO cartIdDTO) {
		logger.info("======Cart Deletion Request for cart {}========", cartIdDTO);
		
		CartId cartId = cartIdDTO.createEntity();
		cartRepository.deleteById(cartId);
	}
	
	
}
