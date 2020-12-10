package com.cerner.pran.service;

public interface UtilitiesService {
	public String passwordGenerator(int length);
	public boolean mailSender(String to, String subject, String text);
	public String passwordEncrypter(String password);
	public String passwordDecrypter(String password);
}
