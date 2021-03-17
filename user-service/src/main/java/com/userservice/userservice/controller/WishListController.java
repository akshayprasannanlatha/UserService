package com.userservice.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.userservice.userservice.dto.CartDTO;
import com.userservice.userservice.dto.CartIdDTO;
import com.userservice.userservice.dto.WishListIdDTO;
import com.userservice.userservice.dto.WishlistDTO;
import com.userservice.userservice.service.CartService;
import com.userservice.userservice.service.WishlistService;



@RestController
@CrossOrigin
public class WishListController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WishlistService wishListService;
	
	@Autowired
	private Environment environment;
	
	//***Implementation of findCartByBuyerID

			
	//***Implementation of addProductToCart
	@PostMapping(value = "/wishlist/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody WishlistDTO wishListDTO) {
		logger.info("====Cart Creation Request for cart with data {}", wishListDTO);
		try {
			wishListService.addToWishList(wishListDTO);
			String msg = ""+ wishListDTO + " " + environment.getProperty("CART.ADDED");
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
	}
				
	//***Implementation of deleteFromCart
	@DeleteMapping(value = "/wishlist/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeFromCart(@RequestBody WishListIdDTO wishListIdDTO) {
		logger.info("======Request to Remove From Cart=========");
		wishListService.deleteFromWishList(wishListIdDTO);
	}
			
}
