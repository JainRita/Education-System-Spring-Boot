package com.training.educationsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Message;
import com.training.educationsystem.exceptions.InvalidInputException;
import com.training.educationsystem.exceptions.InvalidMessageException;
import com.training.educationsystem.services.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/educationsystem/message")
public class MessageController {

	/**
	 * Initializing Logger
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	transient private MessageService messageService;

	/*
	 * @param messageDate 
	 * @param messageDescription
	 * admin will broadcast the messages to the students by giving the date the
	 * description of message this method will save the message in the database and
	 * @return message save in the database
	 * throw the InvalidInputException if any of
	 * the fields are invalid
	 */
	@PostMapping("/add-message")
	public Message addMessage(@RequestParam("messageDate") final String date,
			@RequestParam("messageDescription") final String description) throws InvalidMessageException {
		// LOGGER for admin broadcasting message
		LOGGER.info("admin is adding message-START");
		if (date.isEmpty()) {
			LOGGER.error("date empty error thrown.....");
			throw new InvalidInputException("Date cannot be null");
		}
		if (!(date.matches("^\\d{4}-\\d{2}-\\d{2}$"))) {
			LOGGER.error("date invalid error thrown.....");
			throw new InvalidInputException("Date is invalid");
		}
		if (description.isEmpty()) {
			LOGGER.error("description invalid error thrown.....");
			throw new InvalidInputException("Description cannot be empty ");
		} else {
			LOGGER.info("admin added message successfully-END");
			return messageService.addMessage(LocalDate.parse(date), description);
		}

	}

	/*
	 * student/admin will view all the messages broadcasted by the admin this method
	 * @return all the messages in the database
	 */
	@GetMapping("/view-all-messages")
	public List<Message> viewAllMessages() throws InvalidMessageException {
		// LOGGER for viewing all the messages
		LOGGER.info("viewing all the messages-START-END");
		return messageService.viewAllMessages();
	}

	/*
	 * @param messageId
	 * student/admin will view only one message broadcasted by the admin by giving  by giving the message id 
	 * @return message by messageId
	 */
	@GetMapping("/view-message")
	public Message viewMessage(@RequestParam("messageId") final int messageId) throws InvalidMessageException {
		// LOGGER for viewing message
		LOGGER.info("viewing message-START");
		if (messageId < 0) {
		    LOGGER.error("message id negative error is thrown.....");
			throw new InvalidInputException("Message id cannot be negative :" + messageId);
		}
		if (messageId == 0) {
			LOGGER.error("message id zero error is thrown.....");
			throw new InvalidInputException("Message id cannot be zero :" + messageId);
		}
		if (messageId > 9999) {
			LOGGER.error("message id invalid error is thrown.....");
			throw new InvalidInputException("Message id cannot be greater than 9999 :" + messageId);
		} else {
			LOGGER.info("message is viewed-END");
			return messageService.viewMessage(messageId);
		}
	}

}
