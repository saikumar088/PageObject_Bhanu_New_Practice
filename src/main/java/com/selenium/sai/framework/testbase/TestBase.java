package com.selenium.sai.framework.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.selenium.sai.framework.helper.browserConfigurations.BrowserType;
import com.selenium.sai.framework.helper.browserConfigurations.ChromeBrowser;
import com.selenium.sai.framework.helper.browserConfigurations.FirefoxBrowser;
import com.selenium.sai.framework.helper.browserConfigurations.config.ObjectReader;
import com.selenium.sai.framework.helper.browserConfigurations.config.PropertyReader;
import com.selenium.sai.framework.helper.logger.LoggerHelper;
import com.selenium.sai.framework.helper.resource.ResourceHelper;
import com.selenium.sai.framework.helper.wait.WaitHelper;
import com.selenium.sai.framework.utils.ExtentManager;

/**
 * 
 * @author Sai Kumar
 *
 */
public class TestBase {
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	//ConfigReader reader;
	
/*	@BeforeSuite
	public void beforeSutite() {	
		extent = ExtentManager.getInstance();	
	}*/
	
	@BeforeTest
	public void beforeTest() {
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("/src/main/resources/screenShots"));
		//other way we can do like below to take interface refeerence and create class bject
		//reader= new PropertyReader();
		
		setUpDriver(ObjectReader.reader.getBrowserType());
	}
	
/*	@BeforeClass
	public void beforeClass() {
		
		test =extent.createTest(getClass().getName());
	}*/
	
/*	@BeforeMethod
	public void beforeMethod(Method method) {
		
		test.log(Status.INFO, method.getName()+" test started");
		
	}*/

/*	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath= captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is pass");
			String imagePath= captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable() +" test skiped");
		}
		//we should flush the report after execution
		extent.flush();
	}*/
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null) {
			driver.quit();
		}
		
	}
	
	public WebDriver getBrowserObject(BrowserType browser) throws Exception {

		try {

			switch (browser) {
			case Chrome:
				// get object of chrome driver class
				ChromeBrowser chrome = new ChromeBrowser();
				ChromeOptions options = chrome.getChromeOptions();
				
				return chrome.getChromeDriver(options);
			case Firefox:
				FirefoxBrowser firefox = new FirefoxBrowser();
				FirefoxOptions firefoxOptions = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(firefoxOptions);
			default:
				throw new Exception("Driver not found:  " + browser.name());

			}

		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	
		public void setUpDriver(BrowserType browser) {
			try {
				driver=getBrowserObject(browser);
				log.info("WebDriver is intialised..."+driver.hashCode());
				WaitHelper wait= new WaitHelper(driver);
				wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
				wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		public String captureScreen(String filename,WebDriver driver) {
			
			if(driver==null) {
				log.info("driver is null..");
				return null;
			}if(filename=="") {
				
				filename="blank";
			}
			
			File destFile=null;
			Calendar  calander= Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			try {
				destFile= new File(reportDirectory+"/"+filename+"_"+formater.format(calander.getTime())+".png");
				Files.copy(scrFile.toPath(), destFile.toPath());
				//adding the screen shot to report
				Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
				
			}catch(Exception e){
				
				e.printStackTrace();
			}	
			return destFile.toString();	
		}
	

		public void getNavigationScreen(WebDriver driver) {
			
			log.info("capturing ui navigation scree...");
			String screen= captureScreen("",driver);
			
			try {
				test.addScreenCaptureFromPath(screen);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}


}
