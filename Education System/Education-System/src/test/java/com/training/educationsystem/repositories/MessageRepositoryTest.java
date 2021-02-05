package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Message;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
class MessageRepositoryTest {
	
	@Autowired
    private MessageRepository messageRepository;
	
	
	public Message viewMessage() {
		Message message=new Message();
		message.setMessageDate(LocalDate.parse("2020-03-03"));
		message.setMessageDescription("Aarzoo");
		return message;
		
	}
	
	
	@Test
	@Rollback(false)
	public void testAddMessage() {	//test for adding message
		Message m=viewMessage();
		Message actual=messageRepository.save(m);
		Message expected=messageRepository.getOne(actual.getMessageId());
		assertNotNull(actual);
		assertThat(actual).isEqualTo(expected);
	}
	
	
	@Test
	public void testViewMessage()	//test for viewing message
	{
		Message m=viewMessage();
		Message expected=messageRepository.save(m);
		Message actual=messageRepository.getOne(expected.getMessageId());
		assertNotNull(messageRepository.getOne(expected.getMessageId()));
		assertThat(actual.getMessageId()).isEqualTo(expected.getMessageId());
		assertThat(actual.getMessageDate()).isEqualTo(expected.getMessageDate());
		assertThat(actual.getMessageDescription()).isEqualTo(expected.getMessageDescription());
	}
	
	@Test
	public void testViewAllMessage()	//test for viewing all messages
	{
		List<Message> messageList=messageRepository.findAll();
		assertNotNull(messageList);
		assertThat(messageList).size().isGreaterThan(0);
	}

}
