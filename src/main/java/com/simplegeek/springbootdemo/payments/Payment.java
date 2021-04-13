package com.simplegeek.springbootdemo.payments;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Payment implements Comparable<Payment> {
	private long paymentId;
	private Instant paymentDt;
	private PaymentStatus status;
	private BigDecimal amount;
	private long orderId;
	
	public Payment(Instant paymentDt, PaymentStatus status, BigDecimal amount, long orderId) throws PaymentDateInvalidException {
		this(0, paymentDt, status, amount, orderId);
	}
	
	public Payment(long paymentId, Instant paymentDt, PaymentStatus status, BigDecimal amount, long orderId) throws PaymentDateInvalidException {
		this.paymentId = paymentId;
		
		if (paymentDt.isAfter(Instant.now())) {
			// Payments can't be in the future
			throw new PaymentDateInvalidException("Payment dates cannot be in the future", paymentDt);
		}
		this.paymentDt = paymentDt;
		
		this.status = status;
		this.amount = amount;
	}
	
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) throws PaymentIdModificationException {
		if (this.paymentId == 0) {
			this.paymentId = paymentId;
		} else {
			throw new PaymentIdModificationException(this.paymentId, paymentId);
		}
	}
	public Instant getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(Instant paymentDt) {
		this.paymentDt = paymentDt;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if (o instanceof Payment && o != null) {
			isEqual = ((Payment)o).getPaymentId() == this.getPaymentId();
		}
		return isEqual;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.getPaymentId());
	}
	
	@Override
	public int compareTo(Payment p) {
		int c = 0;
		if (this.paymentId > p.paymentId) {
			c = 1;
		} else if (p.paymentId > this.paymentId) {
			c = -1;
		}
		return c;
	}
	
	// Domain logic methods
	
	/**
	 * Method to cancel the payment.
	 * 
	 * @return true if payment was cancelled, else false
	 */
	public boolean cancelPayment() {
		if (this.getPaymentDt().isAfter(Instant.now().minus(30, ChronoUnit.DAYS))
				&& this.getStatus() == PaymentStatus.Incomplete) {
			// Payments can only be canceled if they are <= 30 days old and Incomplete
			this.setStatus(PaymentStatus.Cancelled);
		}
		return this.getStatus() == PaymentStatus.Cancelled;
	}	
}
