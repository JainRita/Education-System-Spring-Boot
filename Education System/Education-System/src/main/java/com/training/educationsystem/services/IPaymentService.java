package com.training.educationsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exceptions.InvalidPaymentException;
import com.training.educationsystem.exceptions.PaymentException;
import com.training.educationsystem.repositories.PaymentRepository;

/**
 * 
 * @author Anisha
 *
 */
@Transactional
@Service
public class IPaymentService implements PaymentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IPaymentService.class);
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * This method adds Payment for the course after enrollment
	 * 
	 * @param payment
	 * @return Payment
	 * @throws InvalidPaymentException
	 */
	@Override
	public Payment addPayment(final Payment payment) {
		LOGGER.info("service adding payment details - start");
		paymentRepository.save(payment);
		LOGGER.info("service adding payment details - end");
		return payment;
	}

	/**
	 * This method views the payment details by respective Id
	 * 
	 * @param id
	 * @return Payment
	 */

	@Override
	public Payment getPaymentById(final int id) throws PaymentException {
		LOGGER.info("service viewing payment by id - start");
		final Payment payment = paymentRepository.findById(id).orElse(null);
		if (payment == null) {
			LOGGER.error("Payment details not found");
			throw new PaymentException("Payment details not found");
		} else {
			LOGGER.info("service viewing payment by id - end");
			return payment;
		}

	}

	/**
	 * This method returns all payment details as list
	 * 
	 * @return List
	 */

	@Override
	public List<Payment> viewPayment() throws PaymentException {
		LOGGER.info("service viewing payment list - start");
		final List<Payment> paymentList = paymentRepository.findAll();
		if (paymentList.size() > 0) {
			LOGGER.info("service viewing payment list - end");
			return paymentList;
		} else {
			LOGGER.error("Payment details not found");
			throw new PaymentException("Payment details not found");
		}

	}
}