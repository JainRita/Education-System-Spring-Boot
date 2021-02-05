package com.training.educationsystem.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Test;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.ErrorResponse;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.services.ITestService;

/***
 * Implementing Controller for Test
 * 
 * @author Rita.
 *
 */
@RestController
@RequestMapping("/api/educationsystem/test")
public class TestController {
	/**
	 * Initialzing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationSystemController.class);

	@Autowired
	transient private ITestService testService;

	/***
	 * Implementing TestException
	 * 
	 * @param e
	 * @return Error Response
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TestException.class)
	public ErrorResponse handlingException(final TestException testEx) {
		return new ErrorResponse("Test Not Found", "404");
	}

	/***
	 * Implementing EmptyInputException
	 * 
	 * @param e
	 * @return Error Response
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmptyInputException.class)
	public ErrorResponse handlingEmptyException(final EmptyInputException emptyInputEx) {
		return new ErrorResponse("Input provided are empty!", "400");
	}

	/***
	 * Add Test Request Handler
	 * 
	 * @param test
	 *            for adding test in test_table
	 * @return Response Entity
	 * @throws EmptyInputException
	 * @throws Exception
	 */
	@PostMapping("/addTest")
	public ResponseEntity<Test> addTest(@RequestBody final Test test) throws EmptyInputException {
		LOGGER.info("Add Test Method..");
		final Test addtest = testService.addTest(test);
		return new ResponseEntity<Test>(addtest, HttpStatus.OK);
	}

	/***
	 * Displaying Test By Id
	 * 
	 * @param id
	 * @return Test
	 * @throws TestException
	 */
	@GetMapping("/get-test-ById")
	public Test getTest(@RequestParam("id") final int testId) throws TestException {
		final Test getById = testService.getTestById(testId);
		LOGGER.info("Displaying Test by Id");
		return getById;
	}

	/***
	 * Displaying All Test from test_table
	 * 
	 * @return list
	 * @throws TestException
	 */
	@GetMapping("/getlist")
	public List<Test> getTestList() throws TestException {
		final List<Test> gettestlist = testService.getAllList();
		if (gettestlist == null) {
			LOGGER.error("Test Not Found");
			throw new TestException("Test Not Found");
		}
		LOGGER.info("Displaying Test list.....");
		return gettestlist;
	}

	/***
	 * Remove Test
	 * 
	 * @param id
	 * @return Test
	 * @throws TestException
	 */
	@DeleteMapping("/remove")
	public ResponseEntity<Test> removeTest(@RequestParam("id") final int testId) throws TestException {
		final Test removeTest = testService.removeTest(testId);
		LOGGER.info("Removing Test by Id");
		return new ResponseEntity<Test>(removeTest, HttpStatus.OK);
	}

	/***
	 * Adding Questions to Test
	 * 
	 * @param testId,
	 *            quesId
	 * @return Test
	 * @throws TestException
	 */
	@PatchMapping("/update-question")
	public Test updateTestForQuestion(@RequestParam("testId") final int testId,
			@RequestParam("quesId") final int quesId) throws TestException {
		final Test updateTest = testService.updateTestforQuestion(testId, quesId);
		LOGGER.info("Updating Test for Each Question...");
		return updateTest;
	}
}
