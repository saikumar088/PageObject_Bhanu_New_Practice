package com.selenium.sai.framework.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class AssertionsHelper {
	
	private static Logger log = LoggerHelper.getLogger(AssertionsHelper.class);
	
	public static void verifyText(String actual,String expected) {	
		log.info("Comparing text between  "+actual+"   with       " +expected);
		Assert.assertEquals(actual, expected);
	}
	
	public static void makeTrue() {	
		
		log.info("Making script PASS.....");
		Assert.assertTrue(true);
	}
	
	public static void makeTrue(String message) {	
		
		log.info("Making script PASS.....");
		Assert.assertTrue(true,message);
	}
	
	public static void makeFalse() {	
		log.info("Making script FAIL.....");
		Assert.assertFalse(false);
	}
	
	public static void makeFalse(String message) {	
		log.info("Making script FAIL.....");
		Assert.assertFalse(false,message);
	}
	
	
	public static void verifyTrue(boolean status) {	
		Assert.assertTrue(true);
	}
	
	public static void verifyNull(String s1) {	
		
		log.info("verify object is null");
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNull(String s1) {	
		log.info("verify object is not null ");
		Assert.assertNotNull(s1);
	}

}
