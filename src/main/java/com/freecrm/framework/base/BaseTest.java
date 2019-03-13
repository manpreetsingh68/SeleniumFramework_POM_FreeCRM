package com.freecrm.framework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.freecrm.framework.utils.TestUtil;
import com.freecrm.framework.utils.WebEventListener;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver eDriver;
	public static WebEventListener eventListener;

	public BaseTest() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Manpreet\\workspace\\SeleniumFramework_POM_FreeCRM\\src\\main\\java\\com\\freecrm\\framework\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialize() throws MalformedURLException {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.WIN10);
			
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
			
			String hubUrl = "http://192.168.1.6:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(hubUrl), options);
			//WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", prop.getProperty("CHROME_DRIVER_PATH"));
			//driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.chrome.driver", prop.getProperty("FIREFOX_DRIVER_PATH"));
			driver = new FirefoxDriver();
		}

		eDriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eDriver.register(eventListener);
		driver = eDriver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("URL"));
	}
}
