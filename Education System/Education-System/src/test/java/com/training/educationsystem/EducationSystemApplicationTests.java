package com.training.educationsystem;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.educationsystem.controller.CourseController;
import com.training.educationsystem.controller.EducationSystemController;

@SpringBootTest
class EducationSystemApplicationTests {

	@Autowired
	CourseController controller;
	@Autowired
	EducationSystemController educationSystemController;

	@Test
	void contextLoads1() {
		Assert.assertNotNull(educationSystemController);
	}
	@Test
	void contextLoads() {
		Assert.assertNotNull(controller);
	}

}
