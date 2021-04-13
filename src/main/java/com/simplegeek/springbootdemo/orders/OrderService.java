package com.simplegeek.springbootdemo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	public Order findOrderById(long orderId) {
		return repository.findOrderById(orderId);
	}
	
	public ShipOrderResponse markOrderShipped(long orderId) {
		Order o = repository.findOrderById(orderId);
		ShipOrderResponse resp = new ShipOrderResponse(false);
		if (o.ship()) {
			resp.shipped = true;
			repository.updateOrder(o);
		}
		return resp;
	}
}
