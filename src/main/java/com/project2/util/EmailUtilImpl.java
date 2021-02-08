package com.project2.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil{
	
	@Autowired
    public JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
        System.out.println("trying");
        
		
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		/*
		 * MimeMessage message = emailSender.createMimeMessage();
		 * 
		 * MimeMessageHelper helper = new MimeMessageHelper(message, true);
		 * 
		 * helper.setTo(to); helper.setSubject(subject); helper.setText(text);
		 * 
		 * FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		 * helper.addAttachment("Invoice", file);
		 * 
		 * emailSender.send(message);
		 */
		
	}

}
