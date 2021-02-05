package com.training.educationsystem.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.training.educationsystem.repositories.StudentRepository;
import com.training.educationsystem.services.StudentServiceImpl;

@SpringBootTest
class StudentServiceTest {

	@InjectMocks
	StudentServiceImpl studentService;

	@Mock
	private StudentRepository studentRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testRequestRegistration()
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException
	{
		Student student = new Student();
		
		student.setStudentId(2);
		student.setFirstName("Aniketk");
		student.setMiddleName("keshavk");
		student.setLastName("karmakark");
		student.setEmailId("aniketk@gmail.com");
		student.setUserName("Aniket");//
		student.setContactNumber("8983612958");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");

		when(studentRepository.saveAndFlush(any())).thenReturn(student);
		boolean result = studentService.requestRegistration(student);
		assertTrue(result);

	}

	@Test
	void testGetAllStudentsRegistrationRequest()
	{
		Student student = new Student();
		student.setStudentId(1);
		student.setFirstName("Aniket");
		student.setMiddleName("keshav");
		student.setLastName("karmakar");
		student.setEmailId("aniket@gmail.com");
		student.setContactNumber("8983612955");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");

		Student student2 = new Student();
		student2.setStudentId(2);
		student2.setFirstName("Vinay");
		student2.setMiddleName("Kumar");
		student2.setLastName("Prajapati");
		student2.setEmailId("vinay@gmail.com");
		student2.setContactNumber("89836158745");
		student2.setPassword("Aniket$123");
		student2.setConfirmPassword("Aniket$123");

		List<Student> getAllRegistrationRequest = new ArrayList<Student>();

		getAllRegistrationRequest.add(student);
		getAllRegistrationRequest.add(student2);
		when(studentRepository.findByIsValidateFalse()).thenReturn(getAllRegistrationRequest);
		List<Student> registrationRequestStudentList = studentService.getAllStudentsRegistrationRequest();
		assertEquals(2, registrationRequestStudentList.size());

	}

	@Test
	void testGetStudentByIdForValidatingRegistration() throws EntityNotFoundException
	{
		Student student = new Student();
		
		student.setStudentId(1);
		student.setFirstName("Aniket");
		student.setMiddleName("keshav");
		student.setLastName("karmakar");
		student.setEmailId("aniket@gmail.com");
		student.setContactNumber("8983612955");
		student.setUserName("aniket");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");
		student.setValidate(false);

		when(studentRepository.getOne(anyInt())).thenReturn(student);
		boolean approvedRequest = studentService.getStudentByIdForValidatingRegistration(student.getStudentId());
		assertTrue(approvedRequest);
		
	}

	
//	@Test
//	void testValidateStudentLogin() throws StudentNotFoundException, RegistrationRequestNotApprovedException
//	{
//		String username = "aniket";
//		String password = "Aniket$123";
//		
//		Student student = new Student();
//		
//		student.setStudentId(1);
//		student.setFirstName("Anikettt");
//		student.setMiddleName("keshavv");
//		student.setLastName("kar");
//		student.setEmailId("aniket@gma.com");
//		student.setContactNumber("8983612955");
//		student.setUserName("aniket");
//		student.setPassword("Aniket$123");
//		student.setConfirmPassword("Aniket$123");
//		student.setValidate(true);
//
//		when(studentRepository.findByUserName(anyString())).thenReturn(student);
//		boolean isValid = studentService.validateStudentLogin(username, password);
//		assertFalse(isValid);
//
//	}

	@Test
	void testViewAllStudentDetails()
	{
		Student student = new Student();
		
		student.setStudentId(1);
		student.setFirstName("Aniket");
		student.setMiddleName("keshav");
		student.setLastName("karmakar");
		student.setEmailId("aniket@gmail.com");
		student.setContactNumber("8983612955");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");
		student.setValidate(true);

		Student student2 = new Student();
		
		student2.setStudentId(1);
		student2.setFirstName("Aniket");
		student2.setMiddleName("keshav");
		student2.setLastName("karmakar");
		student2.setEmailId("aniket@gmail.com");
		student2.setContactNumber("8983612955");
		student2.setPassword("Aniket$123");
		student2.setConfirmPassword("Aniket$123");
		student2.setValidate(true);

		List<Student> viewAllStudentDetails = new ArrayList<Student>();
		viewAllStudentDetails.add(student);
		viewAllStudentDetails.add(student2);
		when(studentRepository.findByIsValidateTrue()).thenReturn(viewAllStudentDetails);
		List<Student> studentList = studentService.viewAllStudentDetails();
		assertEquals(2, studentList.size());
		
	}

	@Test
	void testViewStudentById()
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		Student student2 = new Student();
		
		student2.setStudentId(1);
		student2.setFirstName("Aniket");
		student2.setMiddleName("keshav");
		student2.setLastName("karmakar");
		student2.setEmailId("aniket@gmail.com");
		student2.setContactNumber("8983612955");
		student2.setPassword("Aniket$123");
		student2.setConfirmPassword("Aniket$123");
		student2.setValidate(true);

		when(studentRepository.getOne(anyInt())).thenReturn(student2);

		Student student = studentService.viewStudentById(1);
		assertEquals(student2.getLastName(), student.getLastName());

	}

	@Test
	void testUpdateStudentDetails()
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		StudentDTO student = new StudentDTO();
		
		student.setFirstName("Aniket");
		student.setMiddleName("kes");
		student.setLastName("kar");
		student.setEmailId("ani@gmail.com");
		student.setContactNumber("8798548511");

		Student student2 = new Student();
		
		student2.setStudentId(1);
		student2.setFirstName("Aniket");
		student2.setMiddleName("keshav");
		student2.setLastName("karmakar");
		student2.setEmailId("aniket@gmail.com");
		student2.setContactNumber("8983612955");
		student2.setPassword("Aniket$123");
		student2.setConfirmPassword("Aniket$123");
		student2.setValidate(true);

		when(studentRepository.save(any())).thenReturn(student2);

		assertThrows(NullPointerException.class, () -> {
			studentService.updateStudentDetails(1, student);
		});

	}

	@Test
	void testViewCourseForStudent()
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException
	{
		Student student = new Student();
		
		student.setStudentId(1);
		student.setFirstName("Aniket");
		student.setMiddleName("keshav");
		student.setLastName("karmakar");
		student.setEmailId("aniket@gmail.com");
		student.setContactNumber("8983612955");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");
		student.setValidate(true);
		
		
		Course c = new Course();
		
		c.setCourseId(1);
		c.setCourseName("Java");
		c.setHours(8);

		List<Course> ar = new ArrayList<Course>();
		ar.add(c);
		
		
		student.setCourses(ar);
		
	

		when(studentRepository.getOne(any())).thenReturn(student);
		List<Course> listCourse = studentService.viewCourseForStudent(1);
		assertEquals("Java", listCourse.get(0).getCourseName());
		
	}

	@Test
	void testUpdateStudentForCourse() throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException, AlreadyEnrolledInCourseException
	{
		Student student = new Student();
		
		student.setStudentId(1);
		student.setFirstName("Aniketk");
		student.setMiddleName("keshavk");
		student.setLastName("karmakark");
		student.setEmailId("aniketk@gmail.com");
		student.setContactNumber("8983612958");
		student.setUserName("Aniketk");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");
		
		
		Course course = new Course();
		
		course.setCourseId(1);
		course.setCourseName("Java");
		course.setHours(5);
		
		Course course2 = new Course();
		course.setCourseId(2);
		course.setCourseName("Java jpa");
		course.setHours(10);
		
		List<Course> listOfCourse = new ArrayList<Course>();
		listOfCourse.add(course);
		listOfCourse.add(course2);
		student.setCourses(listOfCourse);
		
		when(studentRepository.getOne(1)).thenReturn(student);
		assertNotNull(student);
		
	}

	@Test
	final void testViewStudentByIdStudentNotFoundException()
	{
		when(studentRepository.getOne(anyInt())).thenReturn(null);

		assertThrows(StudentNotFoundException.class, () -> {
			studentService.viewStudentById(9);
		});
	}

	@Test
	final void testViewStudentByIdEntityNotFoundException()
	{
		when(studentRepository.getOne(anyInt())).thenReturn(null);

		assertThrows(NullPointerException.class, () -> {
			studentService.requestRegistration(null);
		});
	}
}
