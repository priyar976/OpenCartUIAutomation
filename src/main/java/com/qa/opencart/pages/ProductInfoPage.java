package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;

	private Map<String, String> productMap = new HashMap<String, String>();

	// Private By Locators

	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By productQuantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By successMsg = By.xpath("//div[contains(text(),'Success: You have added ')]");
	private By linkShoppingCart = By.linkText("shopping cart");

	// Public Page Class Constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//Page Actions
	
	public String getProductHeader() {
		String header = eleUtil.doGetElementText(productHeader);
		System.out.println(header);
		return header;
	}

	public int getProductImagesCount() {
		int totalImages = eleUtil.waitForElementsVisible(images, 10).size();
		System.out.println("Image count for " + getProductHeader() + " : " + totalImages);
		return totalImages;
	}


	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String text = e.getText();
			String metakey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metakey, metaValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", price);
		productMap.put("extaxprice", exTaxPrice);

	}

	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", getProductHeader());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product details : "+productMap);
		return productMap;
	}
	
	public String selectQuantityAndAddToCart(int qty) {
		eleUtil.doSendKeys(productQuantity, String.valueOf(qty));
		eleUtil.doClick(addToCartButton);
		String successMessage = eleUtil.doGetElementText(successMsg, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println(successMessage);
		return successMessage;
		
	}
	
	public ShoppingCartPage goToShoppingCart() {
		eleUtil.doClick(linkShoppingCart);
		return new ShoppingCartPage(driver);
	}

}
