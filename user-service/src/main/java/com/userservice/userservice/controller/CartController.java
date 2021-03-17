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
import com.userservice.userservice.service.CartService;



@RestController
@CrossOrigin
public class CartController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CartService cartService;
	
	@Autowired
	private Environment environment;
	
	//***Implementation of findCartByBuyerID
	@GetMapping(value = "/cart/buyer/{buyerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartDTO>> findCartByBuyerId(@PathVariable Integer buyerId) {
		logger.info("===GET Request for Carts with buyerId{}", buyerId);
		try {
			List<CartDTO> cartDTOList = cartService.getByBuyerId(buyerId);
			ResponseEntity<List<CartDTO>> response = new ResponseEntity<List<CartDTO>>(cartDTOList, HttpStatus.OK);
			return response;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}
			
	//***Implementation of addProductToCart
	@PostMapping(value = "/cart/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody CartDTO cartDTO) {
		logger.info("====Cart Creation Request for cart with data {}", cartDTO);
		try {
			cartService.addToCart(cartDTO);
			String msg = ""+ cartDTO + " " + environment.getProperty("CART.ADDED");
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
	}
				
	//***Implementation of deleteFromCart
	@DeleteMapping(value = "/cart/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeFromCart(@RequestBody CartIdDTO cartIdDTO) {
		logger.info("======Request to Remove From Cart=========");
		cartService.deleteFromCart(cartIdDTO);
	}
			
}
