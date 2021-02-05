package com.training.educationsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.exceptions.DateException;
import com.training.educationsystem.exceptions.ErrorMessages;
import com.training.educationsystem.exceptions.InvalidPaymentException;
import com.training.educationsystem.exceptions.PaymentException;
import com.training.educationsystem.services.IPaymentService;

/**
 * 
 * @author Anisha.
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/educationsystem")
public class PaymentController {
	/**
	 * Initialzing Logger.
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	transient private IPaymentService paymentService;

	/**
	 * This method adds Payment for the course after enrollment
	 * 
	 * @param payment
	 * @return Payment
	 * @throws InvalidPaymentException
	 */
	@PostMapping("/add-Payment")
	public Payment addPayment(@RequestBody final Payment payment) throws InvalidPaymentException,DateException {
		LOGGER.info("adding payment details - start");

		final String alphaPattern = "[a-zA-Z]+";
		final String cardNoPattern = "[0-9]{9}";
		final String datePattern = "^(1[0-2]|0[1-9])/(3[01]"
                + "|[12][0-9]|0[1-9])/[0-9]{4}$";
		final List<String> bankNameList = new ArrayList<>();
		bankNameList.add("Axis");
		bankNameList.add("HDFC");
		bankNameList.add("ICICI");
		bankNameList.add("Citi");
		bankNameList.add("Saraswat");
		

		if (!(Pattern.matches(alphaPattern, payment.getCardType()))) {
			LOGGER.error("Card type or bank name cannot contain integer values or should not be null");
			throw new InvalidPaymentException(
					"Card type or bank name cannot contain integer values or should not be null");
		}
		else if(!(bankNameList.contains(payment.getBankName())))
		{
			LOGGER.error("Please enter from the following list: Axis, HDFC, ICICI, Citi, Saraswat");
			throw new InvalidPaymentException("Please enter from the following list: Axis, HDFC, ICICI, Citi, Saraswat");
		}

		else if (!(Pattern.matches(cardNoPattern, String.valueOf(payment.getCardNumber())))) {
			LOGGER.error("Card Number should be of length 9");
			throw new InvalidPaymentException("Card Number should be of length 9");
		} else if (payment.getAmount() == 0 || payment.getDescription() == null) {
			LOGGER.error("Amount and Description cannot contain null values");
			throw new InvalidPaymentException("Amount and Description cannot contain null values");
		} else if (!(Pattern.matches(datePattern, payment.getPaymentDate()))) {
			LOGGER.error("Please enter in the format dd/mm/yyyy");
			throw new DateException("Please enter in the format dd/mm/yyyy");
		}

		else {
			final Payment addPayment = paymentService.addPayment(payment);
			LOGGER.info("adding payment details - end");
			return addPayment;

		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPaymentException.class)
	public ErrorMessages exceptionHandler(final InvalidPaymentException invalidPayEx) {
		return new ErrorMessages("400", invalidPayEx.str);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PaymentException.class)
	public ErrorMessages exceptionHandler(PaymentException paymentEx) {
		return new ErrorMessages("404", paymentEx.str);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateException.class)
	public ErrorMessages exceptionHandler(final DateException dateEx) {
		return new ErrorMessages("400", dateEx.str);
	}

	/**
	 * This method views the payment details by respective Id
	 * 
	 * @param id
	 * @return Payment
	 * @throws PaymentException
	 */

	@GetMapping("/get-Payment/{transactionId}")
	public Payment getPaymentById(@PathVariable("transactionId") final int transactionId) throws PaymentException {
		LOGGER.info("viewing payment by id - start");
		final Payment getPayment = paymentService.getPaymentById(transactionId);
		LOGGER.info("viewing payment by id - end");
		return getPayment;
	}

	/**
	 * This method returns all payment details as list
	 * @return List
	 * @throws PaymentException
	 */

	@GetMapping("/view-Payment")
	public List<Payment> viewPayment() throws PaymentException {
		LOGGER.info("viewing payment list - start");
		final List<Payment> getPaymentList = paymentService.viewPayment();
		LOGGER.info("viewing payment list - end");
		return getPaymentList;
	}



}
