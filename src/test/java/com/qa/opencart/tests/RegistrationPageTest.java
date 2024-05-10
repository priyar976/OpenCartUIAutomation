package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.CommonMethodsUtil;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registrationPageSetup() {
		registrationPage = loginPage.doRegister();
		}
	
	@Test(priority = 1)
	public void RegistrationPageTitleTest() {
		String title = registrationPage.getRegistrationPageTitle();
		Assert.assertEquals(title, AppConstants.REGISTRATION_PAGE_TITLE);
	}
	
	@Test(priority = 2, dataProvider = "csvregdata")
	public void doRegisterTest(String fName, String lName, String telephoneNo, String pwd ) {
		String emailId = CommonMethodsUtil.getRandomEmailId();
		String msg = registrationPage.doRegister(fName, lName, emailId, telephoneNo, pwd);
		Assert.assertEquals(msg, AppConstants.SUCCESS_REGISTRATION_MESSAGEE);
	}
	
	@DataProvider
	public Object[][] doRegisterTestData(){
		return new Object[][] {
			{"Priya", "Rani", "0987654321", "Priya@123"}
		};
	}
	
	@DataProvider
	public Object[][] doRegisterTestDataExcel(){
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider(name="csvregdata")
	public Object[][] getUserRegTestDataFromCSV() {
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}
	



}
