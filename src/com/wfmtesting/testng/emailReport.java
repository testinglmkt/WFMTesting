package com.wfmtesting.testng;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class emailReport {
	
	public static void main(String [] args)
	   {
		
		
		// Create object of Property file
		Properties props = new Properties();
 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "outlook.lmkt.com");
 

 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props);
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("mfabrar@lmkt.com"));
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("testinglmkt@outlook.com"));
            
                        // Add the subject link
			message.setSubject("Testing Subject");
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText("This is message body");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			String filename = "C:\\Users\\mfabrar\\Desktop\\Automation\\ok.txt";
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
	
	   }
	
}
