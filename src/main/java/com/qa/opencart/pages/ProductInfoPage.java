package com.qa.opencart.pages;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil; 
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By clickviewcart = By.id("cart");
	private By viewcart = By.xpath("(//p[@class=\"text-right\"]//a//strong)[1]");
	private Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String productHeaderText  = eleUtil.getElement(productHeader).getText();
		System.out.println("Product header is : "+productHeaderText);
		return productHeaderText;
	}
	
	public int getProductImagesCount() {
		int count = eleUtil.waitForElementsToBeVisible(productImages, 10).size();
		System.out.println(count);
		System.out.println();
		return count;
	}
	
	public Map<String,String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}
	
	private void getProductMetaData() {
		List <WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText();
			String meta[]=text.split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			productInfoMap.put(metakey, metavalue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement>metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("ExcTaxPrice", exPrice);
		
	}
}
