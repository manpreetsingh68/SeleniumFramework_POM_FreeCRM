package com.freecrm.framework.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.framework.base.BaseTest;
import com.freecrm.framework.pageobjects.ContactsCreatePage;
import com.freecrm.framework.pageobjects.ContactsViewPage;
import com.freecrm.framework.pageobjects.HomePage;
import com.freecrm.framework.pageobjects.LoginPage;
import com.freecrm.framework.utils.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ContactsTest extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsViewPage contactsViewPage;
	String sheetName = "Contacts";
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	public ContactsTest() {
		super();
	}

	@BeforeTest
	public void setExtentReport() {
		extentReport = new ExtentReports(System.getProperty("user.dir") + "/test-output/NewExtentReport.html", true);
		extentReport.addSystemInfo("Host Name", "Manpreet's Machine");
		extentReport.addSystemInfo("User name", "Manpreet Singh");
		extentReport.addSystemInfo("Environment", "QA");
	}

	@AfterTest
	public void endExtentReport() {
		extentReport.flush();
		extentReport.close();
	}

	public static String getScreenShot(WebDriver driver, String screenShotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenShots/" + screenShotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination;
	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {
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

	@Test(dataProvider = "getCompanyData")
	public void test001_fillNewContactsForm(String title, String firstName, String lastName, String company) {
		extentTest = extentReport.startTest("Fill new contact form Test");
		loginPage.loginToCRM(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToPageiFrame("mainpanel");
		contactsViewPage.navigateToContactsPage();
		ContactsCreatePage contactsCreatePage = contactsViewPage.clickCreateNewContact();
		contactsCreatePage.createNewContact(title, firstName, lastName, company);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "FAILED TEST CASE IS " + result.getName());
			extentTest.log(LogStatus.FAIL, "FAILED TEST CASE THROWS " + result.getThrowable());

			String screenShotPath = ContactsTest.getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "SKIPPED TEST CASE IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "PASSED TEST CASE IS " + result.getName());
		}

		extentReport.endTest(extentTest);
		driver.quit();
	}
}
