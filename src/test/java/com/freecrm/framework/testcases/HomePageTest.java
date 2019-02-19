package com.freecrm.framework.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.framework.base.BaseTest;
import com.freecrm.framework.pageobjects.HomePage;
import com.freecrm.framework.pageobjects.LoginPage;
import com.freecrm.framework.utils.TestUtil;

public class HomePageTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
	}

	@Test(priority=1)
	public void homePageTitleTest() {
		homePage = loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		String homepageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homepageTitle, "CRMPRO", "Home page title is not matching");
	}
	
	@Test(priority=2)
	public void homePageLogoTest() {
		homePage = loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		boolean flag = homePage.validateHomePageLogo();
		Assert.assertTrue(flag, "Logo is not displayed on Home page");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
