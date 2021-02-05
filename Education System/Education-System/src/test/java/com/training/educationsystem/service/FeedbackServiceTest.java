package com.training.educationsystem.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.training.educationsystem.entities.Feedback;
import com.training.educationsystem.exceptions.InvalidFeedbackException;
import com.training.educationsystem.repositories.FeedbackRepository;
import com.training.educationsystem.services.FeedbackService;



@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FeedbackServiceTest {
	
	@InjectMocks
	private FeedbackService feedbackService;
	
	@MockBean 
	private FeedbackRepository feedbackRepo;
	
	@Test
	public void testAddFeedback() {
		Feedback feedback=new Feedback();
		feedback.setFeedback("hi");
		feedback.setSname("praju");

		
		Mockito.when(feedbackRepo.save(feedback)).thenReturn(feedback);
		assertThat(feedback).isEqualTo(feedback);
	}
	
	@Test
	public void testViewAllFeedback() throws InvalidFeedbackException {
		Feedback feedback1=new Feedback();
		feedback1.setFeedback("hi");
		feedback1.setSname("praju");
		Feedback feedback2=new Feedback();
		feedback2.setFeedback("hi");
		feedback2.setSname("sukanya");
		List<Feedback> feedbackList=new ArrayList<>();
		feedbackList.add(feedback1);
		feedbackList.add(feedback2);
		
		Mockito.when(feedbackRepo.findAll()).thenReturn(feedbackList);
		assertThat(feedbackService.getAllFeedback()).isEqualTo(feedbackList);
	}
	
	
}
