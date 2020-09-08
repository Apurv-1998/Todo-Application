package com.fullstack.todo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fullstack.todo.application.exception.SpringTodoException;
import com.fullstack.todo.application.service.MailService;
import com.fullstack.todo.application.shared.EmailBody;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void sendMail(EmailBody emailBody) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("springtodo@gmail.com");
			messageHelper.setTo(emailBody.getRecipient());
			messageHelper.setSubject(emailBody.getSubject());
			messageHelper.setText(build(emailBody.getBody()));
			
		};
		
		
		try {
			
			javaMailSender.send(messagePreparator);
			System.out.println("MAIL SENT!!!!!!");
			
			
		}catch(Exception e) {
			
			throw new SpringTodoException("Mail Cannot Be Sent");
			
		}
		
		
	}

	@Override
	public String build(String message) {
		
		Context context = new Context();
		context.setVariable("message", message);
		
		return templateEngine.process("emailTemplate", context);
	}

}
