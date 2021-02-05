package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Feedback;
import com.training.educationsystem.exceptions.InvalidFeedbackException;

/**
 * 
 * @author Prajakta
 *
 */
public interface IFeedbackService {

	Feedback addFeedback(String sname, String feedback);

	List<Feedback> getAllFeedback() throws InvalidFeedbackException;

	Feedback updateFeedbackForReply(int id, String reply) throws InvalidFeedbackException;

	String viewReply(int id) throws InvalidFeedbackException;

}
