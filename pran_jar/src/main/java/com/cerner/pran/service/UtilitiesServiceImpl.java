package com.cerner.pran.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {
	
	@Value("${encrypted.property}")
	private String property;
	
	private String getProperty() {
		return property;
	}
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String passwordGenerator(int length) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());
			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@Override
	public boolean mailSender(String to, String subject, String text) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(text, "text/html");
			// Send message
			mailSender.send(message);
			System.out.println("message sent successfully....");
			return true;

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String passwordEncrypter(String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(this.getProperty());
		return encryptor.encrypt(password);
	}

	@Override
	public String passwordDecrypter(String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(this.getProperty());
		try {
			return encryptor.decrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
