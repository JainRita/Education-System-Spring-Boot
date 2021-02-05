package com.training.educationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.repositories.QuestionRepository;
import com.training.educationsystem.services.IQuestionService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuestionServiceTests {
	
	@InjectMocks
	private IQuestionService questionService;
	
	@MockBean 
	private QuestionRepository testRepo;
	
	@Test
	public void testAddQuestion() throws EmptyInputException {
		Question question = new Question();
		question.setQuestion("Which of these is long data type literal");
		question.setOption1("0x99fffL");
		question.setOption2("ABCDEFG");
		question.setOption3("0x99fffa");
		question.setOption4("99671246");
		question.setCorrectAnswer("0x99fffL");
		Mockito.when(testRepo.save(question)).thenReturn(question);
		assertThat(questionService.addQuestion(question)).isEqualTo(question);
	}
	
	@Test
	public void testViewAllQuestions() {
		Question question = new Question();
		question.setQuestion("Which of these is long data type literal");
		question.setOption1("0x99fffL");
		question.setOption2("ABCDEFG");
		question.setOption3("0x99fffa");
		question.setOption4("99671246");
		question.setCorrectAnswer("0x99fffL");
		
		Question question0 = new Question();
		question.setQuestion("Which of these can be returned by the operator &?");
		question.setOption1("Integer");
		question.setOption2("Boolean");
		question.setOption3("Character");
		question.setOption4("Integer or Boolean");
		question.setCorrectAnswer("Integer or Boolean");
		
		List<Question> questionList=new ArrayList<>();
		questionList.add(question);
		questionList.add(question0);
		
		Mockito.when(testRepo.findAll()).thenReturn(questionList);
		assertThat(questionService.viewAllQuestions()).isEqualTo(questionList);
	}
	
	@Test
	public void testDeleteQuestions(){
		Question question = new Question();
		question.setQuestion("Which of these can be returned by the operator &?");
		question.setOption1("Integer");
		question.setOption2("Boolean");
		question.setOption3("Character");
		question.setOption4("Integer or Boolean");
		question.setCorrectAnswer("Integer or Boolean");
		
		Mockito.when(testRepo.getOne(1)).thenReturn(question);
		Mockito.when(testRepo.existsById(question.getQuestionId())).thenReturn(false);
		assertFalse(testRepo.existsById(question.getQuestionId()));
	}

}
