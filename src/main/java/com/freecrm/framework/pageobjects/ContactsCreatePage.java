package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.freecrm.framework.base.BaseTest;

public class ContactsCreatePage extends BaseTest {

	public ContactsCreatePage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//legend[contains(text(),'Contact Information')]")
	private WebElement lbl_ContactInformation;

	@FindBy(xpath = "//select[@name='title']")
	private WebElement select_Title;

	@FindBy(xpath = "//input[@name='first_name']")
	private WebElement txt_FirstName;

	@FindBy(xpath = "//input[@name='surname']")
	private WebElement txt_LastName;

	@FindBy(xpath = "//input[@name='client_lookup']")
	private WebElement txt_Company;
	
	@FindBy(xpath = "//input[@value='Load From Company']/..//input[@value='Save']")
	private WebElement btn_Save;

	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+

	public void isAtCreateContactPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lbl_ContactInformation.isDisplayed();
	}
	
	public void createNewContact(String title, String firstName, String lastName, String company) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Select select = new Select(select_Title);
		select.selectByVisibleText(title);
		txt_FirstName.sendKeys(firstName);
		txt_LastName.sendKeys(lastName);
		txt_Company.sendKeys(company);
		btn_Save.click();
	}

}
