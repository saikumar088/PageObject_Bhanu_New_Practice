package com.selenium.sai.framework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
	int i =1;
	@Test
	public void test2() {
		

		if(i==3) {
		Assert.assertTrue(true);

		}else {
		System.out.println(i);
		i++;
		Assert.assertTrue(false);
		}
	}

}
