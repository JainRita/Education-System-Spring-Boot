package com.training.educationsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Feedback;
import com.training.educationsystem.exceptions.InvalidFeedbackException;
import com.training.educationsystem.repositories.FeedbackRepository;

/**
 * 
 * @author Prajakta
 *
 */
@Transactional
@Service
public class FeedbackService implements IFeedbackService {
	
	/**
	 * Initializing Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackService.class);
	
	/**
	 * Initializing FeedbackRepository
	 */
	@Autowired
	transient private FeedbackRepository feedbackRepo;

	/**
	 * adding feedback
	 * 
	 * @param sname
	 * @param feedback
	 * @return Feedback object
	 * 
	 */
	@Override
	public Feedback addFeedback(final String sname, final String feedback) {
		LOGGER.info("Adding data in feedback service-START");
		final Feedback feedback1 = new Feedback();
		feedback1.setSname(sname);
		feedback1.setFeedback(feedback);
		final Feedback addFeedback = feedbackRepo.save(feedback1);
		LOGGER.info("Done in feedback service-END");
		return addFeedback;
	}

	/**
	 * updating feedback reply
	 * 
	 * @param id
	 * @param reply
	 * @return Feedback object
	 * @throws InvalidFeedbackException
	 * 
	 */
	@Override
	public Feedback updateFeedbackForReply(final int id, final String reply) throws InvalidFeedbackException {
		final Feedback addReply = feedbackRepo.findById(id).orElse(null);
		LOGGER.info("updating feedbackreply in feedback service");
		if (addReply == null) {
			LOGGER.error("Reply of the mentioned Id cannot be FOUND");
			throw new InvalidFeedbackException("Feedback of the mentioned Id cannot be found");
		} else {
			addReply.setReply(reply);
			final Feedback updateFeedback = feedbackRepo.save(addReply);
			LOGGER.info("done in feedback service");
			return updateFeedback;
		}
	}

	/**
	 * shows all feedback present
	 * 
	 * @return Feedback List
	 * @throws InvalidFeedbackException
	 * 
	 */
	@Override
	public List<Feedback> getAllFeedback() throws InvalidFeedbackException {
		LOGGER.info("getting all feedback data in feedback service");
		final List<Feedback> feedbackList = feedbackRepo.findAll();
		if (feedbackList.size() > 0) {
			LOGGER.info("done in feedback service");
			return feedbackList;
		} else {
			LOGGER.error("NO Feedback to show");
			throw new InvalidFeedbackException("No Feedbacks to show");
		}
	}

	/**
	 * shows reply of the mentioned Id
	 * 
	 * @return reply of id
	 * @throws InvalidFeedbackException
	 * 
	 */
	@Override
	public String viewReply(final int id) throws InvalidFeedbackException {
		LOGGER.info("Showing reply in feedback service-START");
		final Feedback viewReply = feedbackRepo.findById(id).orElse(null);
		if (viewReply == null) {
			LOGGER.error("Reply of the mentioned Id cannot be FOUND");
			throw new InvalidFeedbackException("Reply of the mentioned Id cannot be FOUND");
		} else {
			final String reply = viewReply.getReply();
			LOGGER.info("Done in feedback service-END");
			return reply;
		}

	}
}
