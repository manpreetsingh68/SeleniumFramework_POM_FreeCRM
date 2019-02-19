package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.framework.base.BaseTest;

public class HomePage extends BaseTest {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//td[contains(text(),'User: Manpreet Singh ')]")
	private WebElement lbl_username;

	@FindBy(xpath = "//a[@title='Home']")
	private WebElement lnk_Home;
	
	@FindBy(xpath = "//td[contains(text(),'CRMPRO')]")
	private WebElement logo_CRMPRO_Home;

	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+

	public boolean isAtHomePage() {
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return lbl_username.isDisplayed();
	}

	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateHomePageLogo() {
		return logo_CRMPRO_Home.isDisplayed();
	}

}
