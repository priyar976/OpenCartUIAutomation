package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators

	private By logoutLink = By.linkText("Logout1");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	
	//page class constructor
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//Page Actions

	public String getAccountPageTitle() {
		
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("My account page title : " + title);
		return title;
	}
	
	public boolean isLogoutLinkDisplayed() {
		return eleUtil.isElementDisplayed(logoutLink, TimeUtil.DEFAULT_MEDIUM_TIME);

	}
	
	public boolean isMyAccountLinkDisplayed() {
		return eleUtil.isElementDisplayed(myAccountLink, TimeUtil.DEFAULT_MEDIUM_TIME);

	}
	
	public List<String> getAccountsPageHeaders() {
		List<WebElement> headersEleList = eleUtil.getElements(headers);
		List<String> headersList = new ArrayList<String>();
		for(WebElement e : headersEleList)
		{
			headersList.add(e.getText());
		}
		return headersList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching key : "+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
