package com.hcl.feign.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.feign.user.dto.Order;

@FeignClient(name = "http://ORDER-SERVICE/demo/orders")
public interface OrderClient {
	
	@GetMapping("")
	public List<Order> getAll();
	
	@GetMapping("/{userId}")
	public List<Order> getUserOrdersById(@PathVariable("userId") String userId);
	
	@GetMapping("/byReqParam")
	public List<Order> getUserOrdersByReqParam(@RequestParam("userId") String userId);
	
	@PostMapping("/postParam")
	public List<Order> testPostWithParam(@RequestParam("userId") String userId);
	
	@GetMapping("/byBody")
	public Order testReqBody(Order order);

}
