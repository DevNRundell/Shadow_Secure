package com.shadowsecure.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendVerificationEmail extends MailAgent {
	
	public final static String VERIFICATION_LINK = "http://localhost/shadow_secure/resources/php_scripts/email_verify.php?verify=";
	
	public boolean send(String hashedUsername) {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost); 
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.port", "25");
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(from, password);  
		    }  
		});
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Shadow Secure - Verify Email");	
			message.setText(VERIFICATION_LINK + hashedUsername);
			
			Transport.send(message);
			
			return true;
			
		} catch (MessagingException mex) {
	         mex.printStackTrace();
	    }
		
		return false;
	}
}
