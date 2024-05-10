package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By privacyPolicy = By.name("agree");
	private By submit = By.xpath("//input[@type='submit']");
	private By accCreationSuccessMsg = By.tagName("h1");
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// page class constructor

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Page Actions

	public String getRegistrationPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.REGISTRATION_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Page Title ----- " + driver.getTitle());
		return title;
	}

	public String doRegister(String fName, String lName, String emailId, String telephoneNo, String pwd) {
		eleUtil.doSendKeys(firstName, fName);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(email, emailId);
		eleUtil.doSendKeys(telephone, telephoneNo);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirmPassword, pwd);
		eleUtil.doClick(privacyPolicy);
		eleUtil.doClick(submit);
		eleUtil.isElementDisplayed(accCreationSuccessMsg, 5);
		String successMsg = eleUtil.doGetElementText(accCreationSuccessMsg);
		System.out.println("Success message : " + successMsg);
		eleUtil.doClick(logOutLink);
		eleUtil.doClick(registerLink);
		return successMsg;
	}

}
