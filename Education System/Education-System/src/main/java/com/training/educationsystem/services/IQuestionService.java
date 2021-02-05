package com.training.educationsystem.services;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.QuestionException;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.repositories.QuestionRepository;

/**
 * Implementing Service Layer for Question
 * 
 * @author Rita
 *
 */
@Transactional
@Service
public class IQuestionService {
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IQuestionService.class);

	@Autowired
	transient private QuestionRepository questionRepo;

	/***
	 * Add Question to question_table
	 * 
	 * @param question
	 * @return question
	 * @throws EmptyInputException
	 */
	public Question addQuestion(final Question question) throws EmptyInputException {
		LOGGER.info("Inside Service Layer for Adding Questions...START");
		if (question.getQuestion() != null && question.getOption1() != null && question.getOption2() != null
				&& question.getOption3() != null && question.getOption4() != null
				&& question.getCorrectAnswer() != null) {
			LOGGER.info("Question Added Sucessfully...END");
			Question q = questionRepo.save(question);
			LOGGER.info("");
			return q;
		} else {
			LOGGER.error("EmptyInputException occured!..END");
			throw new EmptyInputException("Input provided are empty!");
		}
	}

	/***
	 * We can view question by Id
	 * 
	 * @param id
	 * @return question
	 * @throws QuestionException
	 */
	public Question viewQuestionById(final int id) throws QuestionException {
		LOGGER.info("Inside Service Layer for viewing Question By Id...START");
		final Question question = questionRepo.findById(id).orElse(null);
		if (question == null) {
			LOGGER.error("QuestionException occured!..END");
			throw new QuestionException("Questions not found!!");
		} else {
			LOGGER.info("View Question By Id...END");
			return question;
		}
	}

	/***
	 * Display all questions from question_table
	 * 
	 * @return list
	 */
	public List<Question> viewAllQuestions() {
		LOGGER.info("Inside Service Layer for viewing All Questions...");
		List<Question> questions = questionRepo.findAll();
		return questions;
	}

	/***
	 * Updating Question
	 * 
	 * @param question
	 * @return Question
	 * @throws EmptyInputException
	 */
	public Question updateQuestion(final Question question) throws EmptyInputException {
		LOGGER.info("Inside Service Layer for Adding Questions...START");
		if (question.getQuestion() != null && question.getOption1() != null && question.getOption2() != null
				&& question.getOption3() != null && question.getOption4() != null
				&& question.getCorrectAnswer() != null) {
			LOGGER.info("Updating Question...END");
//			return questionRepo.findById(question.getQuestionId()).map(ques -> {
//				ques.setQuestion(question.getQuestion());
//				ques.setOption1(question.getOption1());
//				ques.setOption2(question.getOption2());
//				ques.setOption3(question.getOption3());
//				ques.setOption4(question.getOption4());
//				ques.setCorrectAnswer(question.getCorrectAnswer());
//				LOGGER.info("Saving the Updated Question...END");
//				return questionRepo.save(question);
//			}).orElseGet(() -> {
//				question.setQuestionId(question.getQuestionId());
//				LOGGER.info("Updating the question by new questionId");
//				return questionRepo.save(question);
//			});
			return questionRepo.saveAndFlush(question);
		} else {
			LOGGER.error("EmptyInputException occured!..END");
			throw new EmptyInputException("Input provided are empty!");
		}
	}

	/***
	 * delete any question from question_table
	 * 
	 * @param id
	 * @return question
	 * @throws TestException
	 */
	public void deleteQuestionById(final int id) throws QuestionException {
		LOGGER.info("Inside Service Layer for removing Question By Id...START");
		Question question = questionRepo.findById(id).orElse(null);
		if (question == null) {
			LOGGER.error("QuestionException occured...END");
			throw new QuestionException("Test cannot be found!");
		} else {
//			question = questionRepo.getOne(id);
//			questionRepo.delete(question);
			LOGGER.info("Deleted Question Sucessfully...END");
			questionRepo.deleteById(id);
		}
	}
}
