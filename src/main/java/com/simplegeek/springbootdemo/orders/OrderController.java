package com.simplegeek.springbootdemo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;
	
	@GetMapping("/api/orders/{orderId}")
	public Order findOrderById(@PathVariable("orderId") long orderId) {
		return service.findOrderById(orderId);
	}
	
	@PostMapping("/api/orders/ship/{orderId}")
	public ShipOrderResponse markOrderShipped(@PathVariable("orderId") long orderId) {
		return service.markOrderShipped(orderId);
	}
}
