package com.training.educationsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.controller.CourseController;
import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Payment;
import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.AlreadyExistsException;
import com.training.educationsystem.exceptions.CourseNotFoundException;
import com.training.educationsystem.exceptions.InvalidCourseException;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.NotFoundException;
import com.training.educationsystem.exceptions.TestNotFoundException;
import com.training.educationsystem.repositories.CourseRepository;
import com.training.educationsystem.repositories.PaymentRepository;
import com.training.educationsystem.repositories.ProgressRepository;
import com.training.educationsystem.repositories.StudentRepository;
import com.training.educationsystem.repositories.TestRepository;
import com.training.educationsystem.repositories.TrainerRepository;

/**
 * This is Service class for Course module
 * 
 * @author Afeeda A.H
 *
 */
@Transactional
@Service
public class CourseService implements ICourseService {
	/**
	 * Initializing Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
	
	/**
	 * Initializing CourseRepository
	 */
	@Autowired
	transient private CourseRepository courseRepo;
	
	/**
	 * Initializing TrainerRepository
	 */
	@Autowired
	transient private TrainerRepository trainerRepo;

	/**
	 * Initializing StudentRepository
	 */
	@Autowired
	transient private StudentRepository studentRepo;

	/**
	 * Initializing PaymentRepository
	 */
	@Autowired
	transient private PaymentRepository paymentRepo;

	/**
	 * Initializing TestRepository
	 */
	@Autowired
	transient private TestRepository testRepo;

	/**
	 * Initializing ProgressRepository
	 */
	@Autowired
	transient private ProgressRepository progressRepo;

	/**
	 * This method adds the course in the System
	 * 
	 * @param cname
	 * @param hours
	 * @return Course object
	 * @throws InvalidCourseException
	 */
	@Override
	public Course addCourse(final Course course) {
		LOGGER.info("Add Course (Service) - START");
		courseRepo.save(course);
		LOGGER.info("Course Added Successfully!");
		LOGGER.info("Add Course (Service) - END");
		return course;
	}

	/**
	 * This method deletes individual courses from the System
	 * 
	 * @param courseId
	 * @return Nothing
	 * @throws NotFoundException 
	 */
	@Override
	public void deleteCourse(final int courseId) throws NotFoundException {
		LOGGER.info("Delete Course (Service) - START");
		if(courseRepo.existsById(courseId)){
		courseRepo.deleteById(courseId);
		LOGGER.info("Course Deleted Successfully!");
		LOGGER.info("Delete Course (Service) - END");}
		else
		{
			LOGGER.error("Course cannot be deleted as this Course cannot be found");
			throw new NotFoundException("Course cannot be deleted as this Course cannot be found");
		}
	}

	/**
	 * This method displays individual courses
	 * 
	 * @param courseId
	 * @return Course 
	 * @throws NotFoundException
	 */
	@Override
	public Course viewCourse(final int courseId) throws NotFoundException {
		LOGGER.info("View Course (Service) -START!");
		final Course course = courseRepo.findById(courseId).orElse(null);
		if (course == null) {
			LOGGER.error("Course cannot be Found!");
			throw new NotFoundException("Course cannot be Found!");
		} else {
			LOGGER.info("Displaying Course!");
			LOGGER.info("View Course  (Service) -END!");
			return course;
		}

	}

	/**
	 * This method displays list of all courses
	 * 
	 * @return List of courses
	 * @throws ListEmptyException
	 */
	@Override
	public List<Course> viewAllCourses() throws ListEmptyException {
		LOGGER.info("View All Courses (Service) -START!");
		final List<Course> courseList = courseRepo.findAll();
		if (courseList.size() > 0) {
			LOGGER.info("Displaying Courses!");
			LOGGER.info("View All Courses  (Service) -END!");
			return courseList;
		} else {
			LOGGER.error("No Courses to Display");
			throw new ListEmptyException("No Courses to Display");
		}
	}

	/**
	 * This method adds Trainers in the Course specified
	 * @param courseId
	 * @param firstName
	 * @return Course 
	 * @throws NotFoundException
	 * @throws AlreadyExistsException
	 */
	@Override
	public Course updateCourseForTrainers(final int courseId, final String firstName)
			throws NotFoundException, AlreadyExistsException {
		LOGGER.info("Updating Course for Trainers (Service) -START!");
		final Course course = courseRepo.findById(courseId).orElse(null);
		if (course != null) {
			final List<Trainer> trainerList = course.getTrainers();
			final List<String> nameList = trainerList.stream().map(e -> e.getFirstName()).collect(Collectors.toList());
			if (nameList.contains(firstName)) {
				LOGGER.warn("Trainer Already assigned in Course ");
				throw new AlreadyExistsException("Trainer Already assigned in Course ");
			} else {
				final List<Trainer> tlist = new ArrayList<>();
				final Trainer trainer = trainerRepo.findByFirstName(firstName);

				tlist.add(trainer);
				course.setTrainers(tlist);
				courseRepo.save(course);
				LOGGER.info("Adding Trainer!");
				LOGGER.info("Updating Course for Trainers (Service) -END!");
				return course;
			}
		} else {
			LOGGER.error("Trainer cannot be added!");
			throw new NotFoundException("Trainer cannot be added!");
		}

	}

	/**
	 * This method adds students who have enrolled in the course
	 * @param courseId
	 * @param userName
	 * @return Course object
	 * @throws NotFoundException
	 * @throws AlreadyExistsException
	 */
	@Override
	public Course updateCourseForStudents(final int courseId, final String userName)
			throws NotFoundException, AlreadyExistsException {
		LOGGER.info("Updating Course for Students (Service) -START!");
		final Course course = courseRepo.findById(courseId).orElse(null);
		if (course != null) {
			final List<Student> studentList = course.getStudents();

			final List<String> usernameList = studentList.stream().map(e -> e.getUserName()).collect(Collectors.toList());
			if (usernameList.contains(userName)) {
				LOGGER.warn("Student Already Enrolled in Course ");
				throw new AlreadyExistsException("Student Already Enrolled in Course ");
			} else {
				final List<Student> slist = new ArrayList<Student>();
				final Student student = studentRepo.findByUserName(userName);

				slist.add(student);
				course.setStudents(slist);
				courseRepo.save(course);
				LOGGER.info("Adding Student!");
				LOGGER.info("Updating Course for Students (Service) -END!");
				return course;
			}
		} else {
			LOGGER.error("Student cannot be added");
			throw new NotFoundException("Student cannot be added");
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

	@Override
	public Course updateCourseForPayment(final int courseId, final int transactionId) throws NotFoundException {
		LOGGER.info("Updating Course  for Payment (Service) -START!");
		final Payment payment = paymentRepo.getOne(transactionId);
		final Course course = courseRepo.getOne(courseId);
		if (course == null) {
			LOGGER.error("Payment cannot be added");
			throw new NotFoundException("Payment cannot be added");
		} else {
			course.setPayment(payment);
			courseRepo.save(course);
			LOGGER.info("Adding Payment!");
			LOGGER.info("Updating Course for Payment (Service) -END!");
			return course;
		}

	}
	/**
	 * This method displays available Trainers for individual courses
	 * 
	 * @param courseId
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@Override
	public List<Trainer> viewTrainers(final int courseId) throws ListEmptyException {
		LOGGER.info("View Trainers (Service) -START!");
		final Course course = courseRepo.getOne(courseId);
		final List<Trainer> trainerList = course.getTrainers();
		if (trainerList.size() > 0) {
			LOGGER.info("Displaying Trainers!");
			LOGGER.info("View Trainers (Service) -END!");
			return trainerList;
		} else {
			LOGGER.error("No Trainers to show !");
			throw new ListEmptyException("No Trainers to show!");
		}

	}

	/**
	 * This method displays Students for individual courses
	 * 
	 * @param courseId
	 * @return List of Students
	 * @throws ListEmptyException
	 */

	@Override
	public List<Student> viewStudents(final int courseId) throws ListEmptyException {
		LOGGER.info("View Students (Service) -START!");
		final Course course = courseRepo.getOne(courseId);
		final List<Student> students = course.getStudents();
		if (students.size() > 0) {
			LOGGER.info("Displaying Studnets!");
			LOGGER.info("View Students (Service) -END!");
			return students;
		} else {
			LOGGER.error("No students to show!");
			throw new ListEmptyException("No students to show!");
		}

	}

	/**
	 * This method adds Test for each course specified
	 * 
	 * @param courseId
	 * @param testId
	 * @return Course 
	 * @throws CourseNotFoundException
	 */
	@Override
	public Course updateCourseForTest(final int courseId,final int testId) throws NotFoundException {
		LOGGER.info("Updating Course for  Test (Service) -START!");
		final Test test = testRepo.getOne(testId);
		final Course course = courseRepo.getOne(courseId);
		if (course != null) {
			course.setTest(test);
			courseRepo.save(course);
			LOGGER.info("Adding Test!");
			LOGGER.info("Updating Course for Test  (Service) -END!");
			return course;
		} else {
			LOGGER.error("Test cannot be added");
			throw new NotFoundException("Test cannot be added");
		}

	}

	/**
	 * This method displays Test for each course
	 * 
	 * @param courseId
	 * @return Test 
	 * @throws TestNotFoundException
	 */
	@Override
	public Test viewTest(final int courseId) throws TestNotFoundException {
		LOGGER.info("View Test (Service) -START!");
		final Course course = courseRepo.getOne(courseId);
		if (course == null) {
			LOGGER.error("Test cannot be found!");
			throw new TestNotFoundException("Test cannot be found!");
		} else {
			LOGGER.info("Displaying Test!");
			LOGGER.info("View Test  (Service) -END!");
			return course.getTest();
		}

	}

	/**
	 * This method adds Progress for each Course specified
	 * @param courseId
	 * @param progressId
	 * @return Course
	 * @throws CourseNotFoundException
	 */
	@Override
	public Course updateCourseForProgress(final int courseId, int progressId) throws NotFoundException {
		LOGGER.info("Updating Course for Progress (Service) -START!");
	    final  Progress progress = progressRepo.getOne(progressId);
		final Course course = courseRepo.getOne(courseId);
		if (course == null) {
			LOGGER.error("Progress cannot be added");
			throw new NotFoundException("Progress cannot be added");
		} else {
			course.setProgress(progress);
			courseRepo.save(course);
			LOGGER.info("Adding Progress!");
			LOGGER.info("Updating Course for Progress (Service) -END!");
			return course;
		}
	}

	@Override
	public Progress viewProgress(int courseId) throws NotFoundException {
		LOGGER.info("View Progress (Service) -START!");
		final Course course = courseRepo.getOne(courseId);
		if (course == null) {
			LOGGER.error("Course cannot be found!");
			throw new NotFoundException("Course cannot be found!");
		} else {
			LOGGER.info("Displaying Progress!");
			LOGGER.info("View   (Service) -END!");
			return course.getProgress();
		}

		
	}

}
