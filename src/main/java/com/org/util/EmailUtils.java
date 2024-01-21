package com.org.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	 public boolean sendMail(String to, String subject, String body) {
	     boolean isSent = false;
		 
	    	try {
	        MimeMessage msg = mailSender.createMimeMessage();
	        MimeMessageHelper message = new MimeMessageHelper(msg);
	        
	        message.setFrom("subhasisghoshgopi@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(body,true);
	        mailSender.send(msg);
	        
	        isSent = true;
	    	}catch (Exception e) {
				System.out.println(e.getMessage());
			}
	        
	    	return isSent;
	    }
}
