package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators

	private By shoppingCart = By.linkText("Shopping Cart");

	// page class constructor

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Page Actions

	public String getShoppingCartPageHeading() {
		String pageHeading = eleUtil.doGetElementText(shoppingCart, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("User landed on : "+pageHeading);
		return pageHeading;
	}

}
