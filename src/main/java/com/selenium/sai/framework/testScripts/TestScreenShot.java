package com.selenium.sai.framework.testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.sai.framework.testbase.TestBase;

public class TestScreenShot extends TestBase{
	
	@Test
	public void testScreen() {
		
		driver.get("https://stackoverflow.com/questions/3438261/trying-to-copy-file-from-one-location-to-another");
		//captureScreen("firstScreen");
		//Assert.assertTrue(false);
	}

}
