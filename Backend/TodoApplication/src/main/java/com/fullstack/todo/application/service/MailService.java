package com.fullstack.todo.application.service;

import com.fullstack.todo.application.shared.EmailBody;

public interface MailService {

	void sendMail(EmailBody emailBody);
	
	String build(String message);

}
