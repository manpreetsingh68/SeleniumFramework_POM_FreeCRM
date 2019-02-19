package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.framework.base.BaseTest;

public class LoginPage extends BaseTest {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//input[@name='username']")
	private WebElement txt_username;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement txt_password;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement btn_Login;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	private WebElement lnk_SignUp;

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	private WebElement lnk_Home;

	@FindBy(xpath = "//a[contains(text(),'Pricing')]")
	private WebElement lnk_Pricing;

	@FindBy(xpath = "//a[contains(text(),'Features')]")
	private WebElement lnk_Features;

	@FindBy(xpath = "//a[contains(text(),'Customers')]")
	private WebElement lnk_Customers;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	private WebElement lnk_Contact;

	@FindBy(xpath = "//div[@class='navbar-header']//img[@class='img-responsive']")
	private WebElement logo_CRMPRO_Login;

	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateLoginPageLogo() {
		return logo_CRMPRO_Login.isDisplayed();
	}

	public HomePage loginToCRM(String uname, String pword) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		txt_username.sendKeys(uname);
		txt_password.sendKeys(pword);
		btn_Login.click();
		return new HomePage();
	}
}
