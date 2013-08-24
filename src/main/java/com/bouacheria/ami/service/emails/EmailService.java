package com.bouacheria.ami.service.emails;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
//	@Autowired
//	private MailSender mailSender;
	 
	@Autowired
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = getMessage( from, to, subject, msg);
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(from);
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(msg);
		
		
		mailSender.send(message);	
	}
	
	private SimpleMailMessage getMessage(String from, String to, String subject, String msg)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		return message;
	}
	
	
	
	
	public void sendMailWithAttachement(String from, String to, String subject, String msg, InputStreamSource inputStreamSrc, String fileName) 
	{
		 
		   MimeMessage message = mailSender.createMimeMessage();
		   SimpleMailMessage simpleMailMessage = getMessage( from, to, subject, msg);
		   try
		   {
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
		 
				helper.setFrom(simpleMailMessage.getFrom());
				helper.setTo(simpleMailMessage.getTo());
				helper.setSubject(simpleMailMessage.getSubject());
				helper.setText(simpleMailMessage.getText());
		 
				helper.addAttachment(fileName, inputStreamSrc);
		   }
		   catch (MessagingException e) 
		   {
			   throw new MailParseException(e);
		   }
		   mailSender.send(message);
	   }

	public String getTo(String hospitalEmail, String userEmail) {
		
		StringBuilder email = new StringBuilder("");

		if(StringUtils.isNotEmpty(hospitalEmail))
		{
			email.append(hospitalEmail);
		}
		
//		if(StringUtils.isNotEmpty(userEmail))
//		{
//			if(email.length()>0)
//			{
//				email.append(","+userEmail);
//			}
//			else
//			{
//				email.append(userEmail);
//			}
//		}
		
		return email.toString();
	}
	public String getTo(String hospitalEmail) {
		
		StringBuilder email = new StringBuilder("");
		
		if(StringUtils.isNotEmpty(hospitalEmail))
		{
			email.append(hospitalEmail);
		}
		
		return email.toString();
	}
}
