package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	/*thread local driver is created to give a copy of driver to each test block 
	when we run test cases in parallel*/
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialize the browser
	 * @param browser
	 * @return this will return a driver
	 */
	
	public WebDriver init_driver(Properties prop) {
		String browserName =  prop.getProperty("browser").trim();
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			System.out.println("Passed browser is : " + browserName);
		} else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			System.out.println("Passed browser is : " + browserName);
		} else {
			System.out.println("Please pass the right browser : "+browserName);
		}
		
//		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	// To initialize config properties we need to use properties class in java by creating object of it
	public Properties init_prop() {
		prop = new Properties();
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		
		if(envName == null) {
			System.out.println("Running on PROD env :");
		try {
			// to interact with the config properties FileInputStream in java is used
			ip = new FileInputStream("./src/test/resources/config/config.properties");
			// to load the data from config properties
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		} else {
			System.out.println("Running on environment : " +envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}		
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
