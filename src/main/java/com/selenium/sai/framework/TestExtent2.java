package com.selenium.sai.framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.sai.framework.testbase.TestBase;

public class TestExtent2 extends TestBase {

	@Test
	public void test1() {
		Assert.assertTrue(true);
	}

	@Test
	public void test2() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods="test2")
	public void test3() {
		Assert.assertTrue(true);
	}
	
	
	@Test
	public void test4() {
		Assert.assertTrue(true);
	}
	
}