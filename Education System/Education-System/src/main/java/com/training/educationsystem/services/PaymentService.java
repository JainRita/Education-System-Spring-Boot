package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exceptions.PaymentException;

/**
 * 
 * @author Anisha
 *
 */
public interface PaymentService {
	Payment addPayment(Payment payment);

	Payment getPaymentById(int paymentId) throws PaymentException;

	List<Payment> viewPayment() throws PaymentException;

}
