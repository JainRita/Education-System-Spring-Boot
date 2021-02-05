package com.training.educationsystem.services;

import java.time.LocalDate;
import java.util.List;


import com.training.educationsystem.entities.Message;

/**
 * 
 * @author Aarzoo
 *
 */
public interface MessageService {

	 Message addMessage(LocalDate localDate, String messageDescription);
	
	 Message  viewMessage(int messageId);
	
	 List<Message> viewAllMessages();
}
