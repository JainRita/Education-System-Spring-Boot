package com.training.educationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Feedback;
import com.training.educationsystem.exceptions.ErrorDetails;
import com.training.educationsystem.exceptions.InvalidFeedbackException;
import com.training.educationsystem.services.FeedbackService;

@CrossOrigin
@RestController
@RequestMapping("/api/educationsystem/feedback")
public class FeedbackController {
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	/**
	 * 
	 */
	@Autowired
	transient private FeedbackService service;

	/**
	 * Shows all list of feedback data 
	 * @return feedback list
	 * @throws InvalidFeedbackException
	 * 
	 */
	@GetMapping("/view-all-feedback")
	public List<Feedback> getAllFeedback() throws InvalidFeedbackException {
		LOGGER.info("Fetching all feedback data in feedback controller -START");
		final List<Feedback> feedbackList = service.getAllFeedback();
		LOGGER.info("Done in feedback controller -END");
		return feedbackList;
	}

	/**
	 * Adds feedback into the database
	 * 
	 * @param sname
	 * @param feedback
	 * @return feedback
	 * @throws InvalidFeedbackException
	 * 
	 */
	@PostMapping("/add-feedback")
	public Feedback addFeedback(@RequestParam("sname") final String sname,
			@RequestParam("feedback") final String feedback) throws InvalidFeedbackException {
		LOGGER.info("for adding feedback-START");
		final String pattern = "^[a-zA-Z0-9]*$";
		final String namePattern = "^[a-zA-Z]+$";

		if (sname == "") {
			LOGGER.error("name cannot be empty");
			throw new InvalidFeedbackException("name cannot be empty");
		}
		if (!(sname.matches(namePattern))) {
			LOGGER.error("Name cannot contain special symbol and numbers");
			throw new InvalidFeedbackException("sname cannot contain special symbol and numbers");
		}
		if (feedback == "") {
			LOGGER.error("feedback cannot be negative");
			throw new InvalidFeedbackException("feedback cannot be empty");
		}
//		if (!(feedback.matches(pattern))) {
//			LOGGER.error("feedback cannot contain special symbol");
//			throw new InvalidFeedbackException("feedback cannot contain special symbol");
//		} 
		else {
			LOGGER.info("feedback added succesfully..-END");
			return service.addFeedback(sname, feedback);
		}
	}
	
	

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidFeedbackException.class)
	public ErrorDetails exceptionHabler(final InvalidFeedbackException ex) {
		return new ErrorDetails("400", ex.message);
	}

	/**
	 * Adds reply to feedback by feedback id
	 * 
	 * @param id
	 * @param reply
	 * @return addReply
	 * @throws InvalidFeedbackException
	 * 
	 */
	@PatchMapping("/add-reply")
	public Feedback updateFeedbackForReply(@RequestParam("id") final int replyId, @RequestParam("reply") final String reply)
			throws InvalidFeedbackException {
		LOGGER.info("Updating reply in feedback controller-START");
		final Feedback addReply = service.updateFeedbackForReply(replyId, reply);
		LOGGER.info("Done in feedback controller-END");
		return addReply;
	}

	/**
	 * Shows reply of feedback by id
	 * 
	 * @param id
	 * @return reply
	 * 
	 */
	@GetMapping("/view-reply/{id}")
	public String viewReply(@PathVariable("id") final int replyId) throws InvalidFeedbackException {
		LOGGER.info("Showing reply in feedback controller-START");
		final String reply = service.viewReply(replyId);
		LOGGER.info("Done in feedback controller-END");
		return reply;
	}
}
