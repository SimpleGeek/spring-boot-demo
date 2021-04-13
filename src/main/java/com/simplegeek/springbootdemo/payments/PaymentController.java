package com.simplegeek.springbootdemo.payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	@Autowired
	private PaymentService service;
	
	@GetMapping("/api/payments/getpayments/{orderId}")
	public List<Payment> getPaymentsByOrderId(@PathVariable("orderId")long orderId) {
		return service.getPaymentsByOrderId(orderId);
	}
	
	@PostMapping("/api/payments/cancel/{paymentId}")
	public PaymentCancellationResponse cancelPayment(@PathVariable("paymentId")long paymentId) {
		return new PaymentCancellationResponse(service.cancelPayment(paymentId));
	}
	
	@PostMapping("/api/payments/create")
	public PaymentCreationResponse createPayment(@RequestBody CreatePaymentRequest createRequest) {
		return service.createPayment(createRequest);
	}
	
}
