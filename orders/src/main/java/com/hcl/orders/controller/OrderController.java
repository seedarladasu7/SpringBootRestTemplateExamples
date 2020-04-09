package com.hcl.orders.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.orders.dto.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	static String sysoOrderCtrl = "from order controller ===============>>";

	@GetMapping("")
	public List<Order> getAll() {

		System.out.println(sysoOrderCtrl);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(101);
		order.setDes("desc1");
		orders.add(order);

		order = new Order();
		order.setId(102);
		order.setDes("desc2");
		orders.add(order);

		return orders;
	}

	@GetMapping("/{userId}")
	public List<Order> getAllById(@PathVariable String userId) {

		System.out.println(sysoOrderCtrl + userId);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(103);
		order.setDes("desc3");
		orders.add(order);

		order = new Order();
		order.setId(104);
		order.setDes("desc4");
		orders.add(order);

		return orders;
	}

	@GetMapping("/byReqParam")
	public List<Order> getAllByReqParam(@RequestParam String userId) {

		System.out.println(sysoOrderCtrl + userId);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(105);
		order.setDes("desc5");
		orders.add(order);

		order = new Order();
		order.setId(106);
		order.setDes("desc6");
		orders.add(order);

		return orders;
	}

	@PostMapping("/byPostReqParam")
	public List<Order> getAllByPostReqParam() {

		System.out.println(sysoOrderCtrl);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setId(107);
		order.setDes("desc7");
		orders.add(order);

		order = new Order();
		order.setId(108);
		order.setDes("desc8");
		orders.add(order);

		return orders;
	}

	@PostMapping("/byBody")
	public Order getAllByPostReqBody(@RequestBody Order order) {

		System.out.println(sysoOrderCtrl);

		order.setDes(order.getDes() + " updated");

		return order;
	}
}
