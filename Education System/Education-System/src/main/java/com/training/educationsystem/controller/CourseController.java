package com.training.educationsystem.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.AlreadyExistsException;
import com.training.educationsystem.exceptions.CourseNotFoundException;
import com.training.educationsystem.exceptions.ErrorMessages;
import com.training.educationsystem.exceptions.InvalidCourseException;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.NotFoundException;
import com.training.educationsystem.exceptions.TestNotFoundException;
import com.training.educationsystem.services.ICourseService;

/**
 * This is the controller for Course module.
 * 
 * @author Afeeda A.H.
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/educationsystem/course")
public class CourseController {
	/**
	 * Initializing Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	transient private ICourseService service;

	/**
	 * This method adds the course in the System
	 * 
	 * @param course
	 * @return Course 
	 * @throws InvalidCourseException
	 */
	@PostMapping("/add-course")
	public Course addCourse(@RequestBody final Course course) throws InvalidCourseException {
		LOGGER.info("Add Course (Controller) - START");
		final String coursePattern = "^[a-zA-Z0-9_+-]*$";
		if (course.getCourseName() == "") {
			LOGGER.error("Course name cannot be Empty!");
			throw new InvalidCourseException("Course name cannot be Empty!");
		} else if (course.getHours() < 0 || course.getHours() > 10) {
			LOGGER.error("Course duration must be greater than 0 hrs and less than 10 hrs!");
			throw new InvalidCourseException("Course duration must be greater than 0 hrs and less than 10 hrs!");
		} else if (!(Pattern.matches(coursePattern, course.getCourseName()))) {
			LOGGER.error("Course name can only contain alphanumeric characters!");
			throw new InvalidCourseException("Course name can only contain alphanumeric characters!");
		} else {
			LOGGER.info("Adding Course");
			Course addedCourse = service.addCourse(course);
			LOGGER.info("Add Course (Controller) - END");
			return addedCourse;
		}
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCourseException.class)
	public ErrorMessages exceptionHandler(final InvalidCourseException invalidEx) {
		return new ErrorMessages("400", invalidEx.str);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorMessages exceptionHandler(final NotFoundException notFoundEx) {
		return new ErrorMessages("404", notFoundEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ListEmptyException.class)
	public ErrorMessages exceptionHandler(final ListEmptyException listEmptyEx) {
		return new ErrorMessages("404", listEmptyEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyExistsException.class)
	public ErrorMessages exceptionHandler(final AlreadyExistsException alreadyExistEx) {
		return new ErrorMessages("400", alreadyExistEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TestNotFoundException.class)
	public ErrorMessages exceptionHandler(final TestNotFoundException testNotFoundEx) {
		return new ErrorMessages("404", testNotFoundEx.message);
	}

	/**
	 * This method displays individual courses
	 * 
	 * @param courseId
	 * @return Course 
	 * @throws NotFoundException
	 */
	@GetMapping("/view-course/{courseId}")
	public Course viewCourse(@PathVariable("courseId") final int courseId) throws NotFoundException {
		LOGGER.info("View Course (Controller) -START!");
		LOGGER.info("Fetching Course");
		final Course course = service.viewCourse(courseId);
		LOGGER.info("View Course  (Controller) -END!");
		return course;
	}

	/**
	 * This method deletes individual courses from the System
	 * 
	 * @param courseId
	 * @return String
	 * @throws NotFoundException 
	 * @throws ListEmptyException 
	 */
	@DeleteMapping("/delete-course/{courseId}")
	public List<Course> deleteCourse(@PathVariable("courseId") final int courseId) throws NotFoundException, ListEmptyException {
		LOGGER.info("Delete Course (Controller) - START");
		LOGGER.info("Deleting Course");
		service.deleteCourse(courseId);
		LOGGER.info("Delete Course (Controller) - END");
		return viewAllCourses();
	}

	/**
	 * This method displays list of all courses
	 * 
	 * @return List of courses
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-all-courses")
	public List<Course> viewAllCourses() throws ListEmptyException {
		LOGGER.info("View All Courses (Controller) -START!");
		LOGGER.info("Fetching  Courses");
		final List<Course> courseList = service.viewAllCourses();
		LOGGER.info("View All Courses  (Controller) -END!");
		return courseList;
	}

	/**
	 * This method displays available Trainers for individual courses
	 * 
	 * @param courseId
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-trainers/{courseId}")
	public List<Trainer> viewTrainers(@PathVariable("courseId") final int courseId) throws ListEmptyException {
		LOGGER.info("View Trainers (Controller) -START!");
		LOGGER.info("Fetching Trainers");
		final List<Trainer> trainerList = service.viewTrainers(courseId);
		LOGGER.info("View Trainers (Controller) -END!");
		return trainerList;
	}

	/**
	 * This method displays Students for individual courses
	 * 
	 * @param courseId
	 * @return List of Students
	 * @throws ListEmptyException
	 */
	@GetMapping("/views-students/{courseId}")
	public List<Student> viewStudents(@PathVariable("courseId") final int courseId) throws ListEmptyException {
		LOGGER.info("View Students (Controller) -START!");
		LOGGER.info("Fetching Students");
		final List<Student> studentList = service.viewStudents(courseId);
		LOGGER.info("View Students (Controller) -END!");
		return studentList;
	}

	/**
	 * This method displays Test for each course
	 * 
	 * @param courseId
	 * @return Test 
	 * @throws TestNotFoundException
	 */
	@GetMapping("/view-test/{courseId}")
	public Test viewTest(@PathVariable("courseId") final int courseId) throws TestNotFoundException {
		LOGGER.info("View Test (Controller) -START!");
		LOGGER.info("Fetching test");
		final Test test = service.viewTest(courseId);
		LOGGER.info("View Test  (Controller) -END!");
		return test;
	}
	@GetMapping("/view-progress/{courseId}")
	public Progress viewProgress(@PathVariable("courseId") final int courseId) throws NotFoundException {
		LOGGER.info("View Progress (Controller) -START!");
		LOGGER.info("Fetching Progress");
		final Progress progress = service.viewProgress(courseId);
		LOGGER.info("View Progress  (Controller) -END!");
		return progress;
	}

	/**
	 * This method adds Trainers in the Course specified
	 * 
	 * @param courseId
	 * @param firstName
	 * @return Course 
	 * @throws InvalidCourseException
	 * @throws NotFoundException
	 * @throws AlreadyExistsException
	 */
	@PatchMapping("/update-trainers")
	public Course updateCourseForTrainers(@RequestParam("courseId") final int courseId, @RequestParam("firstName") final String firstName)
			throws NotFoundException, InvalidCourseException, AlreadyExistsException {
		LOGGER.info("View All Courses  (Controller) -END!");
		final String namePattern = "^[a-zA-Z]+$";
		if (!(Pattern.matches(namePattern, firstName))) {
			throw new InvalidCourseException("Name input can only accept alphabets!");
		} else {
			LOGGER.info("Adding Trainer");
			final Course course = service.updateCourseForTrainers(courseId, firstName);
			LOGGER.info("Updating Course for Trainers (Controller) -END!");
			return course;
		}
	}

	/**
	 * This method adds students who have enrolled in the course
	 * 
	 * @param courseId
	 * @param userName
	 * @return Course object
	 * @throws InvalidCourseException
	 * @throws NotFoundException
	 * @throws AlreadyExistsException
	 */
	@PatchMapping("/update-students")
	public Course updateCourseForStudents(@RequestParam("courseId") final int courseId, @RequestParam("userName") final String userName)
			throws InvalidCourseException, NotFoundException, AlreadyExistsException {
		LOGGER.info("Updating Course for Students (Controller) -START!");
		final String namePattern = "^[a-zA-Z]+$";
		if (!(Pattern.matches(namePattern, userName))) {
			throw new InvalidCourseException("Name input can only accept alphabets!");
		} else {
			LOGGER.info("Adding Student");
			final Course course = service.updateCourseForStudents(courseId, userName);
			LOGGER.info("Updating Course for Students (Controller) -END!");
			return course;
		}
	}

	/**
	 * This method adds payment in the course after enrollment
	 * 
	 * @param courseId
	 * @param transactionId
	 * @return Course 
	 * @throws NotFoundException
	 */
	@PatchMapping("/update-payment")
	public Course updateCourseForPayment(@RequestParam("courseId") final int courseId, @RequestParam("transactionId") final int transactionId)
			throws NotFoundException {
		LOGGER.info("Updating Course  for Payment (Controller) -START!");
		LOGGER.info("Adding Payment");
		final Course course = service.updateCourseForPayment(courseId, transactionId);
		LOGGER.info("Updating Course for Payment (Controller) -END!");
		return course;
	}

	/**
	 * This method adds Test for each course specified
	 * 
	 * @param courseId
	 * @param testId
	 * @return Course 
	 * @throws CourseNotFoundException
	 */
	@PatchMapping("/update-test")
	public Course updateCourseForTest(@RequestParam("courseId") final int courseId, @RequestParam("testId") final int testId)
			throws NotFoundException {
		LOGGER.info("Updating Course for  Test (Controller) -START!");
		LOGGER.info("Adding Test");
		final Course course = service.updateCourseForTest(courseId, testId);
		LOGGER.info("Updating Course for Test  (Controller) -END!");
		return course;
	}

	/**
	 * This method adds Progress for each Course specified
	 * @param courseId
	 * @param progressId
	 * @return Course
	 * @throws NotFoundException
	 */
	@PatchMapping("/update-progress")
	public Course updateCourseForProgress(@RequestParam("courseId") final int courseId, @RequestParam("progressId") final int progressId)
			throws NotFoundException {
		LOGGER.info("Updating Course for Progress (Controller) -START!");
		LOGGER.info("Adding Progress");

		final Course course = service.updateCourseForProgress(courseId, progressId);
		LOGGER.info("Updating Course for Progress (Controller) -END!");
		return course;
	}
}
