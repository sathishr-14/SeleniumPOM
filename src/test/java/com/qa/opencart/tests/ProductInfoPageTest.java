package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart App - Product info Page")
@Story("US 101: Open product info with multiple features")
@Listeners(TestAllureListener.class)
public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}

	@Test(priority = 3)
	public void productInfoTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		Assert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
	}

//	@Test
//	public void addToCartTest() {
//		searchResultPage = accountsPage.doSearch("Macbook");
//		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
//		cartpage = productInfoPage.addToCart();
//		Assert.assertEquals(true, productInfoPage.addToCart().isDisplayed());
//	}

}
