package com.org.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {

	public static String generateRandomPwd() {
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*")).toCharArray();
		String pwd = RandomStringUtils.random(6 , possibleCharacters);
		
		return pwd;
	}
}
