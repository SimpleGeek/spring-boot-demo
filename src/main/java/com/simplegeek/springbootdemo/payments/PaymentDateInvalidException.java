package com.simplegeek.springbootdemo.payments;

import java.time.Instant;

public class PaymentDateInvalidException extends Exception {
	private static final long serialVersionUID = 8441875738193783835L;
	
	private Instant dateReceived;
	
	public PaymentDateInvalidException(String msg, Instant dateReceived) {
		super(msg);
		this.dateReceived = dateReceived;
	}
	
	public Instant getDateReceived() {
		return this.dateReceived;
	}
}
