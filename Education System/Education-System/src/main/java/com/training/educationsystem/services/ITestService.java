package com.training.educationsystem.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.repositories.QuestionRepository;
import com.training.educationsystem.repositories.TestRepository;

/**
 * Implementing Service Layer for Test
 * 
 * @author Rita
 *
 */
@Transactional
@Service
public class ITestService {
	
	/**
	 * Initialzing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ITestService.class);

	@Autowired
	transient private TestRepository testRepo;

	@Autowired
	transient private QuestionRepository questionRepo;

	/***
	 * Add Test in test-table
	 * 
	 * @param test
	 * @return test
	 * @throws EmptyInputException
	 */
	public Test addTest(final Test test) throws EmptyInputException {
		LOGGER.info("Inside Service Layer for Adding Test...START");
		if (test.getNumberOfAttempts() != 0 && test.getScore() != 0 && test.getTestName() != null) {
			LOGGER.info("Test Added Sucessfully...END");
			testRepo.save(test);
			return test;
		} else {
			LOGGER.error("EmptyInputException occured!....END");
			throw new EmptyInputException("Input provided are empty!");
		}

	}

	/***
	 * Display test By Id
	 * 
	 * @param id
	 * @return test
	 * @throws TestException
	 */
	public Test getTestById(final int id) throws TestException {
		LOGGER.info("Inside Service Layer for viewing Test By Id...START");
		final Test test = testRepo.findById(id).orElse(null);
		if (test == null) {
			LOGGER.error("TestException occured!....END");
			throw new TestException("Test cannot be found!");
				
		} else {
			LOGGER.info("Displaying Test..END");
			return test;
		}
	}

	/***
	 * Display list of all test taken from test_table
	 * 
	 * @return test list
	 */
	public List<Test> getAllList() {
		LOGGER.info("Inside Service Layer for viewing all the Test...START");
		final List<Test> testList = testRepo.findAll();
		LOGGER.info("Inside Service Layer for viewing all the Test...END");
		return testList;
	}

	/***
	 * Adding questions of Question_table to question field of test_table
	 * 
	 * @param tid
	 * @return test
	 * @throws TestException
	 */
	public Test updateTestforQuestion(final int tid,final int qid) throws TestException {
		LOGGER.info("Inside Service Layer for Updating the Test for Questions...START");
		 Test test = testRepo.findById(tid).orElse(null);
		if (test == null) {
			LOGGER.error("TestException occured...END");
			throw new TestException("Test cannot be found!");
		} else {
			LOGGER.info("Updating Test By Adding Question...END");
			test = testRepo.getOne(tid);
			Question question = questionRepo.getOne(qid);
			List<Question> q = new LinkedList<Question>(Arrays.asList(question));
			test.setQuestion(q);
			testRepo.save(test);
			LOGGER.info("Saving the test..END");
			return test;
		}
	}

	/***
	 * Delete the test by Id
	 * 
	 * @param id
	 * @return test
	 * @throws TestException
	 */
	public Test removeTest(final int id) throws TestException {
		LOGGER.info("Inside Service Layer for removing Test By Id...START");
		 Test test = testRepo.findById(id).orElse(null);
		if (test  == null) {
			LOGGER.error("TestException occured...END");
			throw new TestException("Test cannot be found!");
		} else {
			LOGGER.info("Removed Test Sucessfully..END");
			test = testRepo.getOne(id);
			testRepo.delete(test);
			LOGGER.info("Deleted Test...END");
			return test;
		}
	}

}
