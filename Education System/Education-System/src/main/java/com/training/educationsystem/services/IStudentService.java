package com.training.educationsystem.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.training.educationsystem.dto.StudentDTO;
import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.exceptions.AlreadyEnrolledInCourseException;
import com.training.educationsystem.exceptions.CourseNotFoundException;
import com.training.educationsystem.exceptions.EmailAlreadyExistsException;
import com.training.educationsystem.exceptions.PasswordAndConfirmPasswordNotMatchException;
import com.training.educationsystem.exceptions.RegistrationRequestNotApprovedException;
import com.training.educationsystem.exceptions.StudentNotFoundException;
import com.training.educationsystem.exceptions.UserNameExistException;
/**
 * 
 * @author Aniket
 *
 */
public interface IStudentService
{
	 boolean requestRegistration(Student student)
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException;

	 List<Student> getAllStudentsRegistrationRequest();

	 boolean getStudentByIdForValidatingRegistration(int id) throws EntityNotFoundException;

	 Student validateStudentLogin(String userName, String password)
			throws StudentNotFoundException, RegistrationRequestNotApprovedException;

	 List<Student> viewAllStudentDetails();

	 Student viewStudentById(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException;

	 Student updateStudentDetails(int id, StudentDTO studentDTO)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException;

	 List<Course> viewCourseForStudent(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException;

	 Student updateStudentForCourse(int id, String name) throws EntityNotFoundException, StudentNotFoundException,
			RegistrationRequestNotApprovedException, CourseNotFoundException, AlreadyEnrolledInCourseException;

}
