package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By register = By.linkText("Register");

	
	//page class constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//Page Actions

	public String getLoginPageTitle() {
		
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Login page title : " + title);
		return title;
	}

	public AccountsPage doLogin(String userName, String pwd) {
		
		eleUtil.doSendKeys(emailId, userName, 5);
		eleUtil.doSendKeys(password, pwd, 5);
		eleUtil.doClick(loginButton, 5);
		
		String accPagetitle = eleUtil.waitForTitleIs("My Account", TimeUtil.DEFAULT_MEDIUM_TIME);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage doRegister() {
		eleUtil.doClick(register, 5);
		System.out.println("User clicked Register link!");
		return new RegistrationPage(driver);
	}

}
