package com.simplegeek.springbootdemo.payments;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Repository class with a static list of POJOs to simulate pulling
 * data from and updating a database.  There's a lot of logic in this
 * class, which isn't good practice for a real repository; this kind
 * of filtering logic should either be in the database query itself,
 * or the query should return something generic (like all payments
 * in the database) and the service class should do this kind of filtering.
 * However, since we don't have a real database, this filtering logic is
 * here to simulate having queries with restricting logic.
 */
public class PaymentRepository {
	Logger l = LoggerFactory.getLogger(PaymentRepository.class);
	
	// In-memory array substitutes for actual database
	private static List<Payment> payments = new LinkedList<>();
	
	static {
		try {
			payments.add(new Payment(1, Instant.now().minus(2, ChronoUnit.DAYS), PaymentStatus.Complete, BigDecimal.valueOf(25.00), 1));
			payments.add(new Payment(2, Instant.now().minus(1, ChronoUnit.DAYS), PaymentStatus.Complete, BigDecimal.valueOf(150.99), 2));
			payments.add(new Payment(3, Instant.now(), PaymentStatus.Incomplete, BigDecimal.valueOf(10.10), 2));
			payments.add(new Payment(4, Instant.now().minus(150, ChronoUnit.DAYS), PaymentStatus.Cancelled, BigDecimal.valueOf(45.00), 3));
			payments.add(new Payment(5, Instant.now().minus(31, ChronoUnit.DAYS), PaymentStatus.Incomplete, BigDecimal.valueOf(25.00), 4));
		} catch (PaymentDateInvalidException e) {
			// This will never happen
		}
	}
	
	public List<Payment> getPaymentsByOrderId(long orderId) {
		return payments.stream().filter(p -> p.getOrderId() == orderId).collect(Collectors.toList());
	}
	
	public Payment getPaymentById(long paymentId) {
		return payments.stream().filter(p -> p.getPaymentId() == paymentId).findFirst().orElse(null);
	}
	
	public void updatePayment(Payment payment) {
		// Changing the individual item in the list
		// that has an id that matches that of the
		// new payment, and reassigning that list to
		// the static list.
		payments = payments.stream().map(p -> {
			if (p.equals(payment)) {
				return payment;
			} else {
				return p;
			}
		}).collect(Collectors.toList());
	}
	
	public long createPayment(Payment payment) throws PaymentIdModificationException {
		long newPaymentId = getMaxPaymentId() + 1;
		payment.setPaymentId(newPaymentId);
		payments.add(payment);
		return newPaymentId;
	}
	
	/*
	 * Private helpers
	 */
	private long getMaxPaymentId() {
		Collections.sort(payments);
		return payments.get(payments.size() - 1).getPaymentId();
	}
}
