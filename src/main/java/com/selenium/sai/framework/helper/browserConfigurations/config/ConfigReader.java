package com.selenium.sai.framework.helper.browserConfigurations.config;

import com.selenium.sai.framework.helper.browserConfigurations.BrowserType;

public interface ConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();

}
