package com.simplegeek.springbootdemo.orders;

import java.time.Instant;
import java.util.List;

public class Order {
	private List<OrderItem> orderItems;
	private long orderId;
	private Instant creationDt;
	private OrderStatus status;
	
	public Order(List<OrderItem> orderItems, long orderId, Instant creationDt, OrderStatus status) {
		this.orderItems = orderItems;
		this.orderId = orderId;
		this.creationDt = creationDt;
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Instant getCreationDt() {
		return creationDt;
	}

	public void setCreationDt(Instant creationDt) {
		this.creationDt = creationDt;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	// Domain logic methods
	public boolean ship() {
		boolean shipped = false;
		if (getStatus() == OrderStatus.Working) {
			shipped = true;
			setStatus(OrderStatus.Shipped);
		}
		return shipped;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if (o instanceof Order && o != null) {
			isEqual = ((Order)o).getOrderId() == this.getOrderId();
		}
		return isEqual;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.getOrderId());
	}
}
