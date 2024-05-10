package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators

	private By searchProducts = By.cssSelector("div.product-thumb");

	
	//page class constructor
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//Page Actions
	
	public int getSearchProductCount() {
		int count = eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("Total search result count : "+count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("searching for product: " + productName);
		eleUtil.waitForElementVisible(By.linkText(productName), TimeUtil.DEFAULT_MEDIUM_TIME).click();
		return new ProductInfoPage(driver);
	}

}
