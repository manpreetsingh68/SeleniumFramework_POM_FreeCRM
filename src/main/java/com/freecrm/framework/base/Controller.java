package com.freecrm.framework.base;

import org.openqa.selenium.support.PageFactory;

import com.freecrm.framework.pageobjects.LoginPage;

public class Controller extends BaseTest{
	
	private <T extends BaseTest > T initPage(Class<T> t) {
		return PageFactory.initElements(driver, t);
		
	}
	public LoginPage loginPage() {
		return initPage(LoginPage.class);
	}
	
	
}
