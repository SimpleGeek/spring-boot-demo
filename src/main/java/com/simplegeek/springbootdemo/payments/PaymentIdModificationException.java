package com.simplegeek.springbootdemo.payments;

public class PaymentIdModificationException extends Exception {
	private static final long serialVersionUID = -1947970216638423699L;
	
	public PaymentIdModificationException(long currentPaymentId, long newPaymentId) {
		super("Payments with an existing ID cannot have a new payment ID assigned.  Current ID: " + currentPaymentId
				+ " New payment ID: " + newPaymentId);
	}
}
