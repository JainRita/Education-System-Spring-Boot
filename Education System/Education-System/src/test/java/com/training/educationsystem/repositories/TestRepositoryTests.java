package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Test;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class TestRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TestRepository testRepo;
	
	@org.junit.jupiter.api.Test
	public Test getTest() {
		Test test = new Test();
		test.setTestName("OOP's Concepts");
		test.setNumberOfAttempts(3);
		test.setScore(10);
		return test;
	}
	
	@org.junit.jupiter.api.Test
	public void testSaveTest() {
		Test test = getTest();
		Test savedTest=entityManager.persist(test);
		Test getFromDb=testRepo.getOne(savedTest.getTestId());
		assertThat(getFromDb).isEqualTo(savedTest);
	}
	
	@org.junit.jupiter.api.Test
	public void getAllTests(){
		List<Test> testList=testRepo.findAll();
		assertThat(testList).size().isGreaterThan(0);
	}
	
	@org.junit.jupiter.api.Test
	public void updateTests() {
		Test savedTest=testRepo.getOne(6);
		savedTest.setTestName("SQL Basics");
		Test updateTest=testRepo.save(savedTest);
		assertThat(updateTest).isNotEqualTo(savedTest);	
	}
	
	@org.junit.jupiter.api.Test
	public void testDeleteTest() {
		Test test = new Test();
		test.setTestName("Spring Boot");
		test.setScore(20);
		test.setNumberOfAttempts(2);
		Test t1 = testRepo.save(test);
		testRepo.deleteById(t1.getTestId());
		assertFalse(testRepo.existsById(8));
		
	}
	
	@org.junit.jupiter.api.Test
	public void testFindAllTest() {
		Test test = new Test();
		test.setTestName("Spring API");
		test.setScore(10);
		test.setNumberOfAttempts(2);
		testRepo.save(test);
		assertNotNull(testRepo.findAll());
	}
}
