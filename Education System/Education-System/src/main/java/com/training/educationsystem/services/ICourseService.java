package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.AlreadyExistsException;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.NotFoundException;
import com.training.educationsystem.exceptions.TestNotFoundException;

/**
 * 
 * @author Afeeda A.H
 *
 */
public interface ICourseService {

	Course addCourse(Course course);

	void deleteCourse(int courseId) throws NotFoundException;

	Course viewCourse(int courseId) throws NotFoundException;

	List<Course> viewAllCourses() throws ListEmptyException;

	Course updateCourseForTrainers(int courseId, String firstName) throws NotFoundException, AlreadyExistsException;

	Course updateCourseForStudents(int courseId, String userName) throws NotFoundException, AlreadyExistsException;

	Course updateCourseForPayment(int courseId, int transactionId) throws NotFoundException;

	Course updateCourseForTest(int courseId, int testId) throws NotFoundException;

	Course updateCourseForProgress(int courseId, int progressId) throws NotFoundException;

	List<Trainer> viewTrainers(int courseId) throws ListEmptyException;

	List<Student> viewStudents(int courseId) throws ListEmptyException;

	Test viewTest(int courseIdd) throws TestNotFoundException;
	
	Progress viewProgress(int courseId) throws NotFoundException;
}
