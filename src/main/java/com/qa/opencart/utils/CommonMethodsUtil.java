package com.qa.opencart.utils;

public class CommonMethodsUtil {
	
	public static String getRandomEmailId() {
		String emailId = "testautomation" + System.currentTimeMillis()+ "@opencart.com";
		return emailId;
	}

}
