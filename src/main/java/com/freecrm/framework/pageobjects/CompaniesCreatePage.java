package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.freecrm.framework.base.BaseTest;

public class CompaniesCreatePage extends BaseTest {

	public CompaniesCreatePage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//legend[contains(text(),'Create New  Company')]")
	private WebElement lbl_CreateNewCompany;
	
	@FindBy(xpath = "//input[@name='company_name']")
	private WebElement txt_Company;
	
	@FindBy(xpath = "//input[@name='industry']")
	private WebElement txt_Industry;
	
	@FindBy(xpath = "//input[@name='num_of_employees']")
	private WebElement txt_NoOfEmployess;
	
	@FindBy(xpath = "//select[@name='status']")
	private WebElement select_Status;

	@FindBy(xpath = "//legend[contains(text(),'Create New  Company')]/..//preceding-sibling::input[@value='Save']")
	private WebElement btn_Save;
	
	
	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+
	
	public boolean isAtCreateCompanyPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return lbl_CreateNewCompany.isDisplayed();
	}
	
	public void createNewCompany(String company, String industry, String noOfEmps, String status) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		txt_Company.sendKeys(company);
		txt_Industry.sendKeys(industry);
		txt_NoOfEmployess.sendKeys(noOfEmps);
		
		Select select = new Select(select_Status);
		select.selectByVisibleText(status);
		btn_Save.click();
	}
	
}
