package com.selenium.sai.framework.helper.resource;


//Tip : Better to give / instead of double back slashes where ever give paths
public class ResourceHelper {
	
	public static String getResourcePath(String path) {
		
     	String basePath = System.getProperty("user.dir");	
		return basePath  +path;	
	}
	
	
/*
 * 
 * Testing	

	public static void main(String[] args) {
		
		String fullpath= ResourceHelper.getResourcePath("/src/main/resources/drivers/chromedriver.exe");
	
		System.out.println(System.getProperty("user.dir"));
	    System.out.println(fullpath);
		System.setProperty("webdriver.chrome.driver", fullpath);
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	
	}
*/
}
