package com.simplegeek.springbootdemo.orders;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
	private static List<Order> orders = new LinkedList<>();	
	
	static {
		orders.add(new Order(List.of(new OrderItem(1, 1, BigDecimal.valueOf(20.00), 1)), 1, Instant.now().minus(30, ChronoUnit.DAYS),
				OrderStatus.Received));
		orders.add(new Order(List.of(new OrderItem(1, 2, BigDecimal.valueOf(35.00), 2)), 2, Instant.now().minus(27, ChronoUnit.DAYS),
				OrderStatus.Returned));
		orders.add(new Order(List.of(new OrderItem(1, 3, BigDecimal.valueOf(15.00), 1), new OrderItem(2, 1, BigDecimal.valueOf(20.00), 2)),
				3, Instant.now().minus(5, ChronoUnit.DAYS), OrderStatus.Shipped));
		orders.add(new Order(List.of(new OrderItem(1, 5, BigDecimal.valueOf(100.00), 10)), 4, Instant.now().minus(1, ChronoUnit.DAYS),
				OrderStatus.Working));
	}
	
	public Order findOrderById(long orderId) {
		return orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);
	}
	
	public void updateOrder(Order order) {
		orders = orders.stream().map(o -> {
			if (o.getOrderId() == order.getOrderId()) {
				return order;
			} else {
				return o;
			}
		}).collect(Collectors.toList());
	}
}
