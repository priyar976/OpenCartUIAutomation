package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ShoppingCartPageTest extends BaseTest {
	
	@BeforeClass
	public void infoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(dataProvider = "shoppingCartHeaderTestData")
	public void shoppingCartHeaderTest(String searchKey, String productName, String currentTab) {
		
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		productInfoPage.selectQuantityAndAddToCart(5);
		shoppingCartPage = productInfoPage.goToShoppingCart();
	
		Assert.assertTrue(shoppingCartPage.getShoppingCartPageHeading().equals(currentTab));
	}
	
	@DataProvider
	public Object[][] shoppingCartHeaderTestData(){
		return ExcelUtil.getTestData(AppConstants.SHOPPING_CART_SHEET_NAME);
	}

}
