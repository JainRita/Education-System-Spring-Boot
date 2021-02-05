package com.training.educationsystem.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.dto.StudentDTO;
import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void setupTest() {
	}

	@Test
	public void testRequestRegistration()
	{
		Student student = new Student();

		student.setFirstName("Aniketk");
		student.setMiddleName("keshavk");
		student.setLastName("karmakark");
		student.setEmailId("aniketk@gmail.com");
		student.setUserName("Aniket");
		student.setContactNumber("8983612958");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");

		studentRepository.save(student);
		assertNotNull(student);

	}

	@Test
	public void testGetAllStudentsRegistrationRequest() {

		List<Student> student = studentRepository.findByIsValidateFalse();
		assertNotNull(student);

	}

	@Test
	public void testGetStudentByIdForValidatingRegistration()
	{
		Student student = new Student();
		
		student.setFirstName("Aniketk");
		student.setMiddleName("keshavk");
		student.setLastName("karmakark");
		student.setEmailId("aniketk@gmail.com");
		student.setUserName("Aniket");
		student.setContactNumber("8983612958");
		student.setPassword("Aniket$123");
		student.setConfirmPassword("Aniket$123");
		student.setValidate(false);

		studentRepository.save(student);
		Student student2 = studentRepository.getOne(9);
		student2.setValidate(true);
		student2.setConfirmPassword("Aniket$123");

		studentRepository.save(student2);
		assertTrue(student2.isValidate());

	}

	@Test
	public void testValidateStudentLogin()
	{
		Student student = studentRepository.findByUserName("ani");

		if (student != null)
		{
			Student student2 = studentRepository.findByUserNameAndPassword("ani", "Prashant#123");
			assertEquals("Prashant", student2.getFirstName());
			
		}
		else 
		{
			assertThrows(NullPointerException.class, () -> {
				Student student2 = studentRepository.findByUserNameAndPassword("aniket", "Prashant#123");
				assertTrue(student2.isValidate());
			});
		}

	}

	@Test
	public void testViewAllStudentDetails()
	{
		List<Student> sList = studentRepository.findByIsValidateTrue();
		assertNotNull("All Student details", sList);
		
	}

	@Test
	public void testViewStudentById()
	{
		Student studentObj = studentRepository.getOne(8);
		assertEquals("ani", studentObj.getUserName());
		
	}

	@Test
	public void testUpdateStudentDetails()
	{
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("Vinay");
		studentDTO.setMiddleName("Kumar");
		studentDTO.setMiddleName("Prajapati");
		studentDTO.setEmailId("virusking@gmail.com");
		studentDTO.setContactNumber("7325488745");

		Student student = new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setMiddleName(studentDTO.getMiddleName());
		student.setLastName(studentDTO.getMiddleName());
		student.setEmailId(studentDTO.getEmailId());
		student.setContactNumber(studentDTO.getContactNumber());
		student.setConfirmPassword(student.getConfirmPassword());
		
		Student updateStudentObj = studentRepository.save(student);
		assertEquals(studentDTO.getFirstName(), updateStudentObj.getFirstName());
		
	}

	@Test
	public void testViewCourseForStudent()
	{
		Student student = studentRepository.getOne(1);
		List<Course> listOfCourses = student.getCourses();
		
		assertEquals(5, listOfCourses.size());

		assertEquals("Java", listOfCourses.get(1).getCourseName().toString());
		
	}

	@Test
	public void updateStudentForCourse()
	{
		Student student = studentRepository.getOne(9);
		List<Course> courseList = new ArrayList<Course>();
		Course course = courseRepository.findByCourseName("Python");
		courseList.add(course);
		
		student.setConfirmPassword(student.getPassword());
		student.setCourses(courseList);
		
		studentRepository.save(student);

		assertEquals("Python", student.getCourses().get(0).getCourseName());

	}

	@Test
	public void findByCourseName()
	{
		Course course = courseRepository.findByCourseName("Java");
		assertEquals("Java", course.getCourseName());

	}

	@Test
	public void findByUserName()
	{
		Student student = studentRepository.findByUserName("ani");
		assertNotNull(student);
		
	}

	@Test
	public void findByEmailId()
	{
		Student student = studentRepository.findByEmailId("aniK@gmail.com");
		assertNull(student);
		
	}

	@Test
	public void findByIsValidateFalse()
	{

		Student student = new Student();
		
		student.setStudentId(9);
		student.setFirstName("Prashant");
		student.setMiddleName("abc");
		student.setLastName("jha");
		student.setEmailId("Prashantjha@gmail.com");
		student.setUserName("prashant");
		student.setContactNumber("8983612955");
		student.setPassword("Prashant#123");
		student.setConfirmPassword("Prashant#123");
		List<Student> stuList = studentRepository.findByIsValidateFalse();
		stuList.get(0).setConfirmPassword("Prashant#123");

		assertEquals(student.getEmailId(), stuList.get(0).getEmailId());
		
	}

	@Test
	public void findByUserNameAndPassword()
	{
		Student student = studentRepository.findByUserNameAndPassword("aniket", "Aniket$123");
		assertNotNull(student);
		
	}

	@Test
	public void findByIsValidateTrue()
	{
		List<Student> studentList = studentRepository.findByIsValidateTrue();
		assertEquals(2, studentList.size());
		
	}

}
