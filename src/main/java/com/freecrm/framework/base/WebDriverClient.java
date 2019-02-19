package com.freecrm.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverClient {

	protected WebDriver driver;
	public WebDriverWait webDriverWait;

	public WebDriverClient(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForVisibilityOfElement(WebElement element) {
		return webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}

}
