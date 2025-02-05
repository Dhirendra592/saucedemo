package com.sauce.base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sauce.utils.WebUtil;

public class BaseTest {

	protected WebUtil webUtil = WebUtil.getObject();;
	private ExtentReports extent;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Extent-Report Initialization");
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports\\SauceDemoExtentReport.html");
		extent.attachReporter(spark);
		webUtil.lodProperties();
	}

	@BeforeMethod
	public void beforeMethod(Method mt) {
		ExtentTest extTest = extent.createTest(mt.getName());
		webUtil.setExtentTest(extTest);
		webUtil.launchBrowser(webUtil.getDataFromPropperties("browser"));
		webUtil.hitUrl(webUtil.getDataFromPropperties("url"));
	}

	@AfterMethod
	public void afterMethod(ITestResult itest, Method mt) {

		webUtil.quit();
		extent.flush();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Extent-Report Finalization");
		extent.flush();
//       Desktop desk=   Desktop.getDesktop();
//       try {
//		desk.open(new File("D:\\Man_Singh_Main_Practics_In_Eclips\\saucedemo\\ExtentReports\\SauceDemoExtentReport.html"));
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	}

}
