package com.freecrm.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.framework.base.BaseTest;

public class ContactsViewPage extends BaseTest {

	public ContactsViewPage() {
		PageFactory.initElements(driver, this);
	}

	// +-----------------------------+
	// Declare data members
	// +-----------------------------+

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	private WebElement lbl_Contacts;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	private WebElement lnk_Contacts;
	
	@FindBy(xpath = "//input[@value='New Contact']")
	private WebElement btn_NewContact;

	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btn_Search;

	// +-----------------------------+
	// Declare members functions
	// +-----------------------------+
	
	public boolean navigateToContactsPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lnk_Contacts.click();
		return lbl_Contacts.isDisplayed();
	}
	
	public ContactsCreatePage clickCreateNewContact() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btn_NewContact.click();
		return new ContactsCreatePage();
	}

}
