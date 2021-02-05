package com.training.educationsystem.controller;


//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.training.educationsystem.entities.Question;
//import com.training.educationsystem.exceptions.EmptyInputException;
//import com.training.educationsystem.exceptions.ErrorResponse;
//import com.training.educationsystem.exceptions.QuestionException;
//import com.training.educationsystem.exceptions.TestException;
//import com.training.educationsystem.services.IQuestionService;

/***
 * Implementing Controller for Test 
 * @author Rita.
 *
 */
//@RestController
//@RequestMapping("/api/educationsystem/question")
//public class QuestionController {	
//	/**
//	 * Initialzing Logger.
//	 */
//	private static final Logger LOGGER = LoggerFactory.getLogger(EducationSystemController.class);
//	
//	@Autowired
//	transient private IQuestionService questionService;
//	
//	/***
//	 * Implementing EmptyInputException
//	 * @param e
//	 * @return Error Response
//	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(EmptyInputException.class)
//	public ErrorResponse handlingEmptyException(final EmptyInputException emptyInputEx) {
//		return new ErrorResponse("Input provided are empty!", "400");
//	}
//	
//	
//	/***
//	 * Implementing QuestionException
//	 * @param e
//	 * @return Error Response
//	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(QuestionException.class)
//	public ErrorResponse handlingException(final QuestionException questionEx) {
//		return new ErrorResponse("Questions not found!", "404");
//	}
//
//	/***
//	 * Add Question Request Handler
//	 * @param question for adding question in question_table
//	 * @return Response Entity
//	 * @throws EmptyInputException 
//	 */
//	@PostMapping("/add-question")
//	public ResponseEntity<Question> addQuestion(@RequestBody final Question question) throws EmptyInputException{
//		final Question addquestion = questionService.addQuestion(question);
//		LOGGER.info("Adding Questions...");
//		return new ResponseEntity<Question>(addquestion, HttpStatus.OK);
//	}
//	
//	/***
//	 * Viewing Question By Taking Input as Id
//	 * @param id
//	 * @return Question 
//	 * @throws QuestionException
//	 */
//	@GetMapping("/get-quesId")	
//	public Question getQuestion(@RequestParam("id") final int questionId) throws QuestionException{
//		final Question getById = questionService.viewQuestionById(questionId);
//		LOGGER.info("Displaying Question by Id:");
//		return getById;
//	}
//	
//	/***
//	 * Displaying All Questions from question_table
//	 * @return Response Entity
//	 * @throws TestException 
//	 */
//	@GetMapping("/get-question-list")
//	public List<Question> getList() throws QuestionException{
//		final List<Question> getlist = questionService.viewAllQuestions();
//		LOGGER.info("Displaying All Question...");
//		if(getlist == null) {
//			throw new QuestionException("No question found");
//		}
//		return getlist;
//	}
//	
//	/***
//	 * Updating Questions
//	 * @param question
//	 * @return Response Entity
//	 * @throws EmptyInputException
//	 */
//	@PostMapping("/update-question")
//	public ResponseEntity<Question> updateQuestion(@RequestBody final Question question) throws EmptyInputException{
//		final Question updatequestion = questionService.updateQuestion(question);
//		LOGGER.info("Updating Question Records for Id");
//		return new ResponseEntity<Question>(updatequestion, HttpStatus.OK);
//	}
//	
//	/***
//	 * Deleting question for a specific Id
//	 * @param id
//	 * @return
//	 * @throws TestException 
//	 */
//	@DeleteMapping("/remove-question")
//	public Question removeQuestions(@RequestParam("id") final int questionId) throws QuestionException{
//		final Question removeQuestion = questionService.deleteQuestionById(questionId);
//		LOGGER.info("Removing Question by Id");
//		return removeQuestion;
//	}
//}

import java.util.List;

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
	
	@Autowired
	IQuestionService questionObj;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Everyone!!!";
	}

	@PostMapping(value="/add")
	public Question addQuestion(@RequestBody Question question) throws EmptyInputException{
		 Question t = questionObj.addQuestion(question);
		 return t;
	}
	
	@PutMapping(value="/update")
	public Question updateQuestion(@RequestBody Question question) throws EmptyInputException{
		return questionObj.updateQuestion(question);
	}
	
	@DeleteMapping(value="/delete/{questionId}")
	public List<Question> removeQuestion(@PathVariable("questionId") int id) throws QuestionException{
		questionObj.deleteQuestionById(id);
		return  getAll();
	}
	
	@GetMapping(value="/getQuestion/{questionId}")
	public Question getQuestion(@PathVariable("questionId") int id) throws QuestionException{
		return questionObj.viewQuestionById(id);
	}
	
	@GetMapping(value="/getAll")
	public List<Question> getAll(){
		return questionObj.viewAllQuestions();
	}
}




