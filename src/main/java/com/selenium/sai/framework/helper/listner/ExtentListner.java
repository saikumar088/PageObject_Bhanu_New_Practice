package com.selenium.sai.framework.helper.listner;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.sai.framework.helper.logger.LoggerHelper;
import com.selenium.sai.framework.testbase.TestBase;
import com.selenium.sai.framework.utils.ExtentManager;

public class ExtentListner extends TestBase implements ITestListener{
	
	public static ExtentReports extent;
	public static ExtentTest test;
	private Logger log = LoggerHelper.getLogger(ExtentListner.class);

	public void onTestStart(ITestResult result) {
		test.log(Status.INFO, result.getMethod().getMethodName()+" started..");
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+" Passed..");
		Reporter.log(result.getMethod().getMethodName()+" Test Passed..");
		log.info(result.getMethod().getMethodName()+" Test Passed..");
		String imagePath= captureScreen(result.getName(),driver);
		try {
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Failed.. "+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" Test Failed.. "+result.getThrowable());
		
		String imagePath= captureScreen(result.getName(),driver);
		try {
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Skipped.. "+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" Test Skipped.. "+result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		extent = ExtentManager.getInstance();
		test =extent.createTest(context.getName());
		Reporter.log(context.getClass().getSimpleName()+" Test Started..");
		log.info(context.getName()+" Test Started..");
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		Reporter.log(context.getClass().getSimpleName()+" Test Completed ..");
		log.info(context.getName()+" Test Completed ..");
	}

}
