package com.simplegeek.springbootdemo.payments;

public class PaymentCreationResponse {
	public boolean createdSuccessfully;
	public long paymentId;
	public String msg;
	
	public PaymentCreationResponse(boolean createdSuccessfully, long paymentId, String msg) {
		this.createdSuccessfully = createdSuccessfully;
		this.paymentId = paymentId;
		this.msg = msg;
	}
}
