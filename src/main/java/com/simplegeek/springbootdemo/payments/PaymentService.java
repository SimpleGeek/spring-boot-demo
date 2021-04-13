package com.simplegeek.springbootdemo.payments;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repository;
	
	public List<Payment> getPaymentsByOrderId(long orderId) {
		return repository.getPaymentsByOrderId(orderId);
	}
	
	public boolean cancelPayment(long paymentId) {
		Payment p = repository.getPaymentById(paymentId);
		boolean cancelled = p.cancelPayment();
		if (cancelled) {
			// Only update the record in the database
			// if the payment was actually modified.
			repository.updatePayment(p);
		}
		return cancelled;
	}
	
	public PaymentCreationResponse createPayment(CreatePaymentRequest request) {
		PaymentCreationResponse resp;
		try {
			Payment p = new Payment(Instant.now(), PaymentStatus.Incomplete, request.amount, request.orderId);
			long newPaymentId = repository.createPayment(p);
			resp = new PaymentCreationResponse(true, newPaymentId, "Payment created successfully");
		} catch (PaymentDateInvalidException e) {
			resp = new PaymentCreationResponse(false, 0, e.getMessage());
		} catch (PaymentIdModificationException e) {
			resp = new PaymentCreationResponse(false, 0, e.getMessage());
		}
		return resp;
	}
}
