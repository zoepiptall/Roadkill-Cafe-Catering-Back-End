package com.project2.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CafeDriverUtility {
	
	public static WebDriver driver;
	
	public static final String url = "http://localhost:4200/";
	// These annotations are Cucumber tag webhooks that inject themselves before all of the other tests begin
	@Before
	public void setUp() {
		String file = "src/test/java/selenium/chromedriver.exe";  //file path for the driver
		// /Project-2-Roadkill-Cafe-Catering/src/test/java/selenium/chromedriver.exe
		System.setProperty("webdriver.chrome.driver", file);
		driver = new  ChromeDriver(); 
		driver.get(url);
	}
	
	@After
	public void tearDown() {
		
		if(driver != null) {
			driver.quit();
		}
		
	}

}
