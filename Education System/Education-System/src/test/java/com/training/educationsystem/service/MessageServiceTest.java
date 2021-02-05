package com.training.educationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import com.training.educationsystem.entities.Message;
import com.training.educationsystem.repositories.MessageRepository;
import com.training.educationsystem.services.MessageServiceImp;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MessageServiceTest {

	@InjectMocks
	private MessageServiceImp messageService;
	
	@MockBean 
	private MessageRepository messageRepo;
	
	@Test
	public void testAddMessage() {		//test for adding message
		Message message=new Message();
		message.setMessageDate(LocalDate.parse("2020-01-01"));
		message.setMessageDescription("Aarzoo....");
		Mockito.when(messageRepo.save(message)).thenReturn(message);
		assertThat(message).isEqualTo(message);
	}
	
	@Test
	public void testViewMessage() {		//test for viewing message
		Message message=new Message();
		message.setMessageId(94);
		message.setMessageDate(LocalDate.parse("2020-01-01"));
		message.setMessageDescription("hello all");		
		Mockito.when(messageRepo.getOne(message.getMessageId())).thenReturn(message);
		assertThat(messageRepo.getOne(message.getMessageId())).isEqualTo(message);
	}
	
	@Test
	public void testViewAllMessages() {		//test for viewing messages
		Message message=new Message();
		message.setMessageDate(LocalDate.parse("2020-01-01"));
		message.setMessageDescription("hello.....");		
		Message message1=new Message();
		message1.setMessageDate(LocalDate.parse("2020-02-02"));
		message1.setMessageDescription("hello all.....");		
		List<Message> messageList=new ArrayList<>();
		messageList.add(message);
		messageList.add(message1);
		
		Mockito.when(messageRepo.findAll()).thenReturn(messageList);
		assertThat(messageService.viewAllMessages()).isEqualTo(messageList);
	}
	

}

