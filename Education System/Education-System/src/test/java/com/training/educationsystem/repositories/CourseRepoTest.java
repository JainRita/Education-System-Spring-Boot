package com.training.educationsystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Course;
import com.training.educationsystem.repositories.CourseRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
class CourseRepoTest {
  
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void testSaveCourse() {
		Course course=new Course();
		course.setCourseId(6);
		course.setCourseName("Maven");
		course.setHours(9);
		
		courseRepository.save(course);
		
		assertNotNull(course);
		
	}
	
	@Test
	public void testGetCourse() {
		Course course=new Course();
		course.setCourseId(8);
		course.setCourseName("Spring");
		course.setHours(11);
		
		Course c=courseRepository.save(course);
		
		assertNotNull(c);
		assertEquals(c.getCourseName(),course.getCourseName());
		assertEquals(c.getHours(),course.getHours());
		
	}
	
	@Test
	public void testDeleteCourse() {
		Course course=new Course();
		course.setCourseId(8);
		course.setCourseName("Spring");
		course.setHours(11);
		
		Course c=courseRepository.save(course);
		courseRepository.deleteById(c.getCourseId());
		
		assertFalse(courseRepository.existsById(8));
		
	}
	
	@Test
	public void testFindAllCourses() {
		Course course=new Course();
		course.setCourseId(8);
		course.setCourseName("Spring");
		course.setHours(11);
		
		courseRepository.save(course);
		assertNotNull(courseRepository.findAll());
	}
}
