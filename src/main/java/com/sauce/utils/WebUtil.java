package com.sauce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import lombok.Getter;

@Getter
public class WebUtil {

	protected WebDriver driver;
	private ExtentTest extTest;
	private Properties prop;

	public void setExtentTest(ExtentTest extTest) {

		this.extTest = extTest;

	}

	private static WebUtil util;

	private WebUtil() {

	}

	public static WebUtil getObject() {

		if (util == null) {
			util = new WebUtil();
		}

		return util;

	}

	public void launchBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			
			System.out.println("Chrome Browser is launched sucessfully");
		} else if (browserName.equalsIgnoreCase("firefox")) {
//				System.setProperty("webdriver.chrome.driver", "Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			System.out.println("FireFox Browser is launched sucessfully");
		} else if (browserName.equalsIgnoreCase("edge")) {
//				System.setProperty("webdriver.chrome.driver", "Driver\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			System.out.println("Edge Browser is launched sucessfully");
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			System.out.println("Safari Browser is launched sucessfully");
		} else {
			System.out.println("Unsupported browser choice");

		}

	}

	public void hitUrl(String url) {

		driver.get(url);
		System.out.println(url + " url is launched successfully");

	}

	public void quit() {

		try {
			driver.quit();

			extTest.log(Status.PASS, "All the  window or tab is closed successfully");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.open('', '_self', ''); window.close();");
			extTest.log(Status.PASS, "All the  window or tab is closed successfully");
		} catch (NoSuchSessionException e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "All the window or tab is not closed successfully");
			throw e;
		} catch (WebDriverException e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "All the  window or tab is not closed successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "All the  window or tab is not closed successfully");
			throw e;
		}
	}

	public void inputValue(WebElement webObj, String inputValue) {

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			extTest.log(Status.PASS, webObj + " Element is Displayed and enabled ");

			try {
				webObj.sendKeys(inputValue);
				extTest.log(Status.PASS, inputValue + webObj + " value is passed in textbox successfully");

			} catch (ElementNotInteractableException e) {

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].value='" + inputValue + "'", webObj);
				extTest.log(Status.PASS, inputValue + webObj + " value has entered in textbox successfully");
			}catch (Exception e) {
				e.printStackTrace();
				extTest.log(Status.FAIL, inputValue + webObj + " value hasn't entered in textbox successfully");
				throw e;
			}

		} else {
			extTest.log(Status.FAIL, webObj + " Element is Displayed and enabled");
		}
	}

	public void click(WebElement webObj) {

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			extTest.log(Status.PASS, webObj + " Element is Displayed and enabled ");

			try {
				webObj.click();
				extTest.log(Status.PASS, webObj + " click on element successfully");

			} catch (ElementClickInterceptedException e) {

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", webObj);
				extTest.log(Status.PASS, webObj + " click on element successfully");

			} catch (Exception e) {
				e.printStackTrace();
				extTest.log(Status.FAIL, webObj + " click on element successfully");
				throw e;
			}

		} else {
			extTest.log(Status.FAIL, "Element is Displayed and enabled");
		}
	}

	public void lodProperties() {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("src\\test\\resources\\configproperty.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();

		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDataFromPropperties(String inputValue) {

		return prop.getProperty(inputValue);

	}
}
