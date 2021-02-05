package com.training.educationsystem.controller;
/**
 * Implementing controller for question_table
 * @author Rita
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.QuestionException;
import com.training.educationsystem.services.IQuestionService;;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/educationsystem/question")
public class QuestionController {
	
	/**
	 * Initialzing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationSystemController.class);
	
	@Autowired
	IQuestionService questionObj;
	
	/***
	 * Used for testing path
	 * @return String
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello Everyone!!!";
	}
	
	/**
	 * Used for adding question
	 * @param question
	 * @return question
	 * @throws EmptyInputException
	 */
	@PostMapping(value="/add")
	public Question addQuestion(@RequestBody Question question) throws EmptyInputException{
		 Question t = questionObj.addQuestion(question);
		 LOGGER.info("Adding Questions");
		 return t;
	}
	
	/**
	 * Used for updating question
	 * @param question
	 * @return question
	 * @throws EmptyInputException
	 */
	@PutMapping(value="/update")
	public Question updateQuestion(@RequestBody Question question) throws EmptyInputException{
		LOGGER.info("Updating Questions");
		return questionObj.updateQuestion(question);
	}
	
	/***
	 * Used for deleting the question
	 * @param id
	 * @return list of all questions
	 * @throws QuestionException
	 */
	@DeleteMapping(value="/delete/{questionId}")
	public List<Question> removeQuestion(@PathVariable("questionId") int id) throws QuestionException{
		questionObj.deleteQuestionById(id);
		LOGGER.info("Removing Question by Id");
		return  getAll();
	}
	
	/**
	 * Used for retrieving question field using questionId
	 * @param id
	 * @return question
	 * @throws QuestionException
	 */
	@GetMapping(value="/getQuestion/{questionId}")
	public Question getQuestion(@PathVariable("questionId") int id) throws QuestionException{
		LOGGER.info("Viewing Question By Id");
		return questionObj.viewQuestionById(id);
	}
	
	/**
	 * Used for viewing all the questions
	 * @return list of questions
	 */
	@GetMapping(value="/getAll")
	public List<Question> getAll(){
		LOGGER.info("Viewing all questions");
		return questionObj.viewAllQuestions();
	}
}




