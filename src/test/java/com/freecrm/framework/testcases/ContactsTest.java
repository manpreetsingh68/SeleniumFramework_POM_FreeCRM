package com.freecrm.framework.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.framework.base.BaseTest;
import com.freecrm.framework.pageobjects.ContactsCreatePage;
import com.freecrm.framework.pageobjects.ContactsViewPage;
import com.freecrm.framework.pageobjects.HomePage;
import com.freecrm.framework.pageobjects.LoginPage;
import com.freecrm.framework.utils.TestUtil;

public class ContactsTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsViewPage contactsViewPage;
	String sheetName = "Contacts";

	public ContactsTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		homePage = new HomePage();
		contactsViewPage = new ContactsViewPage();
		testUtil = new TestUtil();
	}

	@DataProvider
	public Object[][] getCompanyData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(dataProvider="getCompanyData")
	public void test001_fillNewContactsForm(String title, String firstName, String lastName, String company) {
		loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		contactsViewPage.navigateToContactsPage();
		ContactsCreatePage contactsCreatePage = contactsViewPage.clickCreateNewContact();
		contactsCreatePage.createNewContact(title, firstName, lastName, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
