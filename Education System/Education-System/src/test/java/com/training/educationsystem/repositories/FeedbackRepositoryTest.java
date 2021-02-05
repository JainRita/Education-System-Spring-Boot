package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Feedback;
import com.training.educationsystem.repositories.FeedbackRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class FeedbackRepositoryTest {

	
	@Autowired
    private FeedbackRepository feedbackRepository;
	
	
	public Feedback getFeedback() {
		Feedback feedback=new Feedback();
		feedback.setSname("praju");
		feedback.setFeedback("good");
		return feedback;
		
	}

	
	  @Test		  
	  @Rollback(false)		  
	  @Order(1)
	  public void testAddFeedback() { 
      Feedback f=getFeedback();
	  Feedback actual=feedbackRepository.save(f); 
	  Feedback expected=feedbackRepository.getOne(actual.getId());
	  assertNotNull(actual); 
	  assertThat(actual).isEqualTo(expected); } //
	 		
	@Test
	@Order(2)
	public void testViewMessage()
	{
		Feedback m=getFeedback();
		Feedback expected=feedbackRepository.save(m);
		Feedback actual=feedbackRepository.getOne(expected.getId());
		assertNotNull(feedbackRepository.getOne(expected.getId()));
		assertThat(actual.getId()).isEqualTo(expected.getId());
	}	
	@Test
	@Order(3)
	public void testViewAllFeedback()
	{
		List<Feedback> feedbackList=new ArrayList<>();
		feedbackList=feedbackRepository.findAll();
		assertNotNull(feedbackList);
		assertThat(feedbackList).size().isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void testUpdateFeedback()
	{
		//Feedback f1=getFeedback();
		Feedback savedFeedback=feedbackRepository.getOne(6);
		savedFeedback.setReply("hello");
		Feedback updatedFeedback=feedbackRepository.save(savedFeedback);
		assertEquals("hello",updatedFeedback.getReply());
		//assertThat(updatedFeedback.getReply()).isEqualTo("hello");
		//assertThat(updatedFeedback).isEqualTo(savedFeedback);	
		
	}


}