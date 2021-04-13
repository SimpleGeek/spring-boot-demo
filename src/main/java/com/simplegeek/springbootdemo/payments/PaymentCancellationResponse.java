package com.simplegeek.springbootdemo.payments;

public class PaymentCancellationResponse {
	public boolean paymentCancelled;
	
	public PaymentCancellationResponse(boolean paymentCancelled) {
		this.paymentCancelled = paymentCancelled;
	}
}
