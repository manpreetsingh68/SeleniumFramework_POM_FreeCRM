package com.freecrm.framework.testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.framework.base.BaseTest;
import com.freecrm.framework.pageobjects.CompaniesCreatePage;
import com.freecrm.framework.pageobjects.CompaniesViewPage;
import com.freecrm.framework.pageobjects.HomePage;
import com.freecrm.framework.pageobjects.LoginPage;
import com.freecrm.framework.utils.TestUtil;

public class CompaniesTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	CompaniesViewPage companiesViewPage;
	CompaniesCreatePage companiesCreatePage;
	String sheetName = "Companies";

	public CompaniesTest() {
		super();
	}

	@BeforeTest
	public void setUp() throws MalformedURLException {
		initialize();
		loginPage = new LoginPage();
		homePage = new HomePage();
		companiesViewPage = new CompaniesViewPage();
		testUtil = new TestUtil();
	}

	@Test(priority=1)
	public void test001_navigateToHomePage() {
		homePage = loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		boolean flag = homePage.isAtHomePage();
		Assert.assertTrue(flag, "Could not navigate to Home page");
	}

	@Test(priority=2)
	public void test002_navigateToCompaniesPage() {
		boolean flag = companiesViewPage.navigateToCompaniesPage();
		Assert.assertTrue(flag, "Could not navigate to Companies Page");
	}

	@Test(priority=3)
	public void test003_clickCreateNewCompany() {
		companiesCreatePage = companiesViewPage.clickCreateNewCompany();
		boolean flag = companiesCreatePage.isAtCreateCompanyPage();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getCompanyData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCompanyData")
	public void test004_fillNewCompanyForm(String company, String industry, String noOfEmps, String status) {
		companiesCreatePage.createNewCompany(company, industry, noOfEmps, status);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
