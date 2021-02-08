package com.project2.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RCCLogin {
	
	public static final String title = "Roadkill Cafe Catering";
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//input[@type='submit'][@name='usersubmit']")
	public WebElement userSubmit;
	
	@FindBy(xpath="//a[text()='Login']")
	public WebElement login;
	
	
	public RCCLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void goToLogin() {
		this.login.click();
	}
	
	public void loginToRoadkillCafe(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.userSubmit.click();
	}
	
	

}
