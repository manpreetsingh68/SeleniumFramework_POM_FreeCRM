package com.freecrm.framework.testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.framework.base.BaseTest;
import com.freecrm.framework.pageobjects.HomePage;
import com.freecrm.framework.pageobjects.LoginPage;
import com.freecrm.framework.utils.MyScreenRecorder;
import com.freecrm.framework.utils.TestUtil;

public class LoginPageTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		initialize();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.",
				"Title is not matching");
	}

	@Test(priority = 2)
	public void loginPageLogoTest() {
		boolean flag = loginPage.validateLoginPageLogo();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void loginToCRMTest() throws Exception {
		MyScreenRecorder.startRecording("LoginToCRM");
		
		homePage = loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		Assert.assertTrue(homePage.isAtHomePage());
		
		MyScreenRecorder.stopRecording();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
