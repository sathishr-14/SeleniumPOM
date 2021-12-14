package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productName = By.xpath("(//td[@class=\"text-left\"]//a)[1]");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductName() {
		String product = eleUtil.getElement(productName).getText();
		System.out.println("Added product to cart : " +product);
		return product;
	}

}
