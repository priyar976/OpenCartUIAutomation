package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	
	@BeforeClass
	public void infoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=0)
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	
	@Test(priority=1, dataProvider="productImagesCountTestData")
	public void productImagesCountTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}
	
	
	@Test(priority=2, dataProvider="productInfoTestData")
	public void productInfoTest(String searchKey, String productName, String brand, 
			String productCode, String availability, String productPrice, String extaxPrice ) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"), brand);
		softAssert.assertEquals(productActDetailsMap.get("Product Code"), productCode);
		softAssert.assertEquals(productActDetailsMap.get("Availability"), availability);
		softAssert.assertEquals(productActDetailsMap.get("productprice"), productPrice);
		softAssert.assertEquals(productActDetailsMap.get("extaxprice"), extaxPrice);
		softAssert.assertAll();
		
	}
	
	@Test(priority=3)
	public void productAddedToCartMsg() {
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertTrue(productInfoPage.selectQuantityAndAddToCart(3).contains("Success"));
	}
	
	@DataProvider
	public Object[][] productImagesCountTestData(){
		return new Object[][] {
			{"macbook","MacBook Air", 4},
			{"iphone", "iPhone",6},
			{"tab", "Samsung Galaxy Tab 10.1", 7}
		};
	}
	
	@DataProvider
	public Object[][] productInfoTestData(){
		return new Object[][] {
			{"macbook","MacBook Pro", "Apple", "Product 18", "In Stock", "$2,000.00", "$2,000.00"},
		};
	}
	

}
