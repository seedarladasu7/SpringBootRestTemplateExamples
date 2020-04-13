package com.hcl.feign.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.feign.user.client.OrderClient;
import com.hcl.feign.user.dto.Order;

@RestController
@RequestMapping("/users")
public class FeignUserController {
	
	@Autowired
	OrderClient orderClient;
	
	@GetMapping("/info")
	public String getPorts() {
		return orderClient.getPort();
	}
	
	@GetMapping("")
	public List<Order> getUserOrders() {
		return orderClient.getAll();
	}
	
	@GetMapping("/{userId}")
	public List<Order> getUserOrdersById(@PathVariable("userId") String userId) {
		return orderClient.getUserOrdersById(userId);
	}
		
	@GetMapping("/byReqParam")
	public List<Order> getUserOrdersByReqParam(@RequestParam("userId") String userId) {
		return orderClient.getUserOrdersByReqParam(userId);
	}	
	
	@PostMapping("/postParam")
	public List<Order> testPostWithParam(@RequestParam("userId") String userId){
		return orderClient.testPostWithParam(userId);
	}
	
	@GetMapping("/byBody")
	public Order testReqBody(Order order) {
		return orderClient.testReqBody(order);
	}
}
