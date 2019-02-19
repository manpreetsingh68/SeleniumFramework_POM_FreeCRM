package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.framework.base.BaseTest;

public class CompaniesViewPage extends BaseTest {

	public CompaniesViewPage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//td[contains(text(),'Companies')]")
	private WebElement lbl_Companies;
	
	@FindBy(xpath = "//a[@title='Companies']")
	private WebElement lnk_Companies;
	
	@FindBy(xpath = "//input[@value='New Company']")
	private WebElement btn_NewCompany;
	
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btn_Search;
	
	

	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+
	
	public boolean navigateToCompaniesPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lnk_Companies.click();
		return lbl_Companies.isDisplayed();
	}
	
	public CompaniesCreatePage clickCreateNewCompany() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btn_NewCompany.click();
		return new CompaniesCreatePage();
	}

}
