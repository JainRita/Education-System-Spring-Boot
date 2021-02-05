package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Question;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest

public class QuestionRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private QuestionRepository questionRepo;

	public Question getQuestion() {
		Question question = new Question();
		question.setQuestion("Which of these is long data type literal");
		question.setOption1("0x99fffL");
		question.setOption2("ABCDEFG");
		question.setOption3("0x99fffa");
		question.setOption4("99671246");
		question.setCorrectAnswer("0x99fffL");
		return question;
	}

	@Test
	public void testSaveQuestion() {
		Question question = getQuestion();
		Question savedquestion = entityManager.persist(question);
		Question getFromDb = questionRepo.getOne(savedquestion.getQuestionId());
		assertThat(getFromDb).isEqualTo(savedquestion);
	}

	@Test

	public void getAllQuestions() {
		List<Question> questionList = questionRepo.findAll();
		assertThat(questionList).size().isGreaterThan(0);
	}

	@Test

	public void updateQuestions() {
		Question savedquestion = questionRepo.getOne(5);
		savedquestion.setOption3("0x98ffa");
		Question updateQuestion = questionRepo.save(savedquestion);
		assertThat(updateQuestion).isNotEqualTo(savedquestion);
	}

	@Test
	public void testDeleteTest() {
		Question question = getQuestion();
		question.setQuestion("How to run java program in command prompt?");
		question.setOption1("javac filename.java");
		question.setOption2("java filename.java");
		question.setOption3("javac filename");
		question.setOption4("java filename");
		question.setCorrectAnswer("java filename");
		Question q1 = questionRepo.save(question);
		questionRepo.deleteById(q1.getQuestionId());
		assertFalse(questionRepo.existsById(12));

	}

	@Test
	public void testFindAllTest() {
		Question question = getQuestion();
		question.setQuestion("What is JRE?");
		question.setOption1("JRE is a java based GUI application.");
		question.setOption2("JRE is an implementation of the Java Virtual Machine which executes Java programs.");
		question.setOption3("JRE is an application development framework.");
		question.setOption4("None of the Above");
		question.setCorrectAnswer("JRE is an implementation of the Java Virtual Machine which executes Java programs.");
		questionRepo.save(question);
		assertNotNull(questionRepo.findAll());
	}
}
