package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void searchResultSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider="testData")
	public void searchResultCountTest(String searchKey, int count) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		Assert.assertTrue(searchResultsPage.getSearchProductCount()==count);
	}
	
	@DataProvider
	public Object[][] testData(){
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}

}
