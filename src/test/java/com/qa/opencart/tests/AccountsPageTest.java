package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String title = accountsPage.getAccountPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void isLogoutLinkDisplayed() {
		Assert.assertTrue(accountsPage.isLogoutLinkDisplayed(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test
	public void isMyAccountLinkDisplayed() {
		Assert.assertTrue(accountsPage.isMyAccountLinkDisplayed(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test
	public void getHeadersTest() {
		List<String> headersList = accountsPage.getAccountsPageHeaders();
		System.out.println("Headers list : "+headersList);
	}
	
	@Test
	public void searchTest() {
		accountsPage.doSearch("macbook");
	}

}
