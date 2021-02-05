package com.training.educationsystem.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.training.educationsystem.services.StudentServiceImpl;
/**
 * 
 * @author aniket
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/educationsystem")
public class StudentController {
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	transient private StudentServiceImpl studentService;

	/**
	 * 
	 * @param Student Object
	 * This controller function method takes Student object
	 * @return ResponseEntity object about whether the registration request has been
	 *         made or not
	 * @throws EmailAlreadyExistsException
	 * @throws UserNameExistException
	 */

	@PostMapping("/request-registration")
	public ResponseEntity<StudentDTO> makeRegistration(@Valid @RequestBody final Student student)
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException
	{
		LOGGER.info(
				"This is inside the makeRegistration method of the controller which will let the student to make registration request in education system application- START");
		final boolean isRequestMade = studentService.requestRegistration(student);
		
		if (isRequestMade) {
				
			LOGGER.info("End of makeRegistration method- END");
			StudentDTO studentDto = new StudentDTO();
			studentDto.setFirstName(student.getFirstName());
			studentDto.setMiddleName(student.getMiddleName());
			studentDto.setLastName(student.getLastName());
			studentDto.setContactNumber(student.getContactNumber());
			studentDto.setUserName(student.getUserName());
			studentDto.setEmailId(student.getEmailId());
			return new ResponseEntity<StudentDTO>(studentDto,HttpStatus.OK);
			
			
		} 
		else 
		{
			LOGGER.info("End of makeRegistration method - END");
			return new ResponseEntity("Registration request cannot be made",HttpStatus.OK);
			
			
		}

	}

	/**
	 * This controller method will fetch all the students from the database whose
	 * registration request has not been approved yet
	 * 
	 * @return List of Student entity
	 */

	@GetMapping("/get-all-students")
	public ResponseEntity<List<Student>> getAllRegistrationRequest() 
	{
		LOGGER.info("Inside the getAllRegistrationRequest method of student controller - START");
		LOGGER.info(
				"This method will let the admin to see which student had made an request to registration in education system");
		final List<Student> listOfStudent = studentService.getAllStudentsRegistrationRequest();
		
		LOGGER.info("End of getAllRegistrationRequest controller method - END");
		return new ResponseEntity<List<Student>>(listOfStudent, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @return Response Entity object of string about the status of student
	 *         registration request approval
	 * @throws EntityNotFoundException
	 */

//	@PatchMapping("/approve-student-request/{id}")
//	public ResponseEntity<String> approveStudentRequest(@PathVariable("id") final int studentId) throws EntityNotFoundException 
//	{
//		LOGGER.info("Inside the approveStudentRequest method - START");
//		final boolean approveStudentRequest = studentService.getStudentByIdForValidatingRegistration(studentId);
//		
//		if (approveStudentRequest)
//		{
//			LOGGER.info("Student is valid user controller method - END");
//			return new ResponseEntity<String>("Student with Id: " + studentId + " is validated!", HttpStatus.OK);
//			
//		} 
//		else
//		{
//			LOGGER.info("Student is already validated controller method - END");
//			return new ResponseEntity<String>("Student is already validated!", HttpStatus.OK);
//			
//		}
//	}
	
	@PatchMapping("/approve-student-request/{id}")
	public ResponseEntity<List<Student>> approveStudentRequest(@PathVariable("id") final int studentId) throws EntityNotFoundException 
	{
		LOGGER.info("Inside the approveStudentRequest method - START");
		final boolean approveStudentRequest = studentService.getStudentByIdForValidatingRegistration(studentId);
		
		if (approveStudentRequest)
		{
			LOGGER.info("Student is valid user controller method - END");
			
			return getAllRegistrationRequest();
			
		} 
		else
		{
			LOGGER.info("Student is already validated controller method - END");
			return new ResponseEntity("Student is already validated!", HttpStatus.OK);
			
		}
	}
	
	

	/**
	 * 
	 * @param username
	 * @param password
	 * @return Response Entity object of string about whether the student is allowed
	 *         to login or not
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping("/student-login")
	public ResponseEntity<Student> studentLogin(@RequestParam("username") final String username,
			@RequestParam("password") final String password)
			throws StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		LOGGER.info("Inside the studentLogin method - START");
		final Student validateLogin = studentService.validateStudentLogin(username, password);
		System.out.println(validateLogin);
		if (validateLogin != null)
		{
			LOGGER.info("Login is successsful controller method... - END");
			
			return new ResponseEntity<Student>(validateLogin,HttpStatus.OK);
			
		} 
		else
		{
			LOGGER.info("Login Failed controller method - END");
			
			return new ResponseEntity("Invalid username and password!",HttpStatus.OK);
			
			
		}

	}

	/**
	 * 
	 * @return List of students which are registered in the education system
	 */
	
	@GetMapping("/view-all-students")
	public ResponseEntity<List<Student>> viewAllStudentDetails()
	{
		LOGGER.info("Inside the viewAllStudentDetails method - START");
		final List<Student> listofStudent = studentService.viewAllStudentDetails();
		
		LOGGER.info("Returning the Student Entity controller method - END");
		return new ResponseEntity<List<Student>>(listofStudent, HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @param id
	 * @return StudentDTO object of a single student
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping("/view-single-validated-student/{id}")
	public ResponseEntity<StudentDTO> getIndividualValidatedStudentDetails(@PathVariable("id") final int studentId)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		LOGGER.info(
				"This is inside the view single validated method which will get the individual student details who have validated - START");
		Student student = studentService.viewStudentById(studentId);
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(student.getStudentId());
		studentDTO.setFirstName(student.getFirstName());
		studentDTO.setMiddleName(student.getMiddleName());
		studentDTO.setLastName(student.getLastName());
		studentDTO.setEmailId(student.getEmailId());
		studentDTO.setUserName(student.getUserName());
		studentDTO.setContactNumber(student.getContactNumber());
		
		LOGGER.info("End of view validated method and returning the student object controller method - END");
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @param studentDTO
	 * @return Updated Student object in response
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@PatchMapping("/update-student-details/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable("id") final int studentId, @RequestBody final StudentDTO studentDTO)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{

		LOGGER.info("Inside the updateStudentDetails method - START");
		final Student student = studentService.updateStudentDetails(studentId, studentDTO);
		
		LOGGER.info("Returning the Student response Entity from updateStudentDetails controller method - END");
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @return List of Course object of course enrolled by student
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping("/get-courses-enrolled/{id}")
	public List<Course> getCourseEnrolledByStudent(@PathVariable("id") final int studentId)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException
	{
		LOGGER.info("Inside the getCourseEnrolledByStudent method - START");
		final List<Course> listOfCourses = studentService.viewCourseForStudent(studentId);
		
		LOGGER.info("Returning the list of courses from getCoursesEnrolledByStudent method controller method- END");
		return listOfCourses;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @return List of Course object when student course is updated
	 * @throws RegistrationRequestNotApprovedException
	 * @throws StudentNotFoundException
	 * @throws EntityNotFoundException
	 * @throws AlreadyEnrolledInCourseException
	 */

	@PatchMapping("/update-student-course")
	public List<Course> updateStudentCourse(@RequestParam("studentId") final int studentId, @RequestParam("courseName") final String name)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException,
			CourseNotFoundException, AlreadyEnrolledInCourseException
	{
		LOGGER.info("Inside the updateStudentCourse method - START");
		final Student updateCourse = studentService.updateStudentForCourse(studentId, name);
		
		LOGGER.info("Returning the list of courses from updateStudentCourse controller method - END");
		return updateCourse.getCourses();
		
	}

}
